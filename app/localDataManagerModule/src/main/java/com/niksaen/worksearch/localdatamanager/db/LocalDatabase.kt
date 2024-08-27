package com.niksaen.worksearch.localdatamanager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niksaen.worksearch.localdatamanager.dao.VacanciesDao
import com.niksaen.worksearch.localdatamanager.entities.VacancyDbEntity

@Database(entities = [VacancyDbEntity::class],version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun vacanciesDao(): VacanciesDao
}