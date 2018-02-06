package com.americo.ddcharactersheet35

import com.americo.ddcharactersheet35.util.convertLongToPieces
import com.americo.ddcharactersheet35.util.convertPiecesToLong
import com.americo.ddcharactersheet35.util.removeLastComma
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class UnitTest {

    private val values = mapOf<Long,String>(
            Pair(0,"0"),
            Pair(1,"1 CP"),
            Pair(10,"1 SP"),
            Pair(100, "1 GP"),
            Pair(1000, "1 PP"),
            Pair(1111, "1 PP,1 GP,1 SP,1 CP"),
            Pair(1011, "1 PP,1 SP,1 CP"),
            Pair(33333, "33 PP,3 GP,3 SP,3 CP")
    )

    @Test
    fun removeComma() {
        assertEquals("1", removeLastComma("1,"))
    }

    @Test
    fun testConvertLongToPieces() {
        values.forEach {
            l: Long, s: String -> assertEquals(s, convertLongToPieces(l))
        }
    }

    @Test
    fun testPiecesToLong() {
        values.forEach {
            l: Long, s: String -> assertEquals(l, convertPiecesToLong(s))
        }
    }

}