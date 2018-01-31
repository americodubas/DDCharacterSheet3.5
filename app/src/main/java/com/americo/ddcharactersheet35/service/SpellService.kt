package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.model.Spell

/**
 * Created by Americo on 01/05/2017.
 *
 * Service responsible for the [Spell]
 */
class SpellService(val context: Context) {

//    fun getSpellsByNameClassCircle(name: String, spellClass: String, circle: String): MutableList<HashMap<String, String>> {
//        return SpellDao(context).getSpellsByNameClassCircle(name, spellClass, circle)
//    }

    fun get( id: String): Spell {
//        val spellDao = SpellDao(context)

//        val spell = spellDao.getSpellById(id)
//
//        spell.allSpellcasters = convertSpellcastersToString(spell)

        return DatabaseHelper.getInstance(context).spellDao().getSpellById(id.toLong())
    }

    private fun convertSpellcastersToString(spell: Spell): String {
        val str = StringBuilder()

//        spell.spellLevel.forEach {
//            if (str.isNotEmpty()) {
//                //separator for the next caster
//                str.append(", ")
//            }
//            str.append("${it.spellcaster.classes.name} ${it.spellcaster.circle}")
//        }

        return str.toString()
    }

}
