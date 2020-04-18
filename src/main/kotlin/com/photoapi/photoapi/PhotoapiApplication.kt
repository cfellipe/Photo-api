package com.photoapi.photoapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhotoapiApplication

fun main(args: Array<String>) {
	runApplication<PhotoapiApplication>(*args)
}
