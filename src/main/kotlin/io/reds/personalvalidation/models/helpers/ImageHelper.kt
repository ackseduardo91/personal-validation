package io.reds.personalvalidation.models.helpers

import io.reds.personalvalidation.models.enum.FileType
import io.reds.personalvalidation.models.enum.ServiceError
import io.reds.personalvalidation.models.errors.ServiceException
import org.apache.tika.Tika
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.core.MatOfRect
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import org.tensorflow.SavedModelBundle
import org.tensorflow.Tensor
import kotlin.math.sqrt


class ImageHelper {

    companion object{

        //Valida se o arquivo é do tipo JPG ou JPEG
        fun isImageFile(file: ByteArray): Boolean {
            val tika = Tika()
            val mimeType = tika.detect(file)
            return mimeType.startsWith(FileType.IMAGE_JPEG.type) || mimeType.startsWith(FileType.IMAGE_JPG.type)
        }

        //converte o ByteArray em uma imagem Mat do OpenCV
        fun byteArrayToMat(image: ByteArray): Mat {
            return Imgcodecs.imdecode(MatOfByte(*image), Imgcodecs.IMREAD_UNCHANGED)
        }

        //Valida se tem apenas uma face na imagem, utilizando o modelo haar cascade do opencv
        fun detectImageFaces(image: Mat): Boolean {
            val faceCascade = CascadeClassifier("/files/models/haarcascade_frontalface_default.xml")
            val grayImage = Mat()
            val faces = MatOfRect()

            Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY)
            Imgproc.equalizeHist(grayImage, grayImage)

            faceCascade.detectMultiScale(grayImage, faces)

//            faceCascade.detectMultiScale(
//                grayImage, faces, 1.1, 3, 0,
//                Size(30.0, 30.0), Size(300.0, 300.0)
//            )

            return faces.toList().size == 1
        }

        // Função para comparar características de uma lista de imagens
        fun checkCompatibilityBetweenFace(faces: List<Mat>): Boolean{
            val threshold = 0.5f // Limite de distância aceitável

            for (i in 0 until faces.size - 1) {
                val features1 = extractFeatures(faces[i])

                for (j in i + 1 until faces.size) {
                    val features2 = extractFeatures(faces[j])
                    val distance = calculateEuclideanDistance(features1, features2)

                    if (distance > threshold) {
                        throw ServiceException(ServiceError.INVALID_NOT_COMPATIBLE_FACE)
                    }
                }
            }
            return true
        }

        // Função para extrair características de uma imagem
        private fun extractFeatures(img: Mat): FloatArray{
            val modelDir = "/files/models/tensorflow_keras_extract_feature_face.pb"

            SavedModelBundle.load(modelDir, "serve").use { model ->
                val inputTensor = Tensor.create(img)
                val outputTensor = model.session().runner()
                    .feed("input", inputTensor)
                    .fetch("output")
                    .run()[0]

                val outputShape = outputTensor.shape()
                val outputArray = FloatArray(outputShape.size)
                outputTensor.copyTo(outputArray)

                return outputArray
            }
        }

        // Função para calcular a distância euclidiana entre duas características
        private fun calculateEuclideanDistance(features1: FloatArray, features2: FloatArray): Float {
            var sum = 0.0f
            for (i in features1.indices) {
                val diff = features1[i] - features2[i]
                sum += diff * diff
            }
            return sqrt(sum)
        }

    }
}