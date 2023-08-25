package dev.joss.awssert.mixins;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseFieldCheckMixinTest {

  private final String FIELD_NAME = "test field";
  private BaseFieldCheckMixin mixin;

  @BeforeEach
  public void initialize() {
    mixin = mock(BaseFieldCheckMixin.class);
    when(mixin.hasFieldEqualToCheck(any(), any(), any())).thenCallRealMethod();
    when(mixin.hasFieldNotEqualToCheck(any(), any(), any())).thenCallRealMethod();
  }

  @Test
  void shouldReturnEmptyMessageForValidEqualityCheck() {
    String actual = "test";
    String expected = "test";

    Optional<String> actualErrorMessage = mixin.hasFieldEqualToCheck(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).isEmpty();
  }

  @Test
  void shouldThrowErrorMessageForInvalidEqualityCheck() {
    String actual = "test";
    Integer expected = 1;
    String expectedMessage =
        String.format("Expected %s to be equal to <%s> but was <%s>", FIELD_NAME, expected, actual);

    Optional<String> actualErrorMessage = mixin.hasFieldEqualToCheck(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }

  @Test
  void shouldReturnEmptyMessageForValidInEqualityCheck() {
    String actual = "test";
    Integer expected = 1;
    Optional<String> actualErrorMessage =
        mixin.hasFieldNotEqualToCheck(actual, expected, FIELD_NAME);

    assertThat(actualErrorMessage).isEmpty();
  }

  @Test
  void shouldThrowErrorMessageForInvalidInEqualityCheck() {
    String actual = "test";
    String expected = "test";

    String expectedMessage =
        String.format(
            "Expected %s to not be equal to <%s> but was <%s>", FIELD_NAME, expected, actual);

    Optional<String> actualErrorMessage =
        mixin.hasFieldNotEqualToCheck(actual, expected, FIELD_NAME);
    assertThat(actualErrorMessage).hasValue(expectedMessage);
  }
}
