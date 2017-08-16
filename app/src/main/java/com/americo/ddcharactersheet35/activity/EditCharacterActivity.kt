package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.EditCharacterClassAdapter
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.optionSaveSelected
import com.americo.ddcharactersheet35.util.textString

class EditCharacterActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_character)
        createToolbar(getString(R.string.edit_character))
        editRaceListener()
        addClassListener()
    }

    fun editRaceListener() {
        find<ImageButton>(R.id.ib_race).setOnClickListener{
            val intent = Intent(this, EditRaceActivity::class.java)
            intent.putExtra("id", id)
            startActivity( intent )
        }
    }

    fun addClassListener() {
        find<ImageButton>(R.id.ib_add_class).setOnClickListener{
            val intent = Intent(this, AddCharacterClassActivity::class.java)
            intent.putExtra("id", id)
            startActivity( intent )
        }
    }

    override fun onResume() {
        id = intent.getStringExtra("id")
        showCharacter()
        super.onResume()
    }

    override fun onPause() {
        updateCharacter()
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return optionSaveSelected(item, id, Intent(this, CharacterActivity::class.java))
    }

    private fun updateCharacter() {
        val service = CharacterService(this)
        val char = service.getCharacter(id)

        with(char) {
            name = find<EditText>(R.id.et_character_name).text.toString()
            level = find<TextView>(R.id.tv_character_level).text.toString().toInt()
            experience = find<EditText>(R.id.et_character_experience).text.toString().toInt()
            alignment = find<EditText>(R.id.et_character_alignment).text.toString()
            deity = find<EditText>(R.id.et_character_deity).text.toString()
            gender = find<EditText>(R.id.et_character_gender).text.toString()
            age = find<EditText>(R.id.et_character_age).text.toString().toInt()
            size = find<EditText>(R.id.et_character_size).text.toString()
            height = find<EditText>(R.id.et_character_height).text.toString()
            weight = find<EditText>(R.id.et_character_weight).text.toString()
            skin = find<EditText>(R.id.et_character_skin).text.toString()
            eyes = find<EditText>(R.id.et_character_eyes).text.toString()
            hair = find<EditText>(R.id.et_character_hair).text.toString()
        }
        service.updateCharacter(char)
    }

    private fun showCharacter() {
        val char = CharacterService(this).getCharacter(id)

        with(char){
            find<EditText>(R.id.et_character_name).textString(name)
            find<TextView>(R.id.tv_character_level).textString(level)
            find<EditText>(R.id.et_character_experience).textString(experience)
            find<EditText>(R.id.et_character_alignment).textString(alignment)
            find<EditText>(R.id.et_character_deity).textString(deity)
            find<EditText>(R.id.et_character_gender).textString(gender)
            find<EditText>(R.id.et_character_age).textString(age)
            find<EditText>(R.id.et_character_size).textString(size)
            find<EditText>(R.id.et_character_height).textString(height)
            find<EditText>(R.id.et_character_weight).textString(weight)
            find<EditText>(R.id.et_character_skin).textString(skin)
            find<EditText>(R.id.et_character_eyes).textString(eyes)
            find<EditText>(R.id.et_character_hair).textString(hair)
            find<EditText>(R.id.et_character_race).textString(race.name)
        }

        find<ListView>(R.id.lv_classes).adapter = EditCharacterClassAdapter(this, char.characterClasses.toList())

    }

}
