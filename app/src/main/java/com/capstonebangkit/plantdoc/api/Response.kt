package com.capstonebangkit.plantdoc.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class LoginResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("loginResult")
    val loginResult: User
)

data class User(
    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("token")
    val token: String
)

data class RegisterResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class PenyakitResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("photoUrl")
    val photoUrl: String
)

data class AddPenyakitResponse(
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,
)

data class AllPenyakitResponse(
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("listPenyakit")
    val listPenyakit: List<ListPenyakit>
)

@Parcelize
data class ListPenyakit(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("photoUrl")
    val photoUrl: String,

    @SerializedName("createdAt")
    val createdAt: String
) : Parcelable

data class AllPupukResponse(
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("listPupuk")
    val listPupuk: List<ListPupuk>
)

@Parcelize
data class ListPupuk(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("photoUrl")
    val photoUrl: String,

    @SerializedName("spesification")
    val spesification: String,

    @SerializedName("benefit")
    val benefit: String
) : Parcelable

data class AllBeritaResponse(
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("listBerita")
    val listBerita: List<ListBerita>
)

@Parcelize
data class ListBerita(
    @SerializedName("id")
    val id: Int,

    @SerializedName("headline")
    val headline: String,

    @SerializedName("photoUrl")
    val photoUrl: String,

    @SerializedName("description")
    val description: String
) : Parcelable