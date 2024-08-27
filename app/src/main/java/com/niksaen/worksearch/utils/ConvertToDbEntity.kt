package com.niksaen.worksearch.utils

import com.niksaen.worksearch.localdatamanager.entities.Address
import com.niksaen.worksearch.localdatamanager.entities.Experience
import com.niksaen.worksearch.localdatamanager.entities.Salary
import com.niksaen.worksearch.localdatamanager.entities.VacancyDbEntity
import com.niksaen.worksearch.accessToApi.models.Vacancy

class ConvertDbEntity {
    companion object{
        /**
         * Convert [Vacancy] to [VacancyDbEntity]
         */
        fun fromNetworkVacancy(vacancy: Vacancy): VacancyDbEntity{
            val dbEntityAddress = Address(
                vacancy.address.town,
                vacancy.address.street,
                vacancy.address.house
            )

            val dbEntityExperience = Experience(
                vacancy.experience.text,
                vacancy.experience.previewText
            )

            val dbEntitySalary = Salary(vacancy.salary.full)

            val dbEntity = VacancyDbEntity(
                dbEntityAddress,
                vacancy.questions,
                vacancy.description,
                vacancy.title,
                dbEntityExperience,
                dbEntitySalary,
                vacancy.responsibilities,
                vacancy.lookingNumber,
                vacancy.schedules,
                vacancy.company,
                vacancy.appliedNumber,
                vacancy.id,
                vacancy.publishedDate,
                vacancy.isFavorite
            )

            return dbEntity
        }
        /**
         * Convert [VacancyDbEntity] to [Vacancy]
         */
        fun toNetworkVacancy(vacancyDbEntity: VacancyDbEntity):Vacancy{
            val address = com.niksaen.worksearch.accessToApi.models.Address(
                vacancyDbEntity.address.town,
                vacancyDbEntity.address.street,
                vacancyDbEntity.address.house
            )

            val experience = com.niksaen.worksearch.accessToApi.models.Experience(
                vacancyDbEntity.experience.text,
                vacancyDbEntity.experience.previewText
            )

            val salary = com.niksaen.worksearch.accessToApi.models.Salary(vacancyDbEntity.salary.full)

            val dbEntity = Vacancy(
                address,
                vacancyDbEntity.questions,
                vacancyDbEntity.description.toString(),
                vacancyDbEntity.title,
                experience,
                salary,
                vacancyDbEntity.responsibilities,
                vacancyDbEntity.lookingNumber,
                vacancyDbEntity.schedules,
                vacancyDbEntity.company,
                vacancyDbEntity.appliedNumber,
                vacancyDbEntity.id,
                vacancyDbEntity.publishedDate,
                vacancyDbEntity.isFavorite
            )

            return dbEntity
        }
    }
}