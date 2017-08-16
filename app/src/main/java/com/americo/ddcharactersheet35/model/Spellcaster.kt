package com.americo.ddcharactersheet35.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 29/04/2017.
 */
@DatabaseTable(tableName = "spellcaster")
class Spellcaster() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0
    @DatabaseField(foreign = true, columnName = "classes_id", foreignAutoRefresh = true)
    lateinit var classes: Classes
    @DatabaseField(columnName = "circle")
    lateinit var circle: String
}
