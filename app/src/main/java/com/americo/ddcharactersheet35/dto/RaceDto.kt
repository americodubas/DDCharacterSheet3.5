package com.americo.ddcharactersheet35.dto

/**
 * Created by Americo on 19/08/2017.
 *
 * Dto to bring Race data to the view.
 *
 */
class RaceDto {
    var id: Long = 0
    var name: String = ""
    var description: String = ""

    override fun toString(): String {
        return name
    }
}