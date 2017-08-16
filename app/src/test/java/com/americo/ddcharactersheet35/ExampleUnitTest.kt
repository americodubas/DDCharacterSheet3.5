package com.americo.ddcharactersheet35

import com.americo.ddcharactersheet35.data.DataBaseConfigUtil

import org.junit.Test

import org.junit.Assert.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        DataBaseConfigUtil.main(null)
        assertEquals(4, (2 + 2).toLong())
    }
}