package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.americo.ddcharactersheet35.model.Spell

/**
 * Created by Americo on 02/05/2017.
 *
 * Dao for table spell
 *
 */
@Dao
interface SpellDao {

    @Query("select * from spell where _id = :id")
    fun getSpellById(id: Long): Spell

}