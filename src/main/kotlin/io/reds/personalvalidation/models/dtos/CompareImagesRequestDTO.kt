package io.reds.personalvalidation.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

class CompareImagesRequestDTO(
    @JsonProperty("imageSelfie")
    @field:NotBlank(message = "imageSelfie.required")
    val imageSelfie: ByteArray,

    @JsonProperty("frontImageRg")
    @field:NotBlank(message = "frontImageRg.required")
    val frontImageRg: ByteArray
)
