package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.dto.RaceDto
import com.americo.ddcharactersheet35.model.Race
import com.americo.ddcharactersheet35.util.convert

/**
 * Created by Americo on 20/08/2017.
 *
 * Service responsible for the [Race].
 */
class RaceService(context: Context) {

    private val raceDao = DatabaseHelper.getInstance(context).raceDao()

    /**
     * Returns a [List] of all [RaceDto].
     */
    fun getAll(): List<RaceDto> {
        return convert(raceDao.getAllRaces())
    }

    /**
     * Returns a [RaceDto] by the id
     */
    fun get(id: Long): RaceDto = convert(raceDao.getRace(id))

}