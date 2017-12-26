package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.*
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.model.Classes

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [CharacterClasses].
 */
@Dao
interface CharacterClassesDao {

    @Query("select * " +
            "from character_classes cc " +
            "join classes c on  cc.classes_id = c._id " +
            "where character_id = :id")
    fun getCharacterClasses(id: Long): List<Classes>

    @Insert
    fun insertCharacterClass(characterClasses: CharacterClasses): Long

    @Delete
    fun deleteCharacterClass(characterClasses: CharacterClasses)

    @Update
    fun updateCharacterClass(characterClasses: CharacterClasses)

}