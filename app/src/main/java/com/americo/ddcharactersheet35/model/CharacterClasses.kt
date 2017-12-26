package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Americo on 21/06/2017.
 *
 * Entity of the Character's Class
 *
 */
@Entity(
        tableName = "character_classes",
        foreignKeys = [
            ForeignKey(entity = Character::class,
                    parentColumns = ["_id"],
                    childColumns = ["character_id"]),
            ForeignKey(entity = Classes::class,
                    parentColumns = ["_id"],
                    childColumns = ["classes_id"])
        ]
)
class CharacterClasses {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    var id: Long = 0

    @ColumnInfo(name = "character_id")
    var characterId: Int = 0

    @ColumnInfo(name = "classes_id")
    var classesId: Int = 0

    var level: Int = 0

}