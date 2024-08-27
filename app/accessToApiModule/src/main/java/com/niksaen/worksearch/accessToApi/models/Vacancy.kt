package com.niksaen.worksearch.accessToApi.models

data class Vacancy(val address: Address,
                    val questions: List<String>?,
                    val description: String? = "",
                    val title: String = "",
                    val experience: Experience,
                    val salary: Salary,
                    val responsibilities: String = "",
                    val lookingNumber: Int = 0,
                    val schedules: List<String>?,
                    val company: String = "",
                    val appliedNumber: Int = 0,
                    val id: String = "",
                    val publishedDate: String = "",
                    var isFavorite: Boolean = false)


data class Experience(val text: String = "",
                      val previewText: String = "")

data class Salary(val full: String = "")

data class Address(val town: String = "",
                   val street: String = "",
                   val house: String = "")


