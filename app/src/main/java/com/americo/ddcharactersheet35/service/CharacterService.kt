package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.dto.CharacterDto
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.util.convertFromTo

/**
 * Created by Americo on 27/05/2017.
 *
 * Service responsible for the [Character].
 */
class CharacterService(val context: Context) {

    private val characterDao = DatabaseHelper.getInstance(context).characterDao()

    /**
     * Returns a [CharacterDto] by [id].
     */
    fun getCharacter(id: String): CharacterDto {
        return convertFromTo(characterDao.getCharacter(id.toLong()))
    }

    /**
     * Returns a [List] of all [CharacterDto].
     */
    fun getAllCharacters(): List<CharacterDto> {
        return convertFromTo(characterDao.getAllCharacters())
    }

    /**
     * Insert an empty [Character] with default values.
     */
    fun insertEmptyCharacter(): Long {
        var character = Character()
        character.name = "Unknown"
//        character.race = RaceService(context).getDefaultRace()
        character.id = characterDao.insertCharacter(character)

        CharacterClassesService(context).insertDefaultCharacterClasses(character)
        return character.id
    }

    /**
     * Update the [CharacterDto].
     */
    fun updateCharacter(characterDto: CharacterDto) {
        characterDao.updateCharacter(convertFromTo(characterDto))
    }

}