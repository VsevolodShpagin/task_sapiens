package guessing_game.core.number;

import guessing_game.core.Session;
import guessing_game.core.domain.GuessResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuessEvaluatorImplTest {

    @Mock
    private Session mockSession;

    @InjectMocks
    private GuessEvaluatorImpl evaluator;

    @BeforeEach
    void setupMock() {
        when(mockSession.getNumber()).thenReturn("1234");
    }

    @Test
    void evaluate_noMatch_return00() {
        GuessResult result = evaluator.evaluate("9876");
        assertEquals(0, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_oneMatch_return10() {
        GuessResult result = evaluator.evaluate("9816");
        assertEquals(1, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_onePlaceMatch_return01() {
        GuessResult result = evaluator.evaluate("1986");
        assertEquals(0, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_oneMatchOnePlaceMatch_return11() {
        GuessResult result = evaluator.evaluate("9216");
        assertEquals(1, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoMatch_return20() {
        GuessResult result = evaluator.evaluate("9812");
        assertEquals(2, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoPlaceMatch_return02() {
        GuessResult result = evaluator.evaluate("1936");
        assertEquals(0, result.getMatch());
        assertEquals(2, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoMatchTwoPlaceMatch_return2() {
        GuessResult result = evaluator.evaluate("1243");
        assertEquals(2, result.getMatch());
        assertEquals(2, result.getPlaceMatch());
    }

    @Test
    void evaluate_twoMatchOnePlaceMatch_return21() {
        GuessResult result = evaluator.evaluate("9432");
        assertEquals(2, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_oneMatchTwoPlaceMatch_return12() {
        GuessResult result = evaluator.evaluate("9231");
        assertEquals(1, result.getMatch());
        assertEquals(2, result.getPlaceMatch());
    }

    @Test
    void evaluate_threeMatch_return30() {
        GuessResult result = evaluator.evaluate("9123");
        assertEquals(3, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_threePlaceMatch_return03() {
        GuessResult result = evaluator.evaluate("1239");
        assertEquals(0, result.getMatch());
        assertEquals(3, result.getPlaceMatch());
    }

    @Test
    void evaluate_threeMatchOnePlaceMatch_return31() {
        GuessResult result = evaluator.evaluate("2314");
        assertEquals(3, result.getMatch());
        assertEquals(1, result.getPlaceMatch());
    }

    @Test
    void evaluate_FourMatch_return40() {
        GuessResult result = evaluator.evaluate("4321");
        assertEquals(4, result.getMatch());
        assertEquals(0, result.getPlaceMatch());
    }

    @Test
    void evaluate_FourPlaceMatch_return04() {
        GuessResult result = evaluator.evaluate("1234");
        assertEquals(0, result.getMatch());
        assertEquals(4, result.getPlaceMatch());
    }

}
