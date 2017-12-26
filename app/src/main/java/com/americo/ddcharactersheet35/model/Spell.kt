package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Americo on 29/04/2017.
 *
 * Entity of D&D Spells
 *
 */
@Entity(
        indices = [
            Index("name",unique = true)
        ]
)
class Spell {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    var id: Long = 0

    var name: String = ""
    var school: String = ""
    var components: String = ""
    var casting: String = ""
    var range: String = ""
    var area: String = ""
    var duration: String = ""
    var saving: String = ""
    var resistance: String = ""
    var description: String = ""
    var focus: String = ""
    var notes: String = ""

}
