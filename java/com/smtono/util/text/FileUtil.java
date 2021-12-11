package com.smtono.util.text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    /** Returns lines from file */
    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try {
            InputStream inputStream = FileUtil.class.getResourceAsStream('/' + fileName);
            BufferedReader reader = null;
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            if (reader != null) {
                for (String line; (line = reader.readLine()) != null;) {
                    lines.add(line);
                }
                reader.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
