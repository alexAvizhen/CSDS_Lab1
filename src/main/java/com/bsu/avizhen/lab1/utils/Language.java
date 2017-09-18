package com.bsu.avizhen.lab1.utils;

/**
 * Created by Александр on 17.09.2017.
 */
public enum Language {
    ENGLISH('A', 26),
    RUSSIAN('А', 32);

    Language(char startSymbol, int amountOfSymbolsInLang) {
        this.startSymbol = startSymbol;
        this.amountOfSymbolsInLang = amountOfSymbolsInLang;
    }

    char startSymbol;
    int amountOfSymbolsInLang;

    public char getStartSymbol() {
        return startSymbol;
    }

    public int getAmountOfSymbolsInLang() {
        return amountOfSymbolsInLang;
    }
}
