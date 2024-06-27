package org.example.cinemamanagement.utils;

public class ConvertJsonNameToTypeName {
    public static String convert(String jsonName) {
        if(!jsonName.contains("_")) {
            return jsonName;
        }

        String[] words = jsonName.split("_");
        StringBuilder typeName = new StringBuilder();

        typeName.append(words[0]);

        for (int i = 1; i < words.length; i++) {
            typeName.append(words[i].substring(0, 1).toUpperCase());
            typeName.append(words[i].substring(1));
        }

        return typeName.toString();
    }
}
