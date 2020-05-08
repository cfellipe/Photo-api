package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.Photo
import com.photoapi.photoapi.entity.dto.PhotoDTO
import com.photoapi.photoapi.entity.repository.PhotoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime


@Service
data class PhotoService(val s3Service: S3Service,
                        val photoRepository: PhotoRepository,
                        @Value("\${aws.s3.bucket}") val bucketName: String,
                        @Value("\${aws.s3.serverUrl}") val serverUrl: String) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PhotoService::class.java)
    }

    fun savePhoto(multipartFile: MultipartFile): PhotoDTO {
        val webPath = uploadFileToS3(multipartFile)
        val photo = Photo(webPath = webPath, name = multipartFile.originalFilename, createdDate = LocalDateTime.now())
        val savedPhoto = photoRepository.save(photo)
        return PhotoDTO.fromPhoto(savedPhoto)
    }

    private fun uploadFileToS3(multipartFile: MultipartFile): String {
        try {
            val file = s3Service.convertMultiPartToFile(multipartFile)
            val fileName: String = s3Service.generateFileName(multipartFile)
            s3Service.uploadFileTos3bucket(bucketName, fileName, file)
            file.delete()
            return bucketName + "." + serverUrl + "/" + fileName
        } catch (e: Exception) {
            LOGGER.error("there was a erro:", e.printStackTrace())
            throw AppException(AppError.UPLOAD_BAD_REQUEST)
        }

    }

}



