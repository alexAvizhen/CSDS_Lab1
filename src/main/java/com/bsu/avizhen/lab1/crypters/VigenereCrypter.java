package com.bsu.avizhen.lab1.crypters;

import com.bsu.avizhen.lab1.utils.Language;

/**
 * Created by Александр on 17.09.2017.
 */
public class VigenereCrypter {
    private Language language = Language.ENGLISH;
    private char startSymbol = Language.ENGLISH.getStartSymbol();
    private int amountOfSymbolsInLang = Language.ENGLISH.getAmountOfSymbolsInLang();

    public VigenereCrypter(Language language) {
        this.language = language;
        this.startSymbol = language.getStartSymbol();
        this.amountOfSymbolsInLang = language.getAmountOfSymbolsInLang();
    }

    public  String encryptText(String inputText, String keyWord) {
        String tempInputText = inputText.toUpperCase();
        StringBuilder resultUpperText = new StringBuilder();
        char[][] encryptTable = generateVigenereTable(keyWord, startSymbol, amountOfSymbolsInLang);
        for (int i = 0; i < tempInputText.length(); i++) {
            int columnNumber = tempInputText.charAt(i) - startSymbol;
            if (columnNumber >= 0 && columnNumber <= amountOfSymbolsInLang) {
                resultUpperText.append(encryptTable[i % keyWord.length()][columnNumber]);
            } else {
                resultUpperText.append(tempInputText.charAt(i));
            }
        }
        return restoreSymbolsCase(resultUpperText.toString(), getLowerCaseStates(inputText));
    }

    public  String decryptText(String encryptText, String keyWord) {
        String tempEncryptText = encryptText.toUpperCase();
        StringBuilder resultUpperText = new StringBuilder();
        char[][] encryptTable = generateVigenereTable(keyWord, startSymbol, amountOfSymbolsInLang);
        for (int i = 0; i < encryptText.length(); i++) {
            int row = i % keyWord.length();
            int k = 0;
            while (k < amountOfSymbolsInLang && encryptTable[row][k] != tempEncryptText.charAt(i)) {
                k++;
            }
            if (k >= amountOfSymbolsInLang) {
                resultUpperText.append(tempEncryptText.charAt(i));
            } else {
                char c = (char) (startSymbol + k);
                resultUpperText.append(c);
            }
        }
        return restoreSymbolsCase(resultUpperText.toString(), getLowerCaseStates(encryptText));
    }

    public  char[][] generateVigenereTable(String keyWord, char startSymbol, int amountOfSymbolsInLang) {
        char[][] resultTable = new char[keyWord.length()][amountOfSymbolsInLang];
        for (int i = 0; i < keyWord.length(); i++) {
            for (int j = 0; j < amountOfSymbolsInLang; j++) {
                resultTable[i][j] = (char) (startSymbol + (keyWord.charAt(i) - startSymbol + j) % amountOfSymbolsInLang);
            }
        }
        return resultTable;
    }

    private  String restoreSymbolsCase(String text, boolean[] lowerCaseStates) {
        StringBuilder result = new StringBuilder(text);
        for (int i = 0; i < lowerCaseStates.length; i++) {
            if (lowerCaseStates[i]) {
                result.setCharAt(i, Character.toLowerCase(result.charAt(i)));
            }
        }
        return result.toString();
    }

    private  boolean[] getLowerCaseStates(String inputText) {
        boolean[] resultArr = new boolean[inputText.length()];
        for (int i = 0; i < inputText.length(); i++) {
            if (Character.isLowerCase(inputText.charAt(i))) {
                resultArr[i] = true;
            }
        }
        return resultArr;
    }
}
