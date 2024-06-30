package api.utilities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;


public class CommonTest {

    public static ResourceBundle envProperties;

    @BeforeClass
    public static ResourceBundle getEnvProperties() {
        envProperties =  ResourceBundle.getBundle("env"); // load properties file
        return envProperties;
    }

    public static void compareJsonObject(JSONObject actual, JSONObject expected) {
        Set<String> expectedKeys = expected.keySet();
        Set<String> actualKeys = actual.keySet();

        if (!expectedKeys.equals(actualKeys)) {
            throw new AssertionError("Key set mismatch. Expected: " + expectedKeys + ", Actual: " + actualKeys);
        }
        for (String key : expectedKeys) { // only in the expected keys
            Object expectedValue = expected.get(key);
            Object actualValue = actual.get(key);

            if (expectedValue instanceof JSONArray && actualValue instanceof JSONArray) {
                compareJsonArrays((JSONArray) expectedValue, (JSONArray) actualValue);
            }
            else if (expectedValue instanceof JSONObject && actualValue instanceof JSONObject) {
                compareJsonObject(expected.getJSONObject(key), actual.getJSONObject(key));
            } else if (!expectedValue.equals(actualValue)) {
                throw new AssertionError(String.format("value does not match in key '%s' \n actual value: %s \n expected value: %s",key,actualValue,expectedValue));
            }
        }

    }

    public static void compareJsonArrays(JSONArray expected, JSONArray actual) {
        if (expected.length() != actual.length()) {
            throw new AssertionError("Array size mismatch. Expected: " + expected.length() + ", Actual: " + actual.length());
        }

        for (int i = 0; i < expected.length(); i++) {
            Object expectedValue = expected.get(i);
            Object actualValue = actual.get(i);

            if (expectedValue instanceof JSONObject && actualValue instanceof JSONObject) {
                compareJsonObject( (JSONObject) actualValue,(JSONObject) expectedValue);
            } else if (expectedValue instanceof JSONArray && actualValue instanceof JSONArray) {
                compareJsonArrays( (JSONArray) actualValue, (JSONArray) expectedValue);
            } else if (!expectedValue.equals(actualValue)) {
                throw new AssertionError("Array element mismatch at index " + i + ". Expected: " + expectedValue + ", Actual: " + actualValue);
            }
        }
    }

    public static void compareJson(JSONObject actual, JSONObject expected) {
        if (actual == null && expected == null) {
            return;
        }
        // iterate expectation keys
        Iterator<String> keys = expected.keys();

        while (keys.hasNext()) {
            String key = keys.next();

            // if the expected does not contains actual json, return false
            if (!actual.has(key)) {
                throw new AssertionError(String.format("Response does not contains key: '%s'",key));
            } else {
                Object actualValue = actual.get(key);
                Object expectValue = expected.get(key);

                if (!actualValue.equals(expectValue)) {
                    throw new AssertionError(String.format("value does not match in key '%s' \n actual value: %s \n expected value: %s",key,actualValue,expectValue));
                }
            }
        }

    }

    public static boolean assertJson(JSONObject actual, JSONObject expected) {

        // Check if both objects are null
        if (actual == null && expected == null) {
            return true;
        }

        // iterate expectation keys
        Iterator<String> keys = expected.keys();

        while (keys.hasNext()) {
            String key = keys.next();

            // if the expected does not contains actual json, return false
            if (!actual.has(key)) {
                return false;
            } else {
                Object actualValue = actual.get(key);
                Object expectValue = expected.get(key);

                if (!actualValue.equals(expectValue)) {
                    return false;
                }
            }

        }

        return true;
    }

    public JSONObject readExpectedJson(String fileName) {
        String userDirectory = System.getProperty("user.dir");
        String expectedJsonPath = "/src/test/java/api/test/expectedData";
        Path path = Paths.get(userDirectory, expectedJsonPath, fileName);

        try {
            return new JSONObject(new String(Files.readAllBytes(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
