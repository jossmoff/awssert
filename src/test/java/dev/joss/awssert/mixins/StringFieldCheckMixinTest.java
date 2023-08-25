package dev.joss.awssert.mixins;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringFieldCheckMixinTest {

  private final String FIELD_NAME = "test field";
  private StringFieldCheckMixin mixin;

  @BeforeEach
  public void initialize() {
    mixin = mock(StringFieldCheckMixin.class);
    when(mixin.hasFieldThatIsNotNullOrEmpty(any(), any())).thenCallRealMethod();
    when(mixin.hasFieldThatIsNullOrEmpty(any(), any())).thenCallRealMethod();
    when(mixin.hasFieldThatStartsWith(any(), any(), any())).thenCallRealMethod();
    when(mixin.hasFieldThatEndsWith(any(), any(), any())).thenCallRealMethod();
    when(mixin.hasFieldThatContains(any(), any(), any())).thenCallRealMethod();
    when(mixin.hasFieldThatHasLength(any(), anyInt(), any())).thenCallRealMethod();
    when(mixin.hasFieldThatMatchesRegex(any(), any(), any())).thenCallRealMethod();
  }

  static Stream<Arguments> invalidStringArguments() {
    return Stream.of(
        Arguments.of(null, "test"), Arguments.of("test", null), Arguments.of("test", "abc"));
  }



  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {""})
  void shouldReturnErrorMessageForInvalidActualOnNotNullOrEmptyCheck(String actual) {
    String expectedMessage =
        String.format("Expected %s to not be null or empty but was <%s>", FIELD_NAME, actual);
    Optional<String> actualErrorMessage = mixin.hasFieldThatIsNotNullOrEmpty(actual, FIELD_NAME);

    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForValidActualOnNotNullOrEmptyCheck() {
    String actual = "test";
    Optional<String> actualErrorMessage = mixin.hasFieldThatIsNotNullOrEmpty(actual, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @Test
  void shouldReturnErrorMessageForInvalidActualOnNullOrEmptyCheck() {
    String actual = "test";
    String expectedMessage =
        String.format("Expected %s to be null or empty but was <%s>", FIELD_NAME, actual);

    Optional<String> actualErrorMessage = mixin.hasFieldThatIsNullOrEmpty(actual, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {""})
  void shouldReturnEmptyMessageForValidActualOnNullOrEmptyCheck(String actual) {
    Optional<String> actualErrorMessage = mixin.hasFieldThatIsNullOrEmpty(actual, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @ParameterizedTest
  @MethodSource("invalidStringArguments")
  void shouldReturnErrorMessageForInvalidStartsWithCheck(String actual, String expected) {
    String expectedMessage =
        String.format("Expected %s to start with <%s> but was <%s>", FIELD_NAME, expected, actual);

    Optional<String> actualErrorMessage =
        mixin.hasFieldThatStartsWith(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForValidStartsWithCheck() {
    String actual = "test";
    String expected = "te";
    Optional<String> actualErrorMessage =
        mixin.hasFieldThatStartsWith(actual, expected, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @ParameterizedTest
  @MethodSource("invalidStringArguments")
  void shouldReturnErrorMessageForInvalidEndsWithCheck(String actual, String expected) {
    String expectedMessage =
            String.format("Expected %s to end with <%s> but was <%s>", FIELD_NAME, expected, actual);

    Optional<String> actualErrorMessage =
            mixin.hasFieldThatEndsWith(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForValidEndsWithCheck() {
    String actual = "test";
    String expected = "st";
    Optional<String> actualErrorMessage =
            mixin.hasFieldThatEndsWith(actual, expected, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @ParameterizedTest
  @MethodSource("invalidStringArguments")
  void shouldReturnErrorMessageForInvalidContainsCheck(String actual, String expected) {
    String expectedMessage =
            String.format("Expected %s to contain <%s> but was <%s>", FIELD_NAME, expected, actual);

    Optional<String> actualErrorMessage =
            mixin.hasFieldThatContains(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForValidContainsCheck() {
    String actual = "test";
    String expected = "es";
    Optional<String> actualErrorMessage =
            mixin.hasFieldThatContains(actual, expected, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @Test
  void shouldReturnErrorMessageForNullActualLengthCheck() {
    String actual = null;
    int expectedLength = 1;

    String expectedMessage =
            String.format("Expected %s to have length <%s> but was null", FIELD_NAME, expectedLength);

    Optional<String> actualErrorMessage =
            mixin.hasFieldThatHasLength(actual, expectedLength, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForInvalidLengthCheck() {
    String actual = "test";
    int expectedLength = actual.length() - 1;

    String expectedMessage =
            String.format("Expected %s to have length <%s> but was <%s>", FIELD_NAME, expectedLength, actual.length());

    Optional<String> actualErrorMessage =
            mixin.hasFieldThatHasLength(actual, expectedLength, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForValidLengthCheck() {
    String actual = "test";
    int expectedLength = actual.length();
    Optional<String> actualErrorMessage =
            mixin.hasFieldThatHasLength(actual, expectedLength, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @ParameterizedTest
  @MethodSource("invalidStringArguments")
  void shouldReturnErrorMessageForInvalidRegexCheck(String actual, String expected) {
    String expectedMessage =
            String.format("Expected %s to match regex <%s> but was <%s>", FIELD_NAME, expected, actual);

    Optional<String> actualErrorMessage =
            mixin.hasFieldThatMatchesRegex(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForRegexCheck() {
    String actual = "test";
    String expectedRegex = "t.*t";
    Optional<String> actualErrorMessage =
            mixin.hasFieldThatMatchesRegex(actual, expectedRegex, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }
}
