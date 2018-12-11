package com.pixelart.week6daily2flikrapi.remote

import com.pixelart.week6daily2flikrapi.common.PHOTO_INFO_RELATIVE_URL
import com.pixelart.week6daily2flikrapi.common.PHOTO_RELATIVE_URL
import com.pixelart.week6daily2flikrapi.model.PhotoResponse
import com.pixelart.week6daily2flikrapi.model.photoinfo.InfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(PHOTO_RELATIVE_URL)
    fun flikrSearch(
        @Query("api_key") apiKey : String,
        @Query("tags") searchByTag: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ):Single<PhotoResponse>

    @GET(PHOTO_INFO_RELATIVE_URL)
    fun getPhotoInof(
        @Query("api_key") apiKey : String,
        @Query("photo_id") photoId: String,
        @Query("secret") secret: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Single<InfoResponse>
}