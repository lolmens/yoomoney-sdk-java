/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 NBCO YooMoney LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.yoo.money.api.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PeriodTest {

    @Test
    public void testParsing() {
        Map<String, Period> testData = new HashMap<>(6);
        testData.put("P3Y", new Period(3, 0, 0));
        testData.put("P10M", new Period(0, 10, 0));
        testData.put("P5D", new Period(0, 0, 5));
        testData.put("P2M10D", new Period(0, 2, 10));
        testData.put("P3Y6M", new Period(3, 6, 0));
        testData.put("P3Y6M1D", new Period(3, 6, 1));

        for (Map.Entry<String, Period> entry : testData.entrySet()) {
            Period actual = Period.parse(entry.getKey());
            Period expected = entry.getValue();
            assertEquals(actual, expected);
        }
    }

    @Test()
    public void testIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            Period.parse("3Y6M1D");
        });
    }

    @Test()
    public void testUnsupportedArgumentException() {
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
        {
            Period.parse("P3Y6M4DT12H30M5S");
        });
    }
}
