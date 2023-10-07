package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Numbers {

    public static final int LENGTH = 3;

    private final List<Number_> numbers;

    public Numbers(List<Number_> numbers) {
        validateNumbersLength(numbers);
        this.numbers = numbers;
    }

    private void validateNumbersLength(List<Number_> numbers) {
        int length = numbers.size();
        if (length != LENGTH) {
            throw new IllegalArgumentException(LENGTH + "개의 수가 주어져야 합니다.");
        }
    }

    private HashMap<Integer, Integer> getNumberCountsFromArray(List<Integer> values) {
        HashMap<Integer, Integer> balls = new HashMap<>();
        for (int value : values){
            int numberCountOfCurrentValue = balls.getOrDefault(value, 0);
            balls.put(value, numberCountOfCurrentValue + 1);
        }
        return balls;
    }

    public Score getScore(Numbers answerNumbers) {
        int strikeCount = getCountOfSameNumberAtSameIndex(answerNumbers);
        int strikeOrBallCount = getCountOfSameNumberAtAnyWhere(answerNumbers);
        int ballCount = strikeOrBallCount - strikeCount;
        int outCount = LENGTH - strikeOrBallCount;
        return new Score(strikeCount, ballCount, outCount);
    }

    // Strike
    public int getCountOfSameNumberAtSameIndex(Numbers anotherNumbers) {
        int result = 0;
        for (int i=0; i<LENGTH; i++) {
            int number = numbers.get(i);
            int anotherNumber = anotherNumbers.numbers.get(i);
            if (number == anotherNumber) {
                result++;
            }
        }
        return result;
    }

    // Ball or Strike
    public int getCountOfSameNumberAtAnyWhere(Numbers anotherNumbers) {
        int result = 0;
        for (Map.Entry<Integer, Integer> numberCount : numberCounts.entrySet()) {
            int number = numberCount.getKey();
            int count = numberCount.getValue();
            int anotherNumbersCurrentNumberCount = anotherNumbers.getCountOfNumber(number);
            result += Math.min(count, anotherNumbersCurrentNumberCount);
        }
        return result;
    }

    public int getCountOfNumber(int number) {
        return numberCounts.getOrDefault(number, 0);
    }
}
