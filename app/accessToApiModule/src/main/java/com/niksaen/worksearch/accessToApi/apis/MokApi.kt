package com.niksaen.worksearch.accessToApi.apis

import com.niksaen.worksearch.accessToApi.models.Response
import io.reactivex.Single
import retrofit2.http.GET

interface MokApi {
    @GET("/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    fun getResponse(): Single<Response>
}