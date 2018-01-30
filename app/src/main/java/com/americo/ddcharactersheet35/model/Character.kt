package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.*

/**
 * Created by Americo on 29/04/2017.
 *
 * Entity of the character
 *
 */
@Entity(
        foreignKeys = [
            ForeignKey(entity = Race::class,
                parentColumns = ["_id"],
                childColumns = ["race_id"])
        ]
)
class Character {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "race_id")
    var raceId: Long = 0

    @ColumnInfo(name = "full_hp")
    var fullHp: Int = 0

    var age: Int = 0
    var level: Int = 0
    var experience: Int = 0
    var strength: Int = 0
    var dexterity: Int = 0
    var constitution: Int = 0
    var intelligence: Int = 0
    var wisdom: Int = 0
    var charisma: Int = 0
    var fortitude: Int = 0
    var reflex: Int = 0
    var will: Int = 0
    var hp: Int = 0
    var portrait: Int = 0
    var name: String = ""
    var alignment: String = ""
    var deity: String = ""
    var size: String = ""
    var gender: String = ""
    var height: String = ""
    var weight: String = ""
    var eyes: String = ""
    var hair: String = ""
    var skin: String = ""
    var speed: String = ""

}
