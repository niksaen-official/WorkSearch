package com.niksaen.worksearch.models

import com.niksaen.worksearch.accessToApi.models.Vacancy
/**
 * Model for presentation vacancies in a RecyclerView
 * */
data class VacancyModel(
    val vacancy: Vacancy
):BaseModel