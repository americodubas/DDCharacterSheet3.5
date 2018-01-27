package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

/**
 * Created by Americo on 26/01/2018.
 *
 * BaseDao to hold common methods
 */
interface BaseDao<in T> {

    @Insert
    fun insert(obj: T): Long

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}