package dev.joss.awssert.s3;

import static dev.joss.awssert.s3.CreateBucketResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;

class CreateBucketResponseAssertTest {

  @Test
  void checkHasLocationPassesWithValidLocation() {
    String location = "/very/valid/s3-location";
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    assertThat(actual).hasLocation(location);
  }

  @Test
  void checkHasLocationFailsWithoutLocation() {
    String location = "/very/valid/s3-location";
    CreateBucketResponse actual = CreateBucketResponse.builder().build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasLocation(location));
    String expectedMessage =
        String.format(
            "Expected location to be equal to <%s> but was <%s>", location, actual.location());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasLocationFailsWithInvalidLocation() {
    String location = "/very/valid/s3-location";
    CreateBucketResponse actual =
        CreateBucketResponse.builder().location("/not-the-actual-location").build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasLocation(location));
    String expectedMessage =
        String.format(
            "Expected location to be equal to <%s> but was <%s>", location, actual.location());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasLocationStartingWithPassesWithValidInput() {
    String location = "/very/valid/s3-location";
    String locationPrefix = location.substring(0, 3);
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    assertThat(actual).hasLocationStartingWith(locationPrefix);
  }

  @Test
  void checkHasLocationStartingWithFailsWithInvalidInput() {
    String location = "/very/valid/s3-location";
    String locationPrefix = "not-the-actual-prefix";
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).hasLocationStartingWith(locationPrefix));
    String expectedMessage =
        String.format(
            "Expected location to start with <%s> but was <%s>", locationPrefix, actual.location());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasLocationEndingWithPassesWithValidInput() {
    String location = "/very/valid/s3-location";
    String locationSuffix = location.substring(3);
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    assertThat(actual).hasLocationEndingWith(locationSuffix);
  }

  @Test
  void checkHasLocationEndingWithFailsWithInvalidInput() {
    String location = "/very/valid/s3-location";
    String locationSuffix = "not-the-suffix";
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).hasLocationEndingWith(locationSuffix));
    String expectedMessage =
        String.format(
            "Expected location to end with <%s> but was <%s>", locationSuffix, actual.location());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasLocationContainingPassesWithValidInput() {
    String location = "/very/valid/s3-location";
    String locationSubstring = location.substring(3, 13);
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    assertThat(actual).hasLocationContaining(locationSubstring);
  }

  @Test
  void checkHasLocationContainingFailsWithInvalidInput() {
    String location = "/very/valid/s3-location";
    String locationSubstring = "not-a-substring";
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).hasLocationContaining(locationSubstring));
    String expectedMessage =
        String.format(
            "Expected location to contain <%s> but was <%s>", locationSubstring, actual.location());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasLocationMatchingPassesWithValidInput() {
    String location = "/very/valid/s3-location";
    String locationRegex = "/very/valid/.*";
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    assertThat(actual).hasLocationMatching(locationRegex);
  }

  @Test
  void checkHasLocationMatchingFailsWithInvalidInput() {
    String location = "/very/valid/s3-location";
    String locationRegex = "not-a-matching-regex";
    CreateBucketResponse actual = CreateBucketResponse.builder().location(location).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).hasLocationMatching(locationRegex));
    String expectedMessage =
        String.format(
            "Expected location to match regex <%s> but was <%s>", locationRegex, actual.location());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  public AssertionError failsAssertion(Executable executable) {
    return assertThrows(AssertionError.class, executable);
  }
}
