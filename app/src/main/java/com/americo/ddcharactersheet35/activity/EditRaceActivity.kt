package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.dto.CharacterDto
import com.americo.ddcharactersheet35.dto.RaceDto
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.service.RaceService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.optionSaveSelected

class EditRaceActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
        lateinit var races: List<RaceDto>
        lateinit var character: CharacterDto
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
        character.raceId = (find<Spinner>(R.id.sp_character_race).selectedItem as (RaceDto)).id
        CharacterService(this).updateCharacter(character)
    }

    private fun setRaces() {
        races = RaceService(this).getAllRaces()
        find<Spinner>(R.id.sp_character_race).adapter =
                ArrayAdapter<RaceDto>(this,R.layout.support_simple_spinner_dropdown_item,races)
    }

    override fun onResume() {
        super.onResume()
        id = intent.getStringExtra("id")
        character = CharacterService(this).getCharacter(id)
        find<Spinner>(R.id.sp_character_race).setSelection(races.indexOfFirst { character.raceId == it.id })
    }
}
