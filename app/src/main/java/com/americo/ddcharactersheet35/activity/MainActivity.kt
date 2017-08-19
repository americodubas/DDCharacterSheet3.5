package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.CharacterAdapter
import com.americo.ddcharactersheet35.data.DatabaseHelper
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.service.SpellService
import com.americo.ddcharactersheet35.util.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO DELETE LATER
        val spellCount = find<TextView>(R.id.tv_spell_count)
        val count = "Spell Count: ${DatabaseHelper(this).getSpellDao().queryForAll().size}"
        spellCount.text = count

        val spell = SpellService(this).getSpellById("1")
        val test =  "${spell.name} - ${spell.allSpellcasters}"
        find<TextView>(R.id.tv_test).text = test

        find<Button>(R.id.bt_spell_filter).setOnClickListener {
            startActivity( Intent(this, SpellFilterActivity::class.java))
        }

        newCharacterListener()
    }

    override fun onResume() {
        showCharacters()
        super.onResume()
    }

    private fun newCharacterListener() {
        find<Button>(R.id.bt_new_character).setOnClickListener{
            val intent = Intent(this, CharacterActivity::class.java)
            intent.putExtra("id", CharacterService(this).insertEmptyCharacter().toString())
            startActivity( intent )
        }
    }

    private fun showCharacters(){
        find<GridView>(R.id.gv_character).adapter = CharacterAdapter(this, CharacterService(this).getAllCharacters())
    }
}
