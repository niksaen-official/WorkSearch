package com.niksaen.worksearch.localdatamanager.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.niksaen.worksearch.localdatamanager.entities.VacancyDbEntity

@Dao
interface VacanciesDao {
    @Query("SELECT * FROM vacancies")
    fun getAll():List<VacancyDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVacancy(vararg vacancy: VacancyDbEntity)

    @Delete
    fun deleteVacancy(vacancy: VacancyDbEntity)
}