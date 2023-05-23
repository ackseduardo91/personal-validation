package io.reds.personalvalidation.modules.validationService.controller

import io.reds.personalvalidation.models.dtos.CompareImagesRequestDTO
import io.reds.personalvalidation.models.dtos.DocumentRequestDTO
import io.reds.personalvalidation.models.dtos.SelfieRequestDTO
import io.reds.personalvalidation.modules.documentValidation.service.DocumentService
import io.reds.personalvalidation.modules.selfiesValidation.service.SelfieService
import io.reds.personalvalidation.modules.validationService.service.ValidationService
import jakarta.validation.Valid
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Validated
@RestController
@RequestMapping("/api/validation")
class ValidationController(
    private val validationService: ValidationService,
    private val selfieService: SelfieService,
    private val documentService: DocumentService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/auth", produces = ["application/json"])
    fun validateAuth(): Boolean = validationService.validateAuth()

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/selfie", produces = ["application/json"])
    fun validateSelfie(): Boolean {
        //@RequestBody @Valid request: SelfieRequestDTO
        val imgList = arrayListOf(
            ClassPathResource("/files/images/IMG_1.jpg").file.readBytes(),
            ClassPathResource("/files/images/IMG_2.jpg").file.readBytes(),
//            ClassPathResource("/files/documents/DOC_01.pdf").file.readBytes(),
//            ClassPathResource("/files/images/IMG_3.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_4.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_5.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_6.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_7.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_8.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_9.jpg").file.readBytes(),
//            ClassPathResource("/files/images/IMG_10.jpg").file.readBytes()

        )
        val request = SelfieRequestDTO(imgList)
        return selfieService.validateSelfie(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/document", produces = ["application/json"])
    fun validateDocument(@RequestBody @Valid request: DocumentRequestDTO): Boolean = documentService.validateDocument(request)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/images", produces = ["application/json"])
    fun validateImages(@RequestBody @Valid request: CompareImagesRequestDTO): Boolean = validationService.validateImages(request)

}