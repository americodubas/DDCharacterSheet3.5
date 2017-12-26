package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.americo.ddcharactersheet35.model.Classes

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [Classes].
 */
@Dao
interface ClassesDao {

    @Query("select * from classes where _id = :id")
    fun getClasses(id: Long): Classes

    @Query("select * from classes")
    fun getAllClasses(): List<Classes>

}