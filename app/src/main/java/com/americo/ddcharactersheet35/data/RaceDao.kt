package com.americo.ddcharactersheet35.data

import android.content.Context
import com.americo.ddcharactersheet35.model.Race

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [Race]
 */
class RaceDao(context: Context) {

    private val db = DatabaseHelper(context)

    /**
     * Returns a [Race] by id
     */
    fun  getRace(id: Int): Race {
        return db.getRaceDao().queryForId(id)
    }

    /**
     * Returns a [List] of all [Race].
     */
    fun getAllRaces(): List<Race> {
        return db.getRaceDao().queryForAll()
    }

}