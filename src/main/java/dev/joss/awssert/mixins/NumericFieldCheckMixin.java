package dev.joss.awssert.mixins;

import java.util.Optional;

public interface NumericFieldCheckMixin extends BaseFieldCheckMixin {
  default Optional<String> hasFieldGreaterThanCheck(
      Number actual, Number expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to be greater than <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null) {
      return Optional.of(errorMessage);
    }
    if (actual.doubleValue() <= expected.doubleValue()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldLessThanCheck(Number actual, Number expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to be less than <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null) {
      return Optional.of(errorMessage);
    }
    if (actual.doubleValue() >= expected.doubleValue()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldLessThanOrEqualToCheck(
      Number actual, Number expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to be less than or equal to <%s> but was <%s>",
            fieldName, expected, actual);
    if (actual == null || expected == null) {
      return Optional.of(errorMessage);
    }
    if (actual.doubleValue() > expected.doubleValue()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldGreaterThanOrEqualToCheck(
      Number actual, Number expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to be greater than or equal to <%s> but was <%s>",
            fieldName, expected, actual);
    if (actual == null || expected == null) {
      return Optional.of(errorMessage);
    }
    if (actual.doubleValue() < expected.doubleValue()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldBetweenBound(
      Number actual, Number expectedLowerBound, Number expectedUpperBound, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to be between <%s> and <%s> but was <%s>",
            fieldName, expectedLowerBound, expectedUpperBound, actual);
    if (actual == null || expectedLowerBound == null || expectedUpperBound == null) {
      return Optional.of(errorMessage);
    }
    if (actual.doubleValue() < expectedLowerBound.doubleValue()
        || actual.doubleValue() > expectedUpperBound.doubleValue()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }
}
