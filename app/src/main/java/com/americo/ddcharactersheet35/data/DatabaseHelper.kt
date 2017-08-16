package com.americo.ddcharactersheet35.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.americo.ddcharactersheet35.model.*
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.RuntimeExceptionDao
import com.j256.ormlite.support.ConnectionSource
import java.io.FileOutputStream

/**
 * Created by Americo on 08/04/2017.
 */
class DatabaseHelper (var myContext: Context) : OrmLiteSqliteOpenHelper(myContext, DatabaseHelper.DB_NAME, null, DatabaseHelper.DB_VERSION) {

    companion object {
        private val DB_NAME = "ded35"
        private val DB_VERSION = 1
    }

    private val dataBaseFullPath: String

    init {
        this.dataBaseFullPath = "/data/data/${myContext.packageName}/databases/$DB_NAME"

        verifyDatabase()
    }

    private fun verifyDatabase() {
        if ( !databaseExists() ) {
            createDataBase()
        }
    }

    private fun databaseExists(): Boolean {
        return myContext.getDatabasePath(DB_NAME).exists()
    }

    private fun createDataBase() {
        readableDatabase
        copyDatabaseFromAssets()
    }

    private fun copyDatabaseFromAssets() {
        Log.i("copy", "*** Copying database from assets...")
        val myInput = myContext.assets.open(DB_NAME)
        val myOutput = FileOutputStream(dataBaseFullPath)
        val buffer = ByteArray(10)
        var length = myInput.read(buffer)
        while (length > 0) {
            myOutput.write(buffer, 0, length)
            length = myInput.read(buffer)
        }
        myOutput.flush()
        myOutput.close()
        myInput.close()
        Log.i("copy", "*** Copy finished!")
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase, connectionSource: ConnectionSource) {

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, connectionSource: ConnectionSource, i: Int, i1: Int) {

    }

    //***** DAOS *****

    fun getCharacterDao(): RuntimeExceptionDao<Character, Int> {
        return getRuntimeExceptionDao(Character::class.java)
    }

    fun getCharacterClassesDao(): RuntimeExceptionDao<CharacterClasses, Int> {
        return getRuntimeExceptionDao(CharacterClasses::class.java)
    }

    fun getClassesDao(): RuntimeExceptionDao<Classes, Int> {
        return getRuntimeExceptionDao(Classes::class.java)
    }

    fun getRaceDao(): RuntimeExceptionDao<Race, Int> {
        return getRuntimeExceptionDao(Race::class.java)
    }

    fun getSpellDao(): RuntimeExceptionDao<Spell, Int> {
        return getRuntimeExceptionDao(Spell::class.java)
    }

    fun getSpellLevelDao(): RuntimeExceptionDao<SpellLevel, Int> {
        return getRuntimeExceptionDao(SpellLevel::class.java)
    }

    fun getSpellcasterDao(): RuntimeExceptionDao<Spellcaster, Int> {
        return getRuntimeExceptionDao(Spellcaster::class.java)
    }

}
