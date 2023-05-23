package io.reds.personalvalidation.config.integration

import nu.pattern.OpenCV
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class OpenCVConfiguration {

    @PostConstruct
    fun loadOpenCV() {
        OpenCV.loadShared()
    }
}