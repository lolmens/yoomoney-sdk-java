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

package com.yoo.money.api.showcase;

import com.yoo.money.api.model.showcase.components.uicontrols.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Slava Yasevich (support@yoomoney.ru)
 */
public class TextTest extends ParameterTest {

    @Test
    public void testValidation() {
        Text.Builder builder = new Text.Builder();
        prepareParameter(builder);

        Text text = builder.create();
        assertTrue(text.isValid("some random string, no constraints"));
        assertFalse(text.isValid("some random string\nno constraints"));

        builder.setPattern("\\d*?")
                .setMinLength(3)
                .setMaxLength(5);

        text = builder.create();
        assertTrue(text.isValid("123"));
        assertTrue(text.isValid("1234"));
        assertTrue(text.isValid("12345"));
        assertFalse(text.isValid("abc"));
        assertFalse(text.isValid("12"));
        assertFalse(text.isValid("123456"));

        testEmptyValues(builder);
    }
}
