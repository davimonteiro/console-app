import org.example.statistics.MostUsedWordStatistic;
import org.example.statistics.NumberWordStatistic;
import org.example.statistics.SpecialCharacterStatistic;
import org.example.statistics.StatisticComputer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatisticComputerTest {

    private String lines;

    private StatisticComputer statisticComputer;
    private String answer;

    private String expectedAnswer;

    @Test
    public void numberWordStatisticTest() {
        //Given
        givenLinesAsInput();
        givenNumberWordStatistic();
        givenTheExpectedAnswerForWordCounter();

        // When
        whenComputeStatistic();

        // Then
        thenVerifyTheAnswer();
    }

    @Test
    public void specialChaStatisticTest() {
        //Given
        givenLinesAsInput();
        givenSpecialCharacterStatistic();
        givenTheExpectedAnswerForSpecialCharacter();

        // When
        whenComputeStatistic();

        // Then
        thenVerifyTheAnswer();
    }

    @Test
    public void mostUsedWordTest() {
        //Given
        givenLinesAsInput();
        givenMostUsedWordStatistic();
        givenTheExpectedAnswerForMostUsedWord();

        // When
        whenComputeStatistic();

        // Then
        thenVerifyTheAnswer();
    }


    private void thenVerifyTheAnswer() {
        Assertions.assertEquals(answer, expectedAnswer);
    }

    private void whenComputeStatistic() {
        this.answer = statisticComputer.compute(lines);
    }

    private void givenMostUsedWordStatistic() {
        this.statisticComputer = new MostUsedWordStatistic();
    }

    private void givenSpecialCharacterStatistic() {
        this.statisticComputer = new SpecialCharacterStatistic();
    }

    private void givenNumberWordStatistic() {
        this.statisticComputer = new NumberWordStatistic();
    }

    private void givenLinesAsInput() {
        this.lines = "Lorem ipsum ipsum ipsum dolor sit amet.";
    }

    private void givenTheExpectedAnswerForMostUsedWord() {
        this.expectedAnswer = "ipsum";
    }

    private void givenTheExpectedAnswerForSpecialCharacter() {
        this.expectedAnswer = "1";
    }

    private void givenTheExpectedAnswerForWordCounter() {
        this.expectedAnswer = "7";
    }

}
