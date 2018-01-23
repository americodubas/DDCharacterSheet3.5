package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.dto.CharacterClassesDto
import com.americo.ddcharactersheet35.dto.ClassesDto
import com.americo.ddcharactersheet35.service.CharacterClassesService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.optionSaveSelected
import com.americo.ddcharactersheet35.util.textString

class AddCharacterClassesActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
        lateinit var classes: List<ClassesDto>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_character_class)
        createToolbar(getString(R.string.add_class))
        setClassesSelectListener()
    }

    private fun setClassesSelectListener() {
        find<Spinner>(R.id.sp_class).onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                find<TextView>(R.id.tv_description).text =
                        Html.fromHtml(
                                classes[position].description
                        )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
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
        val characterClassesDto = CharacterClassesDto()
        characterClassesDto.characterId = id.toLong()
        characterClassesDto.classesId = (find<Spinner>(R.id.sp_class).selectedItem as (ClassesDto)).id
        characterClassesDto.level = find<EditText>(R.id.et_level).text.toString().toInt()

        CharacterClassesService(this).insertCharacterClasses(characterClassesDto)
    }

    override fun onResume() {
        super.onResume()
        id = intent.getStringExtra("id")
        setNotUsedClasses()
        setDefaultLevel()
    }

    private fun setDefaultLevel() {
        find<EditText>(R.id.et_level).textString(1)
    }

    private fun setNotUsedClasses() {
        classes = CharacterClassesService(this).getNotUsedClasses(id)

        find<Spinner>(R.id.sp_class).adapter =
                ArrayAdapter<ClassesDto>(this,R.layout.support_simple_spinner_dropdown_item, classes)

        find<TextView>(R.id.tv_description).text = Html.fromHtml(classes.first().description)
    }
}
