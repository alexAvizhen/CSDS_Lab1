package com.bsu.avizhen.lab1.analizers;

import com.bsu.avizhen.lab1.utils.AnalyzePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 17.09.2017.
 */
public class KasiskiAnalyzer {
    private int diagramLength = 3;

    public KasiskiAnalyzer(int diagramLength) {
        this.diagramLength = diagramLength;
    }

    public List<AnalyzePair> analyzeKeyWordLength(String inputEncryptText) {
        String encryptText = inputEncryptText.toUpperCase();
        List<Integer> repeatCountList = new ArrayList<>();
        for (int i = 0; i <= encryptText.length() - diagramLength; i++) {
            String temp1 = encryptText.substring(i, i + diagramLength);
            for (int j = i + 1; j <= encryptText.length() - diagramLength; j++) {
                String temp2 = encryptText.substring(j, j + diagramLength);
                if (temp1.equals(temp2)) {
                    repeatCountList.add(j - i);
                }
            }
        }
        int[] nods = new int[5000];
        for (int i = 0; i < repeatCountList.size(); i++) {
            for (int j = i + 1; j < repeatCountList.size(); j++) {
                int index = gcd(repeatCountList.get(i), repeatCountList.get(j));
                if (index < nods.length) {
                    nods[index]++;
                }
            }
        }
        nods[0] = 0;
        List<AnalyzePair> result = new ArrayList<>();
        for (int i = 0; i < nods.length; i++) {
            result.add(new AnalyzePair(i, nods[i]));
        }
        result.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return result.subList(0, 5);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
