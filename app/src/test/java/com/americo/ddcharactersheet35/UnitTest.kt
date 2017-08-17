package com.americo.ddcharactersheet35

import com.americo.ddcharactersheet35.data.createOrmLiteConfigFile
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class UnitTest {
    @Test
    @Throws(Exception::class)
    fun check_if_orm_lite_config_file_exists() {
        createOrmLiteConfigFile()
        assertEquals(File("./res/raw/").listFiles().first().name, "ormlite_config.txt")
    }

}