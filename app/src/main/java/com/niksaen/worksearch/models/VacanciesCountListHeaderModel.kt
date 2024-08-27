package com.niksaen.worksearch.models

/**
 * Model for presentation vacancies count in a RecyclerView*/
data class VacanciesCountListHeaderModel(private val vacanciesCount:Int) : BaseModel{
    val vacanciesCountText =
        when(vacanciesCount.toString().last()){
            '1' -> "1 вакансия"
            '2','3','4' -> "$vacanciesCount вакансии"
            else -> "$vacanciesCount вакансий"
        }
}