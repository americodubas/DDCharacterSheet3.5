package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.base.BaseActivity
import com.americo.ddcharactersheet35.dto.ClassesDto
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.optionSaveSelected

class EditClassesActivity : BaseActivity() {

    private lateinit var classes_id: String
    private lateinit var classes: List<ClassesDto>

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
        setId(intent)
        //TODO i think i should receive a characterClass not the class it self
        //TODO not used, delete later
        classes_id = intent.getStringExtra("classes_id")
    }

}
