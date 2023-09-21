package com.vanka.skillstar.clubs.postUpload

interface ImageUploadCallback {
    fun onSuccess(imageUrl: String)
    fun onFailure(errorMessage: String)
}