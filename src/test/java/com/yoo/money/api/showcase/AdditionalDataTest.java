package com.yoo.money.api.showcase;

import com.yoo.money.api.model.showcase.components.uicontrols.AdditionalData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdditionalDataTest extends ParameterTest {

    @Test
    public void testValidation() {
        AdditionalData.Builder builder = new AdditionalData.Builder();
        prepareParameter(builder);

        AdditionalData additionalData = builder.create();
        assertTrue(additionalData.isValid());

        testEmptyValues(builder);
    }
}
