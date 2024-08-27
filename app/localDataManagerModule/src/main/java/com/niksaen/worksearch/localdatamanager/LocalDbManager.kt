package com.niksaen.worksearch.localdatamanager

import android.content.Context
import androidx.room.Room
import com.niksaen.worksearch.localdatamanager.db.LocalDatabase
import com.niksaen.worksearch.localdatamanager.entities.VacancyDbEntity

/**
 * Local database manager
 * Using for save data in local database*/
class LocalDbManager(applicationContext: Context) {

    private val localDatabase:LocalDatabase = Room.databaseBuilder(
        applicationContext,
        LocalDatabase::class.java, "localDatabase")
        .allowMainThreadQueries()
        .build()

    private val vacanciesDao = localDatabase.vacanciesDao()

    fun getAll() = vacanciesDao.getAll()
    fun addVacancy(vacancyDbEntity: VacancyDbEntity) = vacanciesDao.addVacancy(vacancyDbEntity)
    fun deleteVacancy(vacancyDbEntity: VacancyDbEntity) = vacanciesDao.deleteVacancy(vacancyDbEntity)
}