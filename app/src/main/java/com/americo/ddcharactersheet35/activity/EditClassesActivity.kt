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
        lateinit var allClasses: List<ClassesDto>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_classes)
        createToolbar(getString(R.string.edit_class))
        getAllClasses()
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
        classes_id = intent.getStringExtra("classes_id")
        setAvailableClasses()
    }

    private fun setAvailableClasses() {
        //TODO put it on service
        val availableClasses = ArrayList<ClassesDto>()
        val usedClasses = CharacterClassesService(this).getCharacterClasses(id)

        allClasses.forEach {
            if(it.id == classes_id.toLong() || usedClasses.indexOf(it) == -1){
                availableClasses.add(it)
            }
        }
        //TODO put it on adapter
    }

    private fun getAllClasses() {
         allClasses = ClassesService(this).getAllClasses()
    }

}
