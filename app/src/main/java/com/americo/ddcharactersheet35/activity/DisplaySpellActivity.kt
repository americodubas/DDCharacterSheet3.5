package com.americo.ddcharactersheet35.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.service.SpellService
import com.americo.ddcharactersheet35.util.find

class DisplaySpellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_spell)

        val spell = SpellService(this).getSpellById(intent.getStringExtra("id"))

        find<TextView>(R.id.tv_spell_name).text = spell.name
        find<TextView>(R.id.tv_spell_school).text = spell.school
        find<TextView>(R.id.tv_spell_components).text = spell.components
        find<TextView>(R.id.tv_spell_casting).text = spell.casting
        find<TextView>(R.id.tv_spell_range).text = spell.range
        find<TextView>(R.id.tv_spell_area).text = spell.area
        find<TextView>(R.id.tv_spell_duration).text = spell.duration
        find<TextView>(R.id.tv_spell_saving).text = spell.saving
        find<TextView>(R.id.tv_spell_resistance).text = spell.resistance
        find<TextView>(R.id.tv_spell_description).text = spell.description
        find<TextView>(R.id.tv_spell_notes).text = spell.notes
        //find<TextView>(R.id.tv_spellcaster).text = spell.allSpellcasters

    }
}
