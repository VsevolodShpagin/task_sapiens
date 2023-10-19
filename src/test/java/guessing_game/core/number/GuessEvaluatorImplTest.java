package guessing_game.core.number;

import guessing_game.core.domain.GuessResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuessEvaluatorImplTest {

    private final GuessEvaluator evaluator = new GuessEvaluatorImpl();

    @Test
    void evaluate_noMatch_return00() {
        GuessResult result = evaluator.evaluate("9876", "1234");
        assertEquals(0, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_oneMatch_return10() {
        GuessResult result = evaluator.evaluate("9816", "1234");
        assertEquals(1, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_onePlaceMatch_return01() {
        GuessResult result = evaluator.evaluate("1986", "1234");
        assertEquals(0, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_oneMatchOnePlaceMatch_return11() {
        GuessResult result = evaluator.evaluate("9216", "1234");
        assertEquals(1, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoMatch_return20() {
        GuessResult result = evaluator.evaluate("9812", "1234");
        assertEquals(2, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoPlaceMatch_return02() {
        GuessResult result = evaluator.evaluate("1936", "1234");
        assertEquals(0, result.getMatch());
        assertEquals(2, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoMatchTwoPlaceMatch_return2() {
        GuessResult result = evaluator.evaluate("1243", "1234");
        assertEquals(2, result.getMatch());
        assertEquals(2, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoMatchOnePlaceMatch_return21() {
        GuessResult result = evaluator.evaluate("9432", "1234");
        assertEquals(2, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_oneMatchTwoPlaceMatch_return12() {
        GuessResult result = evaluator.evaluate("9231", "1234");
        assertEquals(1, result.getMatch());
        assertEquals(2, result.getPlaceMatch());
    }

    @Test
    void evaluate_threeMatch_return30() {
        GuessResult result = evaluator.evaluate("9123", "1234");
        assertEquals(3, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_threePlaceMatch_return03() {
        GuessResult result = evaluator.evaluate("1239", "1234");
        assertEquals(0, result.getMatch());
        assertEquals(3, result.getPlaceMatch());
    }

    @Test
    void evaluate_threeMatchOnePlaceMatch_return31() {
        GuessResult result = evaluator.evaluate("2314", "1234");
        assertEquals(3, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_FourMatch_return40() {
        GuessResult result = evaluator.evaluate("4321", "1234");
        assertEquals(4, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_FourPlaceMatch_return04() {
        GuessResult result = evaluator.evaluate("1234", "1234");
        assertEquals(0, result.getMatch());
        assertEquals(4, result.getPlaceMatch());
    }

}
