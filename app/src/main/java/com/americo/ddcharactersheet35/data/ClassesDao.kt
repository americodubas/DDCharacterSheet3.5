package com.americo.ddcharactersheet35.data

import android.content.Context
import com.americo.ddcharactersheet35.model.Classes

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [Classes].
 */
class ClassesDao(context: Context) {

    private val db = DatabaseHelper(context)

    /**
     * Returns a [Classes] by id
     */
    fun  getClasses(id: Int): Classes {
        return db.getClassesDao().queryForId(id)
    }

    /**
     * Returns a [List] of all [Classes]
     */
    fun getAllClasses(): List<Classes> {
        return db.getClassesDao().queryForAll()
    }
}