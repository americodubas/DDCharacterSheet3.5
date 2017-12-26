package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.americo.ddcharactersheet35.model.Race

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [Race]
 */
@Dao
interface RaceDao {

    @Query("select * from race where _id = :id")
    fun getRace(id: Long): Race

    @Query("select * from race")
    fun getAllRaces(): List<Race>

}