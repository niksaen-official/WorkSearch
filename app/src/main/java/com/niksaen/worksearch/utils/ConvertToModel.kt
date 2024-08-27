package com.niksaen.worksearch.utils

import com.niksaen.worksearch.accessToApi.models.Vacancy
import com.niksaen.worksearch.models.VacancyModel

class ConvertToListVacancyModel {
    companion object{
        /**
         * Convert [ArrayList]<[Vacancy]> to [ArrayList]<[VacancyModel]>
         */
        fun convert(arrayList: ArrayList<Vacancy>):ArrayList<VacancyModel>{
            val result = ArrayList<VacancyModel>()
            for(vacancy in arrayList){
                result.add(VacancyModel(vacancy))
            }
            return result
        }
    }
}