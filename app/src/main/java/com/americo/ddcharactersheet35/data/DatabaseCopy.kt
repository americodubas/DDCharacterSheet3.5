package com.americo.ddcharactersheet35.data

import android.content.Context
import android.util.Log
import java.io.FileOutputStream

/**
 * Created by Americo on 08/04/2017.
 *
 * Class responsible for creating the database from the assets file
 *
 */
class DatabaseCopy(private var myContext: Context) {

    companion object {
        val DB_NAME = "ded35"
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

}
