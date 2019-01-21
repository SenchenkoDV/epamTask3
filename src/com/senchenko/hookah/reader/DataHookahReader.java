package com.senchenko.hookah.reader;

import com.senchenko.hookah.exception.HookahException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataHookahReader {
    private final static String DEFAULT_PATH = "input/input.txt";
    private static String path = DEFAULT_PATH;
    private static List<String> dataList;

    public static List<String> readDataForHookahHouse(String transferredPath) throws RuntimeException, HookahException {
        if (new File(transferredPath).exists()){
            path = transferredPath;
        }
        try (Stream<String> stream = Files.lines(Paths.get(path))){
            dataList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new HookahException(e);
        }
        return dataList;
    }
}
