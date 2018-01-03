package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.*
import com.americo.ddcharactersheet35.dto.CharacterClassesDto
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.model.Classes

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [CharacterClasses].
 */
@Dao
interface CharacterClassesDao {

    @Query("""
        select ch._id id, ch.character_id characterId, ch.classes_id classesId,
        ch.level level, cl.name classesName
        from character_classes ch
        join classes cl on ch.classes_id = cl._id
        where ch.character_id = :id
        """)
    fun getCharacterClasses(id: Long): List<CharacterClassesDto>

    @Query("""
        select *
        from character_classes ch
        join classes cl on ch.classes_id = cl._id
        where ch.character_id = :id
        """)
    fun getUsedClasses(id: Long): List<Classes>

    @Insert
    fun insertCharacterClass(characterClasses: CharacterClasses): Long

    @Delete
    fun deleteCharacterClass(characterClasses: CharacterClasses)

    @Update
    fun updateCharacterClass(characterClasses: CharacterClasses)

}