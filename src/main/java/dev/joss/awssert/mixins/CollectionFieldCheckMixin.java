package dev.joss.awssert.mixins;

import java.util.Collection;
import java.util.Optional;

public interface CollectionFieldCheckMixin extends BaseFieldCheckMixin {

  default Optional<String> hasFieldThatIsEmpty(Collection<?> actual, String fieldName) {
    String errorMessage = String.format("Expected %s to be empty but was <%s>", fieldName, actual);
    if (actual == null || !actual.isEmpty()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldThatIsNotEmpty(Collection<?> actual, String fieldName) {
    String errorMessage =
        String.format("Expected %s to not be empty but was <%s>", fieldName, actual);
    if (actual == null || actual.isEmpty()) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldContaining(
      Collection<?> actual, Object expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to contain <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || !actual.contains(expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldNotContaining(
      Collection<?> actual, Object expected, String fieldName) {
    String errorMessage =
        String.format("Expected %s to not contain <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || actual.contains(expected)) {
      return Optional.of(errorMessage);
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldContainingAnyOf(
      Collection<?> actual, Collection<?> expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to contain any of <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || expected.isEmpty()) {
      return Optional.of(errorMessage);
    }
    for (Object expectedItem : expected) {
      if (actual.contains(expectedItem)) {
        return Optional.empty();
      }
    }
    return Optional.of(errorMessage);
  }

  default Optional<String> hasFieldContainingAllOf(
      Collection<?> actual, Collection<?> expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to contain all of <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || expected.isEmpty()) {
      return Optional.of(errorMessage);
    }
    for (Object expectedItem : expected) {
      if (!actual.contains(expectedItem)) {
        return Optional.of(errorMessage);
      }
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldContainingExactly(
      Collection<?> actual, Collection<?> expected, String fieldName) {
    String errorMessage =
        String.format(
            "Expected %s to contain exactly <%s> but was <%s>", fieldName, expected, actual);
    if (actual == null || expected == null || expected.isEmpty()) {
      return Optional.of(errorMessage);
    }
    if (actual.size() != expected.size()) {
      return Optional.of(errorMessage);
    }
    for (Object expectedItem : expected) {
      if (!actual.contains(expectedItem)) {
        return Optional.of(errorMessage);
      }
    }
    return Optional.empty();
  }

  default Optional<String> hasFieldContainingNoDuplicates(Collection<?> actual, String fieldName) {
    String errorMessage =
        String.format("Expected %s to contain no duplicates but was <%s>", fieldName, actual);
    if (actual == null) {
      return Optional.of(errorMessage);
    }
    for (Object item : actual) {
      if (actual.stream().filter(i -> i.equals(item)).count() > 1) {
        return Optional.of(errorMessage);
      }
    }
    return Optional.empty();
  }
}
