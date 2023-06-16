package com.capstonebangkit.plantdoc.data

data class Pupuk(
    val id: Int,
    val photoUrl: String,
    val name: String,
    val specification: String,
    val benefit: String
){
    fun getFormattedSpecification(): String {
        return "- ${specification.replace("\n", "\n- ")}"
    }

    fun getFormattedBenefit(): String {
        return "★ ${benefit.replace("\n", "\n★ ")}"
    }
}