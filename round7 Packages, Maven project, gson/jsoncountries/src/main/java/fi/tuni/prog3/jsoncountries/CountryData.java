package fi.tuni.prog3.jsoncountries;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CountryData {

    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) throws IOException {
        Map<String, Number> areaMap = getMapFromJsonFile(areaFile, "Area");
        Map<String, Number> popMap = getMapFromJsonFile(populationFile, "Population");
        Map<String, Number> gdpMap = getMapFromJsonFile(gdpFile, "GDP");

        List<Country> countries = areaMap.keySet().stream()
            .filter(name -> popMap.containsKey(name) && gdpMap.containsKey(name))
            .map(name -> new Country(name, areaMap.get(name).doubleValue(), popMap.get(name).longValue(), gdpMap.get(name).doubleValue()))
            .sorted()
            .collect(Collectors.toList());

        return countries;
    }

    private static Map<String, Number> getMapFromJsonFile(String filePath, String fieldName) throws IOException {
        Map<String, Number> map = new HashMap<>();
        JsonParser parser = new JsonParser();

        JsonObject jsonObj = parser.parse(new FileReader(filePath)).getAsJsonObject();
        JsonArray records = jsonObj.getAsJsonObject("Root").getAsJsonObject("data").getAsJsonArray("record");

        for (JsonElement record : records) {
            JsonObject obj = record.getAsJsonObject();
            String countryName = obj.getAsJsonArray("field").get(0).getAsJsonObject().get("value").getAsString();
            double value = Double.parseDouble(obj.getAsJsonArray("field").get(2).getAsJsonObject().get("value").getAsString());
            map.put(countryName, value);
        }

        return map;
    }

    public static void writeToJson(List<Country> countries, String outputFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(countries);
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}