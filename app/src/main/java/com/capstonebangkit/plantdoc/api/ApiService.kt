package com.capstonebangkit.plantdoc.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): LoginResponse

    @Multipart
    @POST("history")
    suspend fun addScanPenyakit(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): AddPenyakitResponse

    @GET("pupuk")
    suspend fun allPupukTanaman(
        @Field("name") name: String,
        @Field("photoUrl") photoUrl: String,
        @Field("spesification") spesification: String,
        @Field("benefit") benefit: String
    ): AllPupukResponse

    @GET("news")
    suspend fun allBeritaTanaman(
        @Field("headline") headline: String,
        @Field("photoUrl") photoUrl: String,
        @Field("description") description: String
    ): AllBeritaResponse
}