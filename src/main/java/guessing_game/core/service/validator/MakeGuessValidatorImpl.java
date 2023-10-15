package guessing_game.core.service.validator;

import guessing_game.core.converter.NumberConverter;
import guessing_game.core.request.MakeGuessRequest;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.errorProcessor.ErrorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static guessing_game.core.number.NumberGeneratorFourDigitImpl.SECRET_NUMBER_LENGTH;

@Component
public class MakeGuessValidatorImpl implements MakeGuessValidator {

    private static final String REGEX_NUMBER = "[0-9]+";
    private static final String REGEX_REPEATING = "^.*(.).*\\1.*$";

    private static final String GUESS_BLANK = "GUESS_BLANK";
    private static final String GUESS_NOT_NUMBER = "GUESS_NOT_NUMBER";
    private static final String GUESS_WRONG_LENGTH = "GUESS_WRONG_LENGTH";
    private static final String GUESS_START_0 = "GUESS_START_0";
    private static final String GUESS_REPEATING = "GUESS_REPEATING";

    @Autowired
    private NumberConverter numberConverter;
    @Autowired
    private ErrorProcessor errorProcessor;

    @Override
    public List<ResponseError> validate(MakeGuessRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        validateGuessIsPresent(request).ifPresent(errors::add);
        String number = numberConverter.toString(request.getGuess());
        if (errors.size() == 0) validateIsNumber(number).ifPresent(errors::add);
        if (errors.size() == 0) validateLength(number).ifPresent(errors::add);
        if (errors.size() == 0) validateStartWithNonZero(number).ifPresent(errors::add);
        if (errors.size() == 0) validateNoDuplicates(number).ifPresent(errors::add);
        return errors;
    }

    private Optional<ResponseError> validateGuessIsPresent(MakeGuessRequest request) {
        return (request.getGuess() == null)
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(GUESS_BLANK)))
                : Optional.empty();
    }

    private Optional<ResponseError> validateIsNumber(String number) {
        return (number == null || number.isBlank() || !number.matches(REGEX_NUMBER))
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(GUESS_NOT_NUMBER)))
                : Optional.empty();
    }

    private Optional<ResponseError> validateLength(String number) {
        return (number == null || number.isBlank() || number.trim().length() != SECRET_NUMBER_LENGTH)
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(GUESS_WRONG_LENGTH) + " " + SECRET_NUMBER_LENGTH))
                : Optional.empty();
    }

    private Optional<ResponseError> validateStartWithNonZero(String number) {
        return (number == null || number.isBlank() || String.valueOf(number.charAt(0)).equals("0"))
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(GUESS_START_0)))
                : Optional.empty();
    }

    private Optional<ResponseError> validateNoDuplicates(String number) {
        return (number == null || number.isBlank() || number.matches(REGEX_REPEATING))
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(GUESS_REPEATING)))
                : Optional.empty();
    }

}
