package io.reds.personalvalidation.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

class DocumentRequestDTO(
    @JsonProperty("imgDoc")
    @field:NotBlank(message = "document.required")
    val imgDoc: ByteArray,

    @JsonProperty("name")
    @field:NotBlank(message = "name.required")
    val name: String,

    @JsonProperty("cpf")
    @field:NotBlank(message = "cpf.required")
    val cpf: String
)
