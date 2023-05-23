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
        if(request.selfies.any { ImageHelper.isImageFile(it).not() }){ throw ServiceException(ServiceError.INVALID_TYPE_FILE_FACE) }
        val imagesMat = request.selfies.map { ImageHelper.byteArrayToMat(it) }
//        imagesMat.forEach {
//            if (ImageHelper.detectImageFaces(it, directoryModels)){throw ServiceException(ServiceError.INVALID_MORE_DETECT_ONE_FACE)}
//        }
        return ImageHelper.checkCompatibilityBetweenFace(imagesMat, directoryModels)
    }
}