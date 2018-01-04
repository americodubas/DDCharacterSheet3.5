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
        select cl._id, cl.name
        from classes cl
        left join character_classes ch on ch.classes_id = cl._id and ch.character_id = :id
        where ch._id is null
        """)
    fun getNotUsedClasses(id: Long): List<Classes>

    @Insert
    fun insertCharacterClasses(characterClasses: CharacterClasses): Long

    @Delete
    fun deleteCharacterClasses(characterClasses: CharacterClasses)

    @Update
    fun updateCharacterClasses(characterClasses: CharacterClasses)

}