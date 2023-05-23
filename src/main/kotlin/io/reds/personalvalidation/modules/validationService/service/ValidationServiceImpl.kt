package io.reds.personalvalidation.modules.validationService.service

import io.reds.personalvalidation.models.dtos.CompareImagesRequestDTO
import io.reds.personalvalidation.models.enum.ServiceError
import io.reds.personalvalidation.models.errors.ServiceException
import io.reds.personalvalidation.models.helpers.ImageHelper
import io.reds.personalvalidation.modules.validationService.provider.ValidationProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ValidationServiceImpl: ValidationService {
    @Value("\${directory.files.models}")
    lateinit var directoryModels: String

    override fun validateAuth(): Boolean {
        if (false){throw ServiceException(ServiceError.INVALID_ACCESS)} //TODO: Implementar validacao da chave do cliente para acesso a API
        return true
    }

    override fun validateImages(request: CompareImagesRequestDTO): Boolean {
        val imageSelfieMat = ImageHelper.byteArrayToMat(request.imageSelfie)
        val frontImageRgMat = ImageHelper.byteArrayToMat(request.frontImageRg)
        return ImageHelper.checkCompatibilityBetweenFace(arrayListOf(imageSelfieMat, frontImageRgMat))
    }
}