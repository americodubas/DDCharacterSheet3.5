package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.EditCharacterClassAdapter
import com.americo.ddcharactersheet35.service.CharacterClassesService
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.service.RaceService
import com.americo.ddcharactersheet35.util.*

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

    private fun editRaceListener() {
        find<TextView>(R.id.tv_race).setOnClickListener{
            it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.click))
            val intent = Intent(this, EditRaceActivity::class.java)
            intent.putExtra("id", id)
            startActivity( intent )
        }
    }

    private fun addClassListener() {
        find<TextView>(R.id.tv_add_class).setOnClickListener{
            val intent = Intent(this, AddCharacterClassesActivity::class.java)
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

    /**
     * Get fields from the xml and updates the character.
     */
    private fun updateCharacter() {
        val service = CharacterService(this)
        val characterDto = service.getCharacter(id)

        with(characterDto) {
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

        service.updateCharacter(characterDto)
    }

    private fun showCharacter() {
        val characterDto = CharacterService(this).getCharacter(id)

        with(characterDto){
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
        }

        find<EditText>(R.id.tv_character_race).textString(RaceService(this).getRace(characterDto.raceId).name)

        val classesView = find<ListView>(R.id.lv_classes)
        classesView.adapter = EditCharacterClassAdapter(this, CharacterClassesService(this).getCharacterClasses(id))
        setListViewHeight(classesView)

    }

}
