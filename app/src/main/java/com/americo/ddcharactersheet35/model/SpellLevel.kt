package com.americo.ddcharactersheet35.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 29/04/2017.
 */

@DatabaseTable(tableName = "spell_level")
class SpellLevel() {

    @DatabaseField(foreign = true, columnName = "spell_id", foreignAutoRefresh = true)
    lateinit var spell: Spell
    @DatabaseField(foreign = true, columnName = "spellcaster_id", foreignAutoRefresh = true)
    lateinit var spellcaster: Spellcaster
}
