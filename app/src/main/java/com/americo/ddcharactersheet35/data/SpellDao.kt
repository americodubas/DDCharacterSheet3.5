package com.americo.ddcharactersheet35.data

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.americo.ddcharactersheet35.model.Spell
import com.americo.ddcharactersheet35.model.Spellcaster
import java.sql.SQLException

/**
 * Created by Americo on 02/05/2017.
 */

class SpellDao(context: Context) {


    private val db = DatabaseHelper(context)

    fun getSpellById(id: String): Spell {
        return getSpellById(Integer.valueOf(id)!!)
    }

    fun getSpellById(id: Int): Spell {
        return db.getSpellDao().queryForId(id)
    }

    fun getSpellcastersFromSpell(id: Int): List<Spellcaster> {
        val spellLevelBuilder = db.getSpellLevelDao().queryBuilder()
        val spellcasterBuilder = db.getSpellcasterDao().queryBuilder()

        spellLevelBuilder.where().eq("spell_id", id)
        return spellcasterBuilder.join(spellLevelBuilder).query()
    }

    fun getSpellsByNameClassCircle(name: String, spellClass: String, circle: String): MutableList<HashMap<String, String>> {
        val spellsArray = ArrayList<HashMap<String, String>>()
        val filterClass = spellClass != "All classes"
        val filterCircle = circle != "All circles"

        val spellBuilder = db.getSpellDao().queryBuilder()
        val spellLevelBuilder = db.getSpellLevelDao().queryBuilder()
        val spellcasterBuilder = db.getSpellcasterDao().queryBuilder()

        spellBuilder.where().like("name", "%$name%")

        if (filterClass || filterCircle) {
            spellLevelBuilder.join(spellcasterBuilder)
            spellBuilder.join(spellLevelBuilder)
        }

        if (filterClass && filterCircle) {
            spellcasterBuilder.where()
                    .eq("class", spellClass)
                    .and()
                    .eq("circle", circle)
        } else if (filterClass) {
            spellcasterBuilder.where().eq("class", spellClass)
        } else {
            spellcasterBuilder.where().eq("circle", circle)
        }

        val spells = spellBuilder.query()
        for (spell in spells) {
            val hash = HashMap<String, String>()
            hash.put("id", spell.id.toString())
            hash.put("name", spell.name)
            hash.put("description", spell.description)
            spellsArray.add(hash)
        }

        return spellsArray
    }
}