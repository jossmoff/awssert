package dev.joss.awssert.mixins;

import java.util.Optional;

public interface StringFieldCheckMixin extends BaseFieldCheckMixin {

  default Optional<String> hasFieldThatIsNullOrEmpty(String actual, String fieldName) {
    String errorMessage =
        String.format("Expected %s to be null or empty but was <%s>", fieldName, actual);
    if (actual != null && !actual.isEmpty()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatIsNotNullOrEmpty(String actual, String fieldName) {
    String errorMessage =
        String.format("Expected %s to not be null or empty but was <%s>", fieldName, actual);
    if (actual == null || actual.isEmpty()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatStartsWith(
      String actual, String expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to start with <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || !actual.startsWith(expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatEndsWith(String actual, String expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to end with <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || !actual.endsWith(expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatContains(String actual, String expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to contain <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || !actual.contains(expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatHasLength(String actual, int expected, String fieldName) {
    if (actual == null) {
      String errorMessage =
          String.format("Expected %s to have length <%s> but was null", fieldName, expected);
      return Optional.of(errorMessage);
    }
    if (actual.length() != expected) {
      String errorMessage =
          String.format(
              "Expected %s to have length <%s> but was <%s>", fieldName, expected, actual.length());
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatMatchesRegex(String actual, String regex, String fieldName) {
    String errorMessage =
        String.format("Expected %s to match regex <%s> but was <%s>", fieldName, regex, actual);
    if (actual == null || regex == null || !actual.matches(regex)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }
}
