package utils;

import io.restassured.path.json.JsonPath;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Helper {
    public static String getStringResponse(String response, String path) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getString(path);
    }

    public static List getListResponse(String response, String path) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getList(path);
    }

    public static int getIntResponse(String response, String path) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getInt(path);
    }

    public static String extractKeyFromUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            String query = url.getQuery();

            // Split the query string into key-value pairs
            String[] pairs = query.split("&");
            Map<String, String> queryPairs = new HashMap<>();

            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = URLDecoder.decode(pair.substring(0, idx), String.valueOf(StandardCharsets.UTF_8));
                String value = URLDecoder.decode(pair.substring(idx + 1), String.valueOf(StandardCharsets.UTF_8));
                queryPairs.put(key, value);
            }

            // Return the value of the "key" parameter
            return queryPairs.get("key");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String updateJsonValue(String jsonString, String keyPath, Object newValue) {
        // Convert the JSON string to a JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // Split the key path by dots to navigate through the structure
        String[] keys = keyPath.split("\\.");

        // Navigate through the JSON object hierarchy
        JSONObject currentObject = jsonObject;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];

            // Check if the key contains an array index (e.g., "array[0]")
            if (key.contains("[") && key.contains("]")) {
                // Extract the array name and index
                String arrayName = key.substring(0, key.indexOf("["));
                int arrayIndex = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));

                // Get the JSONArray and the object at the specified index
                JSONArray jsonArray = currentObject.getJSONArray(arrayName);
                currentObject = jsonArray.getJSONObject(arrayIndex);
            } else {
                // Standard key, move deeper into the JSON object
                currentObject = currentObject.getJSONObject(key);
            }
        }

        // Determine the data type of the existing key's value and update accordingly
        String finalKey = keys[keys.length - 1];
        Object existingValue = currentObject.get(finalKey);

        if (existingValue instanceof Integer) {
            currentObject.put(finalKey, Integer.parseInt(newValue.toString()));
        } else if (existingValue instanceof Boolean) {
            currentObject.put(finalKey, Boolean.parseBoolean(newValue.toString()));
        } else if (existingValue instanceof Double) {
            currentObject.put(finalKey, Double.parseDouble(newValue.toString()));
        } else if (existingValue instanceof Long) {
            currentObject.put(finalKey, Long.parseLong(newValue.toString()));
        } else if (existingValue instanceof JSONArray) {
            if (newValue instanceof JSONArray) {
                currentObject.put(finalKey, newValue);
            } else if (newValue instanceof ArrayList) {
                currentObject.put(finalKey, new JSONArray((ArrayList<?>) newValue));
            } else {
                throw new IllegalArgumentException("Expected a JSONArray or ArrayList but got: " + newValue.getClass().getName());
            }
        } else if (existingValue instanceof JSONObject) {
            if (newValue instanceof JSONObject) {
                currentObject.put(finalKey, newValue);
            } else {
                throw new IllegalArgumentException("Expected a JSONObject but got: " + newValue.getClass().getName());
            }
        } else {
            // Default case for String or any other type
            currentObject.put(finalKey, newValue);
        }

        // Return the updated JSON string
        return jsonObject.toString();
    }
}
