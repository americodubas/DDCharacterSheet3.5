package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.model.Race
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.optionSaveSelected

class EditRaceActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
        lateinit var races: List<Race>
        lateinit var character: Character
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_race)
        createToolbar(getString(R.string.edit_race))
        setRaces()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        editRace()
        return optionSaveSelected(item, id, Intent(this, EditCharacterActivity::class.java))
    }

    private fun editRace() {
        character.race = find<Spinner>(R.id.sp_character_race).selectedItem as (Race)
        CharacterService(this).updateCharacter(character)
    }

    fun setRaces() {
        races = CharacterService(this).getAllRaces()
        find<Spinner>(R.id.sp_character_race).adapter =
                ArrayAdapter<Race>(this,R.layout.support_simple_spinner_dropdown_item,races)
    }

    override fun onResume() {
        super.onResume()
        id = intent.getStringExtra("id")
        character = CharacterService(this).getCharacter(id)
        find<Spinner>(R.id.sp_character_race).setSelection(races.indexOfFirst { character.race.id == it.id })
    }
}
