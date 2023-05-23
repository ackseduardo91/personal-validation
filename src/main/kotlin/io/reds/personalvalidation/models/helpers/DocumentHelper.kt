package io.reds.personalvalidation.models.helpers

import io.reds.personalvalidation.models.dtos.DocumentOcrValidateDTO
import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.util.regex.Matcher
import java.util.regex.Pattern

class DocumentHelper {
    companion object{

        fun extractDataRG(imgDoc: ByteArray, filePath: String): DocumentOcrValidateDTO? {
            val tesseract = Tesseract()
            tesseract.setDatapath("") //TODO: Defina o caminho para o diretório contendo os arquivos de treinamento do Tesseract

            // Defina o idioma do documento que você deseja validar
            tesseract.setLanguage("pt-BR")

            try {
                // Realize a OCR na imagem do documento
                val ocrResult  = tesseract.doOCR(byteArrayToFile(imgDoc, filePath)).split("\n")

                // Realize a extraçao dos dados da OCR
                return makeDocumentOcrValidateDTO(ocrResult)
            } catch (e: TesseractException) {
                // Lida com exceções que podem ocorrer durante o processo de OCR
                e.printStackTrace()
            }
            return null
        }

        private fun makeDocumentOcrValidateDTO(ocrResult: List<String>) =
            DocumentOcrValidateDTO(
                getNameHolderOrc(ocrResult),
                getRgNumberOcr(ocrResult),
                formatCPF(getCpfOcr(ocrResult)),
                LocalDate.parse(getDateBirthOcr(ocrResult)),
                getKeyWorkSearching(ocrResult)
            )

        private fun getDateBirthOcr(ocrResult: List<String>): String {
            println(ocrResult)
            return ""
        }

        private fun getNameHolderOrc(ocrResult: List<String>): String {
            println(ocrResult)
            return ""
        }

        private fun getRgNumberOcr(ocrResult: List<String>): String {
            println(ocrResult)
            return ""
        }

        private fun getCpfOcr(ocrResult: List<String>): String? {
            println(ocrResult)
            return null
        }

        private fun getKeyWorkSearching(ocrResult: List<String>): List<String> {
            val wordOne = "VÁLIDA EM TODO O TERRITÓRIO NACIONAL"
            val wordTwo = "LEI N\" 7.116 DE 29/08/83"
            val wordThree = "REPÚBLICA FEDERATIVA DO BRASIL"
            return ocrResult.filter { arrayListOf(wordOne, wordTwo, wordThree).toList().contains(it) }
        }

        private fun formatCPF(cpf: String?): String?{
            if(cpf != null){
                val pattern: Pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
                val matcher: Matcher = pattern.matcher(cpf)

                return if (matcher.find()) {
                    matcher.replaceAll("$1.$2.$3-$4")
                } else cpf
            }
            return null
        }

        private fun byteArrayToFile(byteArray: ByteArray, filePath: String): File {
            val file = File(filePath)
            val outputStream = FileOutputStream(file)
            outputStream.write(byteArray)
            outputStream.close()
            return file
        }

    }
}
