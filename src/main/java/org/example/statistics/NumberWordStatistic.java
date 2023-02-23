package org.example.statistics;

import java.util.StringTokenizer;

public class NumberWordStatistic implements StatisticComputer {

    @Override
    public String compute(String lines) {
        StringTokenizer stringTokenizer = new StringTokenizer(lines);
        int wordCount = stringTokenizer.countTokens();
        return Integer.toString(wordCount);
    }

}
