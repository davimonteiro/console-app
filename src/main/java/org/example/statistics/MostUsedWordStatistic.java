package org.example.statistics;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedWordStatistic implements StatisticComputer {

    @Override
    public String compute(String lines) {
        Map<String, Long> wordFrequency = new HashMap<>();

        StringTokenizer tokenizer = new StringTokenizer(lines);
        List<String> words = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken());
        }

        Map<String, Long> group = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        group.forEach((word, frequency) -> {
            wordFrequency.computeIfPresent(word, (key, value) -> value + frequency);
            wordFrequency.computeIfAbsent(word, key -> frequency);
        });

        String mostUsedWord = "";
        Long max = 0L;
        for (var entry : wordFrequency.entrySet()) {
            String word = entry.getKey();
            Long frequency = entry.getValue();

            if (frequency > max) {
                max = frequency;
                mostUsedWord = word;
            }
        }

        return mostUsedWord;
    }

}
