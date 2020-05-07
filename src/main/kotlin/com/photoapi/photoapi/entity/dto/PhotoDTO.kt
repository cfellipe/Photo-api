package com.photoapi.photoapi.entity.dto


import com.fasterxml.jackson.annotation.JsonInclude
import com.photoapi.photoapi.entity.Album
import com.photoapi.photoapi.entity.BaseEntity
import com.photoapi.photoapi.entity.Photo
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.ManyToOne

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PhotoDTO(
        val webPath: String,
        val name: String? = null,
        val description: String? = null,
        val album: Album? = null
) {
    companion object {
        fun fromPhoto(photo: Photo) = PhotoDTO(
                webPath = photo.webPath,
                name = photo.name,
                description = photo.description,
                album = photo.album)
    }
}