package com.americo.ddcharactersheet35.model

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 29/04/2017.
 */

@DatabaseTable(tableName = "spell")
class Spell() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0
    @DatabaseField(unique = true)
    lateinit var name: String

    @ForeignCollectionField(eager = true, maxEagerLevel = 3)
    lateinit var spellLevel: ForeignCollection<SpellLevel>

    @DatabaseField
    lateinit var school: String
    @DatabaseField
    lateinit var components: String
    @DatabaseField
    lateinit var casting: String
    @DatabaseField
    lateinit var range: String
    @DatabaseField
    lateinit var area: String
    @DatabaseField
    lateinit var duration: String
    @DatabaseField
    lateinit var saving: String
    @DatabaseField
    lateinit var resistance: String
    @DatabaseField
    lateinit var description: String
    @DatabaseField
    lateinit var focus: String
    @DatabaseField
    lateinit var notes: String

    lateinit var allSpellcasters: String
}
