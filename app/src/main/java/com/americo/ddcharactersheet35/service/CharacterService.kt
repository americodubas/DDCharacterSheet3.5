package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.dto.CharacterDto
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.util.convert
import com.americo.ddcharactersheet35.util.portraits

/**
 * Created by Americo on 27/05/2017.
 *
 * Service responsible for the [Character].
 */
class CharacterService(val context: Context) {

    private val characterDao = DatabaseHelper.getInstance(context).characterDao()
    private val defaultRaceId: Long = 2
    private val defaultName = "Unknown"
    private val defaultLevel = 1
    private val defaultPortrait = portraits[0]

    /**
     * Update the [Character] level by the [amount] passed.
     */
    fun updateCharacterLevel(id: Long, amount: Int) {
        val character = characterDao.getCharacter(id)
        character.level += amount
        characterDao.update(character)
    }

    /**
     * Returns a [CharacterDto] by [id].
     */
    fun getCharacter(id: String): CharacterDto {
        return convert(characterDao.getCharacter(id.toLong()))
    }

    /**
     * Returns a [List] of all [CharacterDto].
     */
    fun getAllCharacters(): List<CharacterDto> {
        return convert(characterDao.getAllCharacters())
    }

    /**
     * Insert an empty [Character] with default values.
     */
    fun insertEmptyCharacter(): Long {
        val character = Character()
        with(character) {
            name = defaultName
            raceId = defaultRaceId
            level = defaultLevel
            portrait = defaultPortrait
        }

        character.id = characterDao.insert(character)

        CharacterClassesService(context).insertDefaultCharacterClasses(character)
        return character.id
    }

    /**
     * Update the [CharacterDto].
     */
    fun updateCharacter(characterDto: CharacterDto) {
        characterDao.update(convert(characterDto))
    }

}