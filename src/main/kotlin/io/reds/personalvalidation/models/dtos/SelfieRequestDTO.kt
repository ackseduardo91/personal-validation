package io.reds.personalvalidation.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class SelfieRequestDTO(
    @JsonProperty("selfies")
    @field:NotBlank(message = "selfies.required")
    var selfies: MutableList<ByteArray>
)
