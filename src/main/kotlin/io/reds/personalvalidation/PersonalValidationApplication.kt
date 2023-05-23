package io.reds.personalvalidation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonalValidationApplication

fun main(args: Array<String>) {
	runApplication<PersonalValidationApplication>(*args)
}
