package dev.joss.awssert.mixins;

import java.util.Objects;
import java.util.Optional;

public interface BaseFieldCheckMixin {
  default Optional<String> hasFieldEqualToCheck(Object actual, Object expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to be equal to <%s> but was <%s>", fieldName, expected, actual);
    if ((actual == null ^ expected == null) || !Objects.equals(actual, expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldNotEqualToCheck(
      Object actual, Object expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to not be equal to <%s> but was <%s>", fieldName, expected, actual);
    if ((actual == null) == (expected == null) || Objects.equals(actual, expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }
}
