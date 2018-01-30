package com.americo.ddcharactersheet35.data

import android.content.Context
import android.util.Log
import java.io.FileOutputStream

/**
 * Created by Americo on 08/04/2017.
 *
 * Class responsible for checking if the database already exists.
 * If the database isn't found it'll create one from the file ded35 in the assets.
 *
 */
class CheckDatabase(private var myContext: Context) {

    companion object {
        const val databaseName = "ded35"
    }

    private val databaseFullPath: String

    init {
        this.databaseFullPath = "/data/data/${myContext.packageName}/databases/$databaseName"
        verifyDatabase()
    }

    private fun verifyDatabase() {
        if ( !databaseExists() ) {
            createDatabaseFromAssets()
        }
    }

    private fun databaseExists(): Boolean {
        return myContext.getDatabasePath(databaseName).exists()
    }

    private fun createDatabaseFromAssets() {
        Log.i("copy", "*** Copying database from assets...")
        val myInput = myContext.assets.open(databaseName)
        val myOutput = FileOutputStream(databaseFullPath)
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
