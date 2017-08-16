package com.americo.ddcharactersheet35.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 21/06/2017.
 */

@DatabaseTable(tableName = "classes")
class Classes() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0
    @DatabaseField
    lateinit var name: String
    @DatabaseField
    var spellcaster: Int = 0

    override fun toString(): String {
        return name
    }
}