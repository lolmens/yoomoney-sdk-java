package com.yandex.money.model.cps;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vyasevich
 */
public final class JsonUtils {

    private JsonUtils() {
    }

    public static Long getLong(JsonObject object, String memberName) {
        JsonPrimitive primitive = getPrimitiveChecked(object, memberName);
        return primitive == null ? null : primitive.getAsLong();
    }

    public static String getMandatoryString(JsonObject object, String memberName) {
        String string = getString(object, memberName);
        checkMandatoryValue(string, memberName);
        return string;
    }

    public static String getString(JsonObject object, String memberName) {
        JsonPrimitive primitive = getPrimitiveChecked(object, memberName);
        return primitive == null ? null : primitive.getAsString();
    }

    public static BigDecimal getMandatoryBigDecimal(JsonObject object, String memberName) {
        BigDecimal bigDecimal = getBigDecimal(object, memberName);
        checkMandatoryValue(bigDecimal, memberName);
        return bigDecimal;
    }

    public static BigDecimal getBigDecimal(JsonObject object, String memberName) {
        JsonPrimitive primitive = getPrimitiveChecked(object, memberName);
        return primitive == null ? null : primitive.getAsBigDecimal();
    }

    public static DateTime getMandatoryDateTime(JsonObject object, String memberName) {
        DateTime dateTime = getDateTime(object, memberName);
        checkMandatoryValue(dateTime, memberName);
        return dateTime;
    }

    public static DateTime getDateTime(JsonObject object, String memberName) {
        JsonPrimitive primitive = getPrimitiveChecked(object, memberName);
        return primitive == null ? null :
                DateTime.parse(primitive.getAsString(),
                        ISODateTimeFormat.dateTimeParser().withOffsetParsed());
    }

    public static Map<String, String> map(JsonObject object) {
        checkObject(object);
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getAsString());
        }
        return result;
    }

    private static JsonPrimitive getPrimitiveChecked(JsonObject object, String memberName) {
        checkParameters(object, memberName);
        return object.getAsJsonPrimitive(memberName);
    }

    private static void checkParameters(JsonObject object, String memberName) {
        checkObject(object);
        checkMemberName(memberName);
    }

    private static void checkObject(JsonObject object) {
        if (object == null) {
            throw new NullPointerException("JSON object is null.");
        }
    }

    private static void checkMemberName(String memberName) {
        if (memberName == null) {
            throw new NullPointerException("Member name is null.");
        }
        if (memberName.length() == 0) {
            throw new IllegalArgumentException("Member is an empty string.");
        }
    }

    private static void checkMandatoryValue(Object value, String memberName) {
        if (value == null) {
            throw new NullPointerException("mandatory value \'" + memberName + "\' is null");
        }
    }
}