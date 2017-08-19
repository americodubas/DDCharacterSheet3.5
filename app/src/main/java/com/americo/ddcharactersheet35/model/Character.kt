package com.americo.ddcharactersheet35.model

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 29/04/2017.
 *
 * Entity of the character
 *
 */

@DatabaseTable(tableName = "character")
class Character {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0

    @DatabaseField(foreign = true, columnName = "race_id", foreignAutoRefresh = true)
    lateinit var race: Race

    @ForeignCollectionField(eager = true, maxEagerLevel = 2)
    lateinit var characterClasses: ForeignCollection<CharacterClasses>

    @DatabaseField
    var name: String = ""
    @DatabaseField
    var alignment: String = ""
    @DatabaseField
    var deity: String = ""
    @DatabaseField
    var size: String = ""
    @DatabaseField
    var age: Int = 0
    @DatabaseField
    var gender: String = ""
    @DatabaseField
    var height: String = ""
    @DatabaseField
    var weight: String = ""
    @DatabaseField
    var eyes: String = ""
    @DatabaseField
    var hair: String = ""
    @DatabaseField
    var skin: String = ""
    @DatabaseField
    var level: Int = 0
    @DatabaseField
    var experience: Int = 0
    @DatabaseField
    var strength: Int = 0
    @DatabaseField
    var dexterity: Int = 0
    @DatabaseField
    var constitution: Int = 0
    @DatabaseField
    var intelligence: Int = 0
    @DatabaseField
    var wisdom: Int = 0
    @DatabaseField
    var charisma: Int = 0
    @DatabaseField
    var fortitude: Int = 0
    @DatabaseField
    var reflex: Int = 0
    @DatabaseField
    var will: Int = 0
    @DatabaseField(columnName = "full_hp")
    var fullHp: Int = 0
    @DatabaseField
    var hp: Int = 0
    @DatabaseField
    var speed: String = ""
}
