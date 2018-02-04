package com.americo.ddcharactersheet35.activity

import android.os.Bundle
import android.widget.GridView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.PortraitAdapter
import com.americo.ddcharactersheet35.base.BaseActivity
import com.americo.ddcharactersheet35.util.ID
import com.americo.ddcharactersheet35.util.find

class EditPortraitActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_portrait)
    }

    override fun onResume() {
        setId(intent)
        showPortraits()
        super.onResume()
    }

    private fun showPortraits() {
        find<GridView>(R.id.gv_portrait).adapter = PortraitAdapter(this, id)
    }
}
