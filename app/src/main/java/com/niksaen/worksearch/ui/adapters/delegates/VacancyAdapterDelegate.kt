package com.niksaen.worksearch.ui.adapters.delegates

import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.niksaen.worksearch.R
import com.niksaen.worksearch.accessToApi.models.Vacancy
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.VacancyModel
import com.niksaen.worksearch.utils.MonthMap

fun vacancyAdapterDelegate(
    onItemClick: (vacancy:Vacancy)->Unit = {},
    onBtnItemClick: ()->Unit = {},
    isFavoriteClick: (vacancy:Vacancy)->Unit = {}) = adapterDelegate<VacancyModel,BaseModel>(R.layout.item_vacancy){
    val lookingNumber = findViewById<TextView>(R.id.lookingNumber)
    val title = findViewById<TextView>(R.id.title)
    val town = findViewById<TextView>(R.id.town)
    val company = findViewById<TextView>(R.id.company)
    val experience = findViewById<TextView>(R.id.experienceView)
    val publishedDate = findViewById<TextView>(R.id.textView13)
    val isFavorite = findViewById<CheckBox>(R.id.imageView3)
    val button = findViewById<Button>(R.id.button3)
    bind {
        lookingNumber.text = "Сейчас просматривают ${item.vacancy.lookingNumber}"
        val count = item.vacancy.lookingNumber.toString()
        if(count.endsWith("2") || count.endsWith("3") || count.endsWith("4"))
            lookingNumber.text = lookingNumber.text.toString() + " человека"
        else
            lookingNumber.text = lookingNumber.text.toString() + " человек"

        title.text = item.vacancy.title
        town.text = item.vacancy.address.town
        company.text = item.vacancy.company
        experience.text = item.vacancy.experience.previewText

        val data = item.vacancy.publishedDate.split("-")
        val day = data[2].toInt()
        val month = data[1].toInt()

        publishedDate.text = "Опубликовано $day ${MonthMap.getMonth(month)}"
        isFavorite.isChecked = item.vacancy.isFavorite

        isFavorite.setOnClickListener {
            item.vacancy.isFavorite = !item.vacancy.isFavorite
            isFavoriteClick(item.vacancy)
        }
        itemView.setOnClickListener {
            onItemClick(item.vacancy)
        }
        button.setOnClickListener {
            onBtnItemClick()
        }
    }
}