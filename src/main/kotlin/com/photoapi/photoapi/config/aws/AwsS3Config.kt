package com.photoapi.photoapi.config.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
data class AWSS3Config(@Value("\${aws.s3.accessKey}") val accessKey: String,
                       @Value("\${aws.s3.secretKey}") val secretKey: String,
                       @Value("\${aws.s3.region}") val region: String){
    @Bean
    fun getAmazonS3Cient(): AmazonS3 {
        val basicAWSCredentials = BasicAWSCredentials(accessKey, secretKey)
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials))
                .build()
    }
}