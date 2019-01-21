package com.senchenko.hookah.parser;

import java.util.ArrayList;
import java.util.List;

public class HookahDataParser {
    private static final String SPACE = "[:]\\s";
    private static final int DATA_POSITION = 1;
    private List<Integer> parsedData = new ArrayList<>();

    public List<Integer> parseDataForHookahHouse(List<String> dataForParse){
        for (String element: dataForParse){
            parsedData.add(Integer.parseInt(element.split(SPACE)[DATA_POSITION]));
        }
        return parsedData;
    }
}
