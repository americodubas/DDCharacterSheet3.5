package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.dto.ClassesDto
import com.americo.ddcharactersheet35.service.CharacterClassesService
import com.americo.ddcharactersheet35.service.ClassesService
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.optionSaveSelected
import java.util.ArrayList

class EditClassesActivity : AppCompatActivity() {

    companion object {
        lateinit var id: String
        lateinit var classes_id: String
        lateinit var classes: List<ClassesDto>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_classes)
        createToolbar(getString(R.string.edit_class))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return optionSaveSelected(item, id, Intent(this, EditCharacterActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        id = intent.getStringExtra("id")
        //TODO i think i should receive a characterClass not the class itsself
        //TODO not used, delete later
        classes_id = intent.getStringExtra("classes_id")
    }

}
