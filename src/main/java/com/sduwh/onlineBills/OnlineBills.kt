package com.sduwh.onlineBills

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class OnlineBillsApplication

fun main(args: Array<String>) {
    SpringApplication.run(OnlineBillsApplication::class.java, *args)
}