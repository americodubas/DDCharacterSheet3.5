package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

/**
 * Created by Americo on 29/04/2017.
 *
 * Entity of the spell's level
 *
 */

@Entity(
        tableName = "spell_level",
        foreignKeys = [
            ForeignKey(entity = Spell::class,
                    parentColumns = ["_id"],
                    childColumns = ["spell_id"]),
            ForeignKey(entity = Spellcaster::class,
                    parentColumns = ["_id"],
                    childColumns = ["spellcaster_id"])
        ],
        primaryKeys = ["spell_id","spellcaster_id"]

)
class SpellLevel {

    @ColumnInfo(name = "spell_id")
    var spellId: Int = 0
    @ColumnInfo(name = "spellcaster_id")
    var spellcasterId: Int = 0

}
