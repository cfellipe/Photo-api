package com.photoapi.photoapi.config.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Service
data class S3Service(val s3client: AmazonS3) {

    fun uploadFileTos3bucket(bucketName: String, fileName: String, file: File) {
        s3client.putObject(PutObjectRequest(bucketName, fileName, file))
    }

    fun convertMultiPartToFile(file: MultipartFile): File {
        val convFile = File(file.originalFilename)
        val fos = FileOutputStream(convFile)
        fos.write(file.bytes)
        fos.close()
        return convFile
    }

     fun generateFileName(multiPart: MultipartFile): String {
        return Date().getTime().toString() + "-" + multiPart.originalFilename?.replace(" ", "_")
    }
}