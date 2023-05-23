package io.reds.personalvalidation.modules.selfiesValidation.service

import io.reds.personalvalidation.models.dtos.SelfieRequestDTO
import io.reds.personalvalidation.models.enum.ServiceError
import io.reds.personalvalidation.models.errors.ServiceException
import io.reds.personalvalidation.models.helpers.ImageHelper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SelfieServiceImpl: SelfieService {
    @Value("\${directory.files.models}")
    lateinit var directoryModels: String

    override fun validateSelfie(request: SelfieRequestDTO): Boolean {
        // Verificar se existem arquivos de imagem inválidos
        val invalidFiles = request.selfies.filter { !ImageHelper.isImageFile(it) }
        if (invalidFiles.isNotEmpty()) {
            throw ServiceException(ServiceError.INVALID_TYPE_FILE_FACE)
        }

        // Verificar se há mais de uma face detectada em cada imagem
        request.selfies.forEach { selfie ->
            val imageMat = ImageHelper.byteArrayToMat(selfie)
            if (ImageHelper.detectImageFaces(imageMat, directoryModels)) {
                throw ServiceException(ServiceError.INVALID_MORE_DETECT_ONE_FACE)
            }
        }

        // Verificar compatibilidade entre as faces nas imagens
        val imagesMat = request.selfies.map { ImageHelper.byteArrayToMat(it) }
        return ImageHelper.checkCompatibilityBetweenFace(imagesMat, directoryModels)
    }
}