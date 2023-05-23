package io.reds.personalvalidation.models.helpers

import java.time.LocalDate

class DateHelper {
    companion object{

        fun validateAgeMajority(dateBirth: LocalDate): Boolean{
            val ageMajority = 18
            val dateNow = LocalDate.now()
            return (dateNow.year - dateBirth.year) >= ageMajority
        }
    }
}