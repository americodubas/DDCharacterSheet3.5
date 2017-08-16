package com.americo.ddcharactersheet35.service

import android.content.Context
import android.util.Log
import com.americo.ddcharactersheet35.data.CharacterDao
import com.americo.ddcharactersheet35.exception.CannotDecreaseCharacterClassesLevel
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.model.Classes
import com.americo.ddcharactersheet35.model.Race
import java.util.TreeSet

/**
 * Created by Americo on 27/05/2017.
 */
class CharacterService(val context: Context) {

    private val characterDao = CharacterDao(context)

    fun getCharacter(id: String): Character {
        return characterDao.getCharacter(id.toInt())
    }

    fun getAllCharacters(): List<Character> {
        val chars = characterDao.getAllCharacters()
        Log.i("size", chars.size.toString())
        return chars
    }

    fun insertEmptyCharacter(): Int {
        //TODO create blank char
        var character = Character(name = "Hizagkuur", race = getDefaultRace())
        character = characterDao.insertCharacter(character)
        characterDao.insertCharacterClass(CharacterClasses(character, getDefaultClass(), 1))
        characterDao.insertCharacterClass(CharacterClasses(character, characterDao.getClasses(2), 2))
        characterDao.insertCharacterClass(CharacterClasses(character, characterDao.getClasses(3), 3))
        return character.id
    }

    private fun getDefaultRace() = characterDao.getRace(2)

    private fun getDefaultClass() = characterDao.getClasses(1)

    fun updateCharacter(character: Character) {
        characterDao.updateCharacter(character)
    }

    fun getAllRaces(): List<Race> {
        return characterDao.getAllRaces()
    }

    fun getAllClasses(): List<Classes> {
        return characterDao.getAllClasses()
    }

    fun getNotUsedClasses(character: Character): List<Classes> {
        val allClasses = getAllClasses()
        val usedClassesId = TreeSet<String>()
        character.characterClasses.forEach { usedClassesId.add(it.classes.id.toString()) }
        return allClasses.filter { !usedClassesId.contains(it.id.toString()) }
    }

    fun getCharacterClasses(id: String): List<Classes> {
        return characterDao.getCharacterClasses(id.toInt())
    }

    fun insertCharacterClasses(characterClasses: CharacterClasses){
        characterDao.insertCharacterClass(characterClasses)
    }

    fun deleteCharacterClasses(characterClasses: CharacterClasses){
        characterDao.deleteCharacterClass(characterClasses)
    }

    fun increaseCharacterClassesLevel(characterClasses: CharacterClasses) : CharacterClasses {
        characterClasses.level += 1
        characterDao.updateCharacterClass(characterClasses)
        return characterClasses
    }

    fun decreaseCharacterClassesLevel(characterClasses: CharacterClasses) : CharacterClasses {
        if (!canDecreaseLevel(characterClasses)) throw CannotDecreaseCharacterClassesLevel()
        characterClasses.level -= 1
        characterDao.updateCharacterClass(characterClasses)
        return characterClasses
    }

    private fun canDecreaseLevel(characterClasses: CharacterClasses) = characterClasses.level > 1

}