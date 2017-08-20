package com.americo.ddcharactersheet35.dto

/**
 * Created by Americo on 19/08/2017.
 *
 * Dto to bring Class data to the view.
 *
 */
class ClassDto {
    var id: Int = 0
    var name: String = ""
    var spellcaster: Int = 0

    override fun toString(): String {
        return name
    }
}