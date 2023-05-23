package io.reds.personalvalidation.models.errors

import io.reds.personalvalidation.models.enum.ServiceError
import org.springframework.web.server.ResponseStatusException

class ServiceException(
    serviceError: ServiceError
): ResponseStatusException(serviceError.status, serviceError.message)