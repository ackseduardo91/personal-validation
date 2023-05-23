package io.reds.personalvalidation.modules.documentValidation.service

import io.reds.personalvalidation.models.dtos.DocumentOcrValidateDTO
import io.reds.personalvalidation.models.dtos.DocumentRequestDTO
import io.reds.personalvalidation.models.enum.ServiceError
import io.reds.personalvalidation.models.errors.ServiceException
import io.reds.personalvalidation.models.helpers.DateHelper
import io.reds.personalvalidation.models.helpers.DocumentHelper
import org.springframework.stereotype.Service

@Service
class DocumentServiceImpl(): DocumentService {

    override fun validateDocument(request: DocumentRequestDTO): Boolean {
        val documentOcr = DocumentHelper.extractDataRG(request.imgDoc, "") ?: throw ServiceException(ServiceError.GENERIC_ERROR)
        return validateDocumentOcr(request, documentOcr)
    }

    private fun validateDocumentOcr(request: DocumentRequestDTO, documentOcr: DocumentOcrValidateDTO): Boolean {
        if(request.name != documentOcr.nameHolder){throw ServiceException(ServiceError.DOCUMENT_INVALID)}
        if(request.cpf != documentOcr.cpfNumber){throw ServiceException(ServiceError.DOCUMENT_INVALID)}
        if(!DateHelper.validateAgeMajority(documentOcr.dateBirth)){throw ServiceException(ServiceError.DOCUMENT_INVALID)}
        if(documentOcr.keyWords.size < 3){throw ServiceException(ServiceError.DOCUMENT_INVALID)}
        if(documentOcr.rgNumber.isEmpty() || documentOcr.rgNumber.isBlank()){throw ServiceException(ServiceError.DOCUMENT_INVALID)}
        return true
    }
}