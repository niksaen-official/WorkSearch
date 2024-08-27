package com.niksaen.worksearch.localdatamanager.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.niksaen.worksearch.localdatamanager.converters.ListConverter

@Entity("vacancies")
@TypeConverters(ListConverter::class)
data class VacancyDbEntity(
    @Embedded("address") val address: Address,
    val questions: List<String>?,
    val description: String? = "",
    val title: String = "",
    @Embedded("experience")val experience: Experience,
    @Embedded("salary") val salary: Salary,
    val responsibilities: String = "",
    val lookingNumber: Int = 0,
    val schedules: List<String>?,
    val company: String = "",
    val appliedNumber: Int = 0,
    @PrimaryKey val id: String = "",
    val publishedDate: String = "",
    var isFavorite: Boolean = false)


data class Experience(
    val text: String = "",
    val previewText: String = "")

data class Salary(val full: String = "")

data class Address(
    val town: String = "",
    val street: String = "",
    val house: String = "")