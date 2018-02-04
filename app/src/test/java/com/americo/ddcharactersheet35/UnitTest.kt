package com.americo.ddcharactersheet35

import com.americo.ddcharactersheet35.util.convertToDDPieces
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class UnitTest {

    @Test
    fun longToDDPieces() {
        assertEquals("0", convertToDDPieces(0))
        assertEquals("1 CP ", convertToDDPieces(1))
        assertEquals("1 SP ", convertToDDPieces(10))
        assertEquals("1 GP ", convertToDDPieces(100))
        assertEquals("1 PP ", convertToDDPieces(1000))
        assertEquals("1 PP 1 GP 1 SP 1 CP ", convertToDDPieces(1111))
        assertEquals("1 PP 1 SP 1 CP ", convertToDDPieces(1011))
        assertEquals("33 PP 3 GP 3 SP 3 CP ", convertToDDPieces(33333))
    }

}