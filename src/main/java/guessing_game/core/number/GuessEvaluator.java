package guessing_game.core.number;

import guessing_game.core.domain.GuessResult;

public interface GuessEvaluator {

    GuessResult evaluate(String guess);

}
