package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.dto.CharacterClassesDto
import com.americo.ddcharactersheet35.dto.CharacterDto
import com.americo.ddcharactersheet35.dto.ClassesDto
import com.americo.ddcharactersheet35.service.CharacterClassesService
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.optionSaveSelected
import com.americo.ddcharactersheet35.util.textString

class AddCharacterClassesActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
        lateinit var classes: List<ClassesDto>
        lateinit var character: CharacterDto
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_character_class)
        createToolbar(getString(R.string.add_class))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        addCharacterClasses()
        return optionSaveSelected(item, id, Intent(this, EditCharacterActivity::class.java))
    }

    private fun addCharacterClasses() {
        val characterClass = CharacterClassesDto()
        characterClass.character = character
        characterClass.classes = find<Spinner>(R.id.sp_class).selectedItem as (ClassesDto)
        characterClass.level = find<EditText>(R.id.et_level).text.toString().toInt()
        CharacterClassesService(this).insertCharacterClasses(characterClass)
    }

    override fun onResume() {
        super.onResume()
        id = intent.getStringExtra("id")
        character = CharacterService(this).getCharacter(id)
        setClasses()
        setDefaultLevel()
    }

    private fun setDefaultLevel() {
        find<EditText>(R.id.et_level).textString(1)
    }

    private fun setClasses() {
        classes = CharacterClassesService(this).getNotUsedClasses(character)
        find<Spinner>(R.id.sp_class).adapter =
                ArrayAdapter<ClassesDto>(this,R.layout.support_simple_spinner_dropdown_item, classes)
    }
}
