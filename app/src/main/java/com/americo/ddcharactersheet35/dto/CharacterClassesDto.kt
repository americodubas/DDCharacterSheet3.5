package com.americo.ddcharactersheet35.dto

/**
 * Created by Americo on 18/08/2017.
 *
 * Dto to bring Character's Class data to the view
 *
 */
class CharacterClassesDto {

    var id: Long = 0
    lateinit  var character: CharacterDto
    lateinit var classes: ClassesDto
    var level: Int = 0

}