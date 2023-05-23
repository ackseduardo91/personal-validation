package io.reds.personalvalidation.models.enum

import org.springframework.http.HttpStatus

enum class ServiceError(
    val status: HttpStatus,
    val message: String)
{
    GENERIC_ERROR(HttpStatus.BAD_REQUEST, "generic.error"),
    INVALID_ACCESS(HttpStatus.FORBIDDEN, "invalid.access"),
    DOCUMENT_INVALID(HttpStatus.FORBIDDEN, "document.invalid.access"),
    INVALID_TYPE_FILE_FACE(HttpStatus.NOT_ACCEPTABLE, "invalid.type.file.face.error"),
    INVALID_NOT_COMPATIBLE_FACE(HttpStatus.NOT_ACCEPTABLE, "invalid.not.compatible.face.error"),
    INVALID_MORE_DETECT_ONE_FACE(HttpStatus.NOT_ACCEPTABLE, "invalid.more.detect.one.face.error");
}