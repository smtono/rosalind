package com.smtono.util.helpers;

import com.smtono.util.text.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Forbidden {
    // TODO: DRY
    public static List<String> getForbiddenWords() {
        return FileUtil.readFile("forbidden_words.txt");
    }

    public static List<String> getForbiddenPeople() {
        File people = new File("forbidden_people.txt");
        List<String> forbiddenPeople = new ArrayList<>();

        try {
            Scanner scn = new Scanner(people);

            while (scn.hasNextLine()) {
                forbiddenPeople.add(scn.nextLine());
            }
            scn.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return forbiddenPeople;
    }

    /*public static void main(String[] args) {
        List<String> words = getForbiddenWords();

        words.forEach(System.out::println);
    }*/
}