package com.niksaen.worksearch.accessToApi.models

data class Response(
    val offers: ArrayList<Offer>,
    val vacancies:ArrayList<Vacancy>
)