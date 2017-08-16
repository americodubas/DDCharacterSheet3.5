package com.americo.ddcharactersheet35;

import com.americo.ddcharactersheet35.data.DataBaseConfigUtil;
import com.americo.ddcharactersheet35.model.Item;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        DataBaseConfigUtil.main(null);
        assertEquals(4, 2 + 2);
    }
}