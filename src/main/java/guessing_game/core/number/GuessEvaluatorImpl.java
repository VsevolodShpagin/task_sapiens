package guessing_game.core.number;

import guessing_game.core.domain.GuessResult;
import guessing_game.core.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class GuessEvaluatorImpl implements GuessEvaluator {

    private static final char NO_MATCH = 'x';
    private static final char MATCH = 'm';
    private static final char PLACE_MATCH = 'p';

    @Autowired
    private SessionRepository sessions;

    @Override
    public GuessResult evaluate(String guess, String number) {
        List<Character> guessAsList = guess.chars().mapToObj(c -> (char) c).toList();
        List<Character> result =
                IntStream.range(0, guessAsList.size()).mapToObj(i -> checkDigit(guessAsList.get(i), i, number)).toList();
        int matchCount = Math.toIntExact(result.stream().filter(c -> c.equals('m')).count());
        int placeMatchCount = Math.toIntExact(result.stream().filter(c -> c.equals('p')).count());
        return new GuessResult(matchCount, placeMatchCount);
    }

    private char checkDigit(int digit, int index, String number) {
        char c = (char) digit;
        char result = NO_MATCH;
        if (number.indexOf(c) > -1 && number.indexOf(c) == index) {
            result = PLACE_MATCH;
        } else if (number.indexOf(c) > -1) {
            result = MATCH;
        }
        return result;
    }

}
