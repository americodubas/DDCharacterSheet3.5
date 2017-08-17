package com.americo.ddcharactersheet35.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.view.View
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.americo.ddcharactersheet35.R

/**
 * Created by Americo on 08/05/2017.
 *
 * File to keep functions to make my life easier and to try new stuff
 *
 */
inline fun <reified T: View> Activity.find(id: Int): T = findViewById(id)

inline fun <reified T: View> Fragment.find(id: Int): T = activity.findViewById(id)

fun TextView.textString(s: String){
    this.text = SpannableStringBuilder(s)
}

fun TextView.textString(s: Int){
    this.text = SpannableStringBuilder(s.toString())
}
fun AppCompatActivity.createToolbar(title: String) {
    val toolbar = this.find<Toolbar>(R.id.in_toolbar)
    toolbar.title = title
    setSupportActionBar(toolbar)
}

fun AppCompatActivity.optionSaveSelected(item: MenuItem?, id: String, intent: Intent): Boolean {
    return when (item?.itemId) {
        R.id.it_save -> {
            intent.putExtra("id", id)
            startActivity( intent )
            return true
        }
        else -> false
    }
}

@Suppress("unused")
fun BaseAdapter.toast(context: Context , message: CharSequence){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}