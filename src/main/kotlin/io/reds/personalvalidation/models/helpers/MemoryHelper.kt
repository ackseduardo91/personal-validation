package io.reds.personalvalidation.models.helpers

import java.time.LocalTime

val MemoryManagement = MemoryHelper()

class MemoryHelper {

    fun showUsage(action: String? = null){
        val free = Runtime.getRuntime().freeMemory()
        val total = Runtime.getRuntime().totalMemory()
        val max = Runtime.getRuntime().maxMemory()
        val used = total - free
        System.out.printf("\t%s - %40s: %10dk of max %10dk%n", LocalTime.now().toString(), action, used/1024, max/1024)
    }

    fun forceGarbage() = System.gc()
}