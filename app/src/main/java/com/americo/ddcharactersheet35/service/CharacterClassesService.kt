package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.dto.CharacterClassesDto
import com.americo.ddcharactersheet35.dto.ClassesDto
import com.americo.ddcharactersheet35.exception.CannotDecreaseCharacterClassesLevel
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.util.convert

/**
 * Created by Americo on 20/08/2017.
 *
 * Service responsible for the [CharacterClasses].
 */
class CharacterClassesService(val context: Context) {

    private val characterClassesDao = DatabaseHelper.getInstance(context).characterClassesDao()
    private val defaultClassesId: Long = 1
    private val defaultLevel = 1

    /**
     * Insert the default [CharacterClasses] to the [Character]
     */
    fun insertDefault(character: Character) {
        val characterClasses = CharacterClasses()
        with(characterClasses){
            characterId = character.id
            classesId = defaultClassesId
            level = defaultLevel
        }

        characterClassesDao.insert(characterClasses)
    }

    /**
     * Returns a [List] of [CharacterClassesDto] of the [Character] by it's [id].
     */
    fun get(id: String): List<CharacterClassesDto> {
        return characterClassesDao.getCharacterClasses(id.toLong())
    }

    /**
     * Insert the relation of [CharacterClassesDto].
     */
    fun insert(characterClassesDto: CharacterClassesDto){
        characterClassesDao.insert(convert(characterClassesDto))

        CharacterService(context).updateLevel(
                characterClassesDto.characterId,
                characterClassesDto.level)
    }

    /**
     * Delete the relation of [CharacterClassesDto].
     */
    fun delete(characterClassesDto: CharacterClassesDto){
        characterClassesDao.delete(convert(characterClassesDto))
    }

    /**
     * Increase the level of the [CharacterClassesDto] by + 1.
     */
    fun increaseLevel(characterClassDto: CharacterClassesDto) : CharacterClassesDto {
        characterClassDto.level += 1
        characterClassesDao.update(convert(characterClassDto))
        return characterClassDto
    }

    /**
     * Decrease the level of the [CharacterClassesDto] by - 1.
     * The level won't be decreased if the [CharacterClassesDto] level is 1,
     * a [CannotDecreaseCharacterClassesLevel] will be throw.
     */
    fun decreaseLevel(characterClassDto: CharacterClassesDto) : CharacterClassesDto {
        if (!canDecreaseLevel(characterClassDto)) throw CannotDecreaseCharacterClassesLevel()
        characterClassDto.level -= 1
        characterClassesDao.update(convert(characterClassDto))
        return characterClassDto
    }

    /**
     * Verify if the [CharacterClassesDto] can be decreased, the minimum level is 1.
     */
    private fun canDecreaseLevel(characterClassDto: CharacterClassesDto) = characterClassDto.level > 1

    /**
     * Returns a [List] of the [ClassesDto] not used by the character's id.
     */
    fun getNotUsedClasses(id: String): List<ClassesDto> {
        return convert(characterClassesDao.getNotUsedClasses(id.toLong()))
    }

}