package org.example.statistics;

import org.apache.commons.lang3.StringUtils;

public class SpecialCharacterStatistic implements StatisticComputer {

    private static final String SPECIAL_CHARACTER = ".";

    @Override
    public String compute(String lines) {
        int counter = StringUtils.countMatches(lines, SPECIAL_CHARACTER);
        return Integer.toString(counter);
    }

}
