package com.niksaen.worksearch.ui.adapters.delegates

import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.niksaen.worksearch.R
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.VacanciesCountListHeaderModel

val favoriteVacanciesHeaderAdapterDelegate = adapterDelegate<VacanciesCountListHeaderModel, BaseModel>(R.layout.item_favorite_vacancies_header){
    val vacanciesCountView = findViewById<TextView>(R.id.vacanciesCount)

    bind {
        payloads -> vacanciesCountView.text = item.vacanciesCountText
    }
}