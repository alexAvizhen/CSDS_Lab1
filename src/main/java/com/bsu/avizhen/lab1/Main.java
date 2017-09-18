package com.bsu.avizhen.lab1;

import com.bsu.avizhen.lab1.analizers.FrequencyAnalyzer;
import com.bsu.avizhen.lab1.analizers.KasiskiAnalyzer;
import com.bsu.avizhen.lab1.crypters.VigenereCrypter;
import com.bsu.avizhen.lab1.utils.AnalyzePair;
import com.bsu.avizhen.lab1.utils.Language;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 17.09.2017.
 */
public class Main {

    public static String KEY_WORD_EN = "MOUSEABC";

    private static List<String> keyWords = new ArrayList<>();

    static {
        keyWords.add("KEY");
        keyWords.add("WORD");
        keyWords.add("MOUSE");
        keyWords.add("LETTER");
        keyWords.add("AMOUNTS");
        keyWords.add("INDICATE");
        keyWords.add("COMMONSENSE");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("src\\main\\resources\\input.txt")));
        FileWriter fileWriter = new FileWriter(new File("src\\main\\resources\\output.txt"));
        StringBuilder inputText = new StringBuilder();
        String line;
        while ((line = fileReader.readLine()) != null) {
            inputText.append(line);
        }
        fileWriter.write("Text length: " + inputText.length() + "\n");
        VigenereCrypter vigenereCrypter = new VigenereCrypter(Language.ENGLISH);
        KasiskiAnalyzer analyzer = new KasiskiAnalyzer(3);
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer(Language.ENGLISH);
        for (String keyWord : keyWords) {
            String encryptText = vigenereCrypter.encryptText(inputText.toString(), keyWord);
            fileWriter.write("Key word length(Original): " + keyWord.length() +"\n");
            fileWriter.write("Key word length(Program):\n");
            List<AnalyzePair> analyzePairs = analyzer.analyzeKeyWordLength(encryptText);
            int keyWordLength = analyzePairs.get(0).getIndex();
            if (keyWordLength == 1) {
                keyWordLength = analyzePairs.get(1).getIndex();
            }
            for (AnalyzePair analyzePair : analyzer.analyzeKeyWordLength(encryptText)) {
                fileWriter.write(analyzePair.getIndex() + " - " + analyzePair.getValue() + "\n");
            }
            fileWriter.write("Key word(original): " + keyWord +"\n");
            String programKeyWord = frequencyAnalyzer.analyzeKeyWord(encryptText, keyWordLength);
            fileWriter.write("Key word(program): " + programKeyWord + "\n");
            int commonChars = 0;
            for (int i = 0; i < keyWord.length(); i++) {
                if (i < programKeyWord.length() && keyWord.charAt(i) == programKeyWord.charAt(i)) {
                    commonChars++;
                }
            }
            fileWriter.write("Common chars: " + (double) commonChars / (double) keyWord.length() + "\n\n");

        }

        fileReader.close();
        fileWriter.close();
    }


}
