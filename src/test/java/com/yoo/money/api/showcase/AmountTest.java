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

import com.yoo.money.api.model.showcase.components.uicontrols.Amount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

/**
 * @author Slava Yasevich (support@yoomoney.ru)
 */
public class AmountTest extends ParameterTest {

    @Test
    public void testValidation() {
        Amount.Builder builder = new Amount.Builder();
        prepareParameter(builder);
        checkValues(builder.create());

        builder.setMax(new BigDecimal(1000));
        checkValues(builder.create());

        testEmptyValues(builder);
    }

    private void checkValues(Amount amount) {
        assertTrue(amount.isValid("0.01"));
        assertTrue(amount.isValid("+1000"));
        assertFalse(amount.isValid("0.0"));
        assertFalse(amount.isValid("1,0"));
        assertFalse(amount.isValid("-10"));
    }
}
