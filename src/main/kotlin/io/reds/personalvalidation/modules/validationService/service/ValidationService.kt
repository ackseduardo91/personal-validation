package io.reds.personalvalidation.modules.validationService.service

import io.reds.personalvalidation.models.dtos.CompareImagesRequestDTO

interface ValidationService {
    fun validateAuth(): Boolean
    fun validateImages(request: CompareImagesRequestDTO): Boolean
}