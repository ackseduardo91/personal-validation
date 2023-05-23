package io.reds.personalvalidation.modules.selfiesValidation.service

import io.reds.personalvalidation.models.dtos.SelfieRequestDTO

interface SelfieService {
    fun validateSelfie(request: SelfieRequestDTO): Boolean
}