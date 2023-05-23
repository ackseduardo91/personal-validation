package io.reds.personalvalidation.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class DocumentOcrValidateDTO(

    @JsonProperty("nameHolder")
    val nameHolder: String,

    @JsonProperty("rgNumber")
    val rgNumber: String,

    @JsonProperty("cpfNumber")
    val cpfNumber: String?,

    @JsonProperty("dateBirth")
    val dateBirth: LocalDate,

    @JsonProperty("keyWords")
    val keyWords: List<String> = emptyList()
)
