package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.ClassesDao
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.dto.ClassesDto
import com.americo.ddcharactersheet35.model.Classes
import com.americo.ddcharactersheet35.util.convertFromTo

/**
 * Created by Americo on 20/08/2017.
 *
 * Service responsible for the [Classes].
 */
class ClassesService(context: Context) {

    private val classesDao = DatabaseHelper.getInstance(context).classesDao()

    /**
     * Returns a [List] of all [ClassesDto].
     */
    fun getAllClasses(): List<ClassesDto> {
        return convertFromTo(classesDao.getAllClasses())
    }


}