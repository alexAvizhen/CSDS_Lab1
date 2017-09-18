package com.bsu.avizhen.lab1.analizers;

import com.bsu.avizhen.lab1.utils.Language;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

/**
 * Created by Александр on 17.09.2017.
 */
public class FrequencyAnalyzer {
    private static List<SimpleEntry<Character, Double>> englishLettersFrequency = new ArrayList<>();
    private static List<SimpleEntry<Character, Double>> russianLettersFrequency = new ArrayList<>();

    private Language language = Language.ENGLISH;

    static {
        initEnglishList();
        initRussianList();
        Comparator<SimpleEntry<Character, Double>> letterFreqComparator = new Comparator<SimpleEntry<Character, Double>>() {
            @Override
            public int compare(SimpleEntry<Character, Double> o1, SimpleEntry<Character, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        englishLettersFrequency.sort(letterFreqComparator);
        russianLettersFrequency.sort(letterFreqComparator);
    }

    public FrequencyAnalyzer(Language language) {
        this.language = language;
    }

    public String analyzeKeyWord(String encryptText, int keyWordLength) {
        char[] resultWord = new char[keyWordLength];
        for (int i = 0; i < keyWordLength; i++) {
            resultWord[i] = analyzeLetter(encryptText.toUpperCase(), keyWordLength, i);
        }
        return new String(resultWord);
    }

    private char analyzeLetter(String encryptText, int keyWordLength, int index) {
        StringBuilder indexSubString = new StringBuilder();
        for (int i = index; i < encryptText.length(); i += keyWordLength) {
            if (encryptText.charAt(i) >= language.getStartSymbol() && encryptText.charAt(i) <= language.getStartSymbol() + language.getAmountOfSymbolsInLang()) {
                indexSubString.append(encryptText.charAt(i));
            }
        }
        List<Map.Entry<Character, Double>> resultList = new ArrayList<>();
        for (int i = 0; i < indexSubString.length(); i ++) {
            resultList.add(new SimpleEntry<>(indexSubString.charAt(i),
                    getLetterFrequency(indexSubString.charAt(i), indexSubString.toString())));
        }
        resultList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        char statisticsChar = findNearestCharInLetterList(resultList.get(0).getValue());
        char encryptChar = resultList.get(0).getKey();
        int number = Math.abs(encryptChar - statisticsChar);
        return (char) (language.getStartSymbol() + number);
    }

    private char findNearestCharInLetterList(double letterFrequency) {
        List<SimpleEntry<Character, Double>> letterFrequencyList;
        if (language == Language.ENGLISH) {
            letterFrequencyList = englishLettersFrequency;
        } else if (language == Language.RUSSIAN) {
            letterFrequencyList = russianLettersFrequency;
        } else {
            return '?';
        }
        if (letterFrequency <= letterFrequencyList.get(0).getValue()) {
            return letterFrequencyList.get(0).getKey();
        } else if (letterFrequency >= letterFrequencyList.get(language.getAmountOfSymbolsInLang() - 1).getValue()) {
            return letterFrequencyList.get(language.getAmountOfSymbolsInLang() - 1).getKey();
        } else {
            for (int i = 0; i < letterFrequencyList.size() - 1; i++) {
                double leftLetterValue = letterFrequencyList.get(i).getValue();
                double rightLetterValue = letterFrequencyList.get(i + 1).getValue();
                if (leftLetterValue <= letterFrequency && letterFrequency <= rightLetterValue) {
                    if (letterFrequency - leftLetterValue < rightLetterValue - letterFrequency) {
                        return letterFrequencyList.get(i).getKey();
                    } else {
                        return letterFrequencyList.get(i + 1).getKey();
                    }
                }
            }
        }
        return '?';
    }

    private double getLetterFrequency(char letter, String text) {
        long count = text.chars().filter(value -> value == letter).count();
        return count / ((double) text.length());
    }

    private static void initEnglishList() {
        englishLettersFrequency.add(new SimpleEntry<>('A', 0.08167));
        englishLettersFrequency.add(new SimpleEntry<>('B', 0.01492));
        englishLettersFrequency.add(new SimpleEntry<>('C', 0.02782));
        englishLettersFrequency.add(new SimpleEntry<>('D', 0.04253));
        englishLettersFrequency.add(new SimpleEntry<>('E', 0.12702));
        englishLettersFrequency.add(new SimpleEntry<>('F', 0.0228));
        englishLettersFrequency.add(new SimpleEntry<>('G', 0.02015));
        englishLettersFrequency.add(new SimpleEntry<>('H', 0.06094));
        englishLettersFrequency.add(new SimpleEntry<>('I', 0.06966));
        englishLettersFrequency.add(new SimpleEntry<>('J', 0.00153));
        englishLettersFrequency.add(new SimpleEntry<>('K', 0.00772));
        englishLettersFrequency.add(new SimpleEntry<>('L', 0.04025));
        englishLettersFrequency.add(new SimpleEntry<>('M', 0.02406));
        englishLettersFrequency.add(new SimpleEntry<>('N', 0.06749));
        englishLettersFrequency.add(new SimpleEntry<>('O', 0.07507));
        englishLettersFrequency.add(new SimpleEntry<>('P', 0.01929));
        englishLettersFrequency.add(new SimpleEntry<>('Q', 0.00095));
        englishLettersFrequency.add(new SimpleEntry<>('R', 0.05987));
        englishLettersFrequency.add(new SimpleEntry<>('S', 0.06327));
        englishLettersFrequency.add(new SimpleEntry<>('T', 0.09056));
        englishLettersFrequency.add(new SimpleEntry<>('U', 0.02758));
        englishLettersFrequency.add(new SimpleEntry<>('V', 0.00978));
        englishLettersFrequency.add(new SimpleEntry<>('W', 0.0236));
        englishLettersFrequency.add(new SimpleEntry<>('X', 0.0015));
        englishLettersFrequency.add(new SimpleEntry<>('Y', 0.01974));
        englishLettersFrequency.add(new SimpleEntry<>('Z', 0.00074));
    }

    private static void initRussianList(){
        russianLettersFrequency.add(new SimpleEntry<>('А', 0.07821));
        russianLettersFrequency.add(new SimpleEntry<>('Б', 0.01732));
        russianLettersFrequency.add(new SimpleEntry<>('В', 0.04491));
        russianLettersFrequency.add(new SimpleEntry<>('Г', 0.01698));
        russianLettersFrequency.add(new SimpleEntry<>('Д', 0.03103));
        russianLettersFrequency.add(new SimpleEntry<>('Е', 0.08567));
        russianLettersFrequency.add(new SimpleEntry<>('Ё', 0.0007));
        russianLettersFrequency.add(new SimpleEntry<>('Ж', 0.01082));
        russianLettersFrequency.add(new SimpleEntry<>('З', 0.01647));
        russianLettersFrequency.add(new SimpleEntry<>('И', 0.06777));
        russianLettersFrequency.add(new SimpleEntry<>('Й', 0.01041));
        russianLettersFrequency.add(new SimpleEntry<>('К', 0.03215));
        russianLettersFrequency.add(new SimpleEntry<>('Л', 0.04813));
        russianLettersFrequency.add(new SimpleEntry<>('М', 0.03139));
        russianLettersFrequency.add(new SimpleEntry<>('Н', 0.0685));
        russianLettersFrequency.add(new SimpleEntry<>('О', 0.11394));
        russianLettersFrequency.add(new SimpleEntry<>('П', 0.02754));
        russianLettersFrequency.add(new SimpleEntry<>('Р', 0.04234));
        russianLettersFrequency.add(new SimpleEntry<>('С', 0.05382));
        russianLettersFrequency.add(new SimpleEntry<>('Т', 0.06443));
        russianLettersFrequency.add(new SimpleEntry<>('У', 0.02882));
        russianLettersFrequency.add(new SimpleEntry<>('Ф', 0.00132));
        russianLettersFrequency.add(new SimpleEntry<>('Х', 0.00833));
        russianLettersFrequency.add(new SimpleEntry<>('Ц', 0.00333));
        russianLettersFrequency.add(new SimpleEntry<>('Ч', 0.01645));
        russianLettersFrequency.add(new SimpleEntry<>('Ш', 0.00775));
        russianLettersFrequency.add(new SimpleEntry<>('Щ', 0.00331));
        russianLettersFrequency.add(new SimpleEntry<>('Ъ', 0.00023));
        russianLettersFrequency.add(new SimpleEntry<>('Ы', 0.01854));
        russianLettersFrequency.add(new SimpleEntry<>('Ь', 0.02106));
        russianLettersFrequency.add(new SimpleEntry<>('Э', 0.0031));
        russianLettersFrequency.add(new SimpleEntry<>('Ю', 0.00544));
        russianLettersFrequency.add(new SimpleEntry<>('Я', 0.01979));
    }

}
