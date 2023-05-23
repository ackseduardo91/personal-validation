package io.reds.personalvalidation.modules.documentValidation.service

import io.reds.personalvalidation.models.dtos.DocumentRequestDTO

interface DocumentService {
    fun validateDocument(request: DocumentRequestDTO): Boolean
}