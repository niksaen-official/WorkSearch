package com.niksaen.worksearch.accessToApi.models

data class Offer(
    val id:String?,
    val title:String,
    val button: HashMap<String,String>? = null,
    val link:String,
)
