package com.americo.ddcharactersheet35.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 21/06/2017.
 */

@DatabaseTable(tableName = "character_classes")
class CharacterClasses() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0
    @DatabaseField(foreign = true, columnName = "character_id", foreignAutoRefresh = true)
    lateinit var character: Character
    @DatabaseField(foreign = true, columnName = "classes_id", foreignAutoRefresh = true)
    lateinit var classes: Classes
    @DatabaseField
    var level: Int = 0

    constructor(
        character: Character,
        classes: Classes,
        level: Int
    ): this() {
        this.character = character
        this.classes = classes
        this.level = level
    }

    override fun toString() = level.toString() + " " + classes.name
}