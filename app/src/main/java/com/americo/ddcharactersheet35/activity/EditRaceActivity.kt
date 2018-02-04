package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.base.BaseActivity
import com.americo.ddcharactersheet35.dto.CharacterDto
import com.americo.ddcharactersheet35.dto.RaceDto
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.service.RaceService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.optionSaveSelected

class EditRaceActivity : BaseActivity() {

    private lateinit var races: List<RaceDto>
    private lateinit var character: CharacterDto
    private lateinit var characterService: CharacterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_race)
        createToolbar(getString(R.string.edit_race))
        setRaces()
        setRaceSelectListener()
        characterService = CharacterService(this)
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
        characterService.update(character)
    }

    private fun setRaces() {
        races = RaceService(this).getAll()
        find<Spinner>(R.id.sp_character_race).adapter =
                ArrayAdapter<RaceDto>(this,R.layout.support_simple_spinner_dropdown_item,races)
    }

    private fun setRaceSelectListener() {
        find<Spinner>(R.id.sp_character_race).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                find<TextView>(R.id.tv_race_description).text =
                        Html.fromHtml(
                                races[position].description
                        )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        setId(intent)
        character = characterService.get(id)

        //this will call the setRaceSelectListener to set the description
        find<Spinner>(R.id.sp_character_race).setSelection(
                races.indexOfFirst {
                    character.raceId == it.id
                }
        )
    }
}
