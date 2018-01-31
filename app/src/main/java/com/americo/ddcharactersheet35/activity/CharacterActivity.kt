package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.TabAdapter
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.SlidingTabLayout
import com.americo.ddcharactersheet35.util.createToolbar
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.startWithId

class CharacterActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        id = intent.getStringExtra("id")
        createSlidingTab()
    }

    override fun onResume() {
        createToolbar(CharacterService(this).get(id).name)
        super.onResume()
    }

    /**
     * Creates the sliding tab on the [R.id.vp_page].
     */
    private fun createSlidingTab() {
        val viewPager = find<ViewPager>(R.id.vp_page)
        viewPager.adapter = TabAdapter(supportFragmentManager, id)

        val slidingTab = find<SlidingTabLayout>(R.id.st_tabs)
        slidingTab.setDistributeEvenly(true)
        slidingTab.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent))
        slidingTab.setViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Function responsible to control the option that was clicked on the menu.
     *
     * If [R.id.it_edit] was clicked it will start the new [EditCharacterActivity].
     * If [R.id.it_change_char] was clicked it will start the new [MainActivity].
     * If [R.id.it_settings] was clicked it will start the new ?.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.it_edit -> {
                startWithId<EditCharacterActivity>(id)
                return true
            }
            R.id.it_change_char -> {
                startActivity( Intent(this, MainActivity::class.java) )
                return true
            }
            R.id.it_settings -> {
                print("settings!")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
