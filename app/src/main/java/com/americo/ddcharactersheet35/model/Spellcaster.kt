package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Americo on 29/04/2017.
 *
 * Entity of D&D spell casters
 *
 */
@Entity(
        foreignKeys = [
            (ForeignKey(entity = Classes::class,
                    parentColumns = ["_id"],
                    childColumns = ["classes_id"]))
        ]
)
class Spellcaster {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    var id: Long = 0

    @ColumnInfo(name = "classes_id")
    var classesID: Int = 0

    var circle: Int = 0
}
