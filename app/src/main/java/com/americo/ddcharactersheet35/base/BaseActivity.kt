package com.americo.ddcharactersheet35.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.americo.ddcharactersheet35.util.ID

/**
 * Created by Americo on 04/02/2018.
 * Base activity to hold common methods
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var id: String

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null)
            setId(intent)
    }

    fun setId(intent: Intent) {
        id = intent.getStringExtra(ID)
    }

}