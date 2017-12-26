package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.data.RaceDao
import com.americo.ddcharactersheet35.dto.RaceDto
import com.americo.ddcharactersheet35.model.Race
import com.americo.ddcharactersheet35.util.convertFromTo

/**
 * Created by Americo on 20/08/2017.
 *
 * Service responsible for the [Race].
 */
class RaceService(context: Context) {

    private val raceDao = DatabaseHelper.getInstance(context).raceDao()

    /**
     * Returns the default [Race].
     */
    fun getDefaultRace() = raceDao.getRace(2)

    /**
     * Returns a [List] of all [RaceDto].
     */
    fun getAllRaces(): List<RaceDto> {
        return convertFromTo(raceDao.getAllRaces())
    }
}