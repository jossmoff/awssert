package dev.joss.awssert.s3;

import dev.joss.awssert.mixins.StringFieldCheckMixin;
import java.util.Optional;
import org.assertj.core.api.AbstractAssert;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;

public class CreateBucketResponseAssert
    extends AbstractAssert<CreateBucketResponseAssert, CreateBucketResponse>
    implements StringFieldCheckMixin {

  private final String LOCATION_FIELD_NAME = "location";

  private CreateBucketResponseAssert(CreateBucketResponse actual) {
    super(actual, CreateBucketResponseAssert.class);
  }

  public static CreateBucketResponseAssert assertThat(CreateBucketResponse actual) {
    return new CreateBucketResponseAssert(actual);
  }

  public CreateBucketResponseAssert hasLocation(String expected) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldEqualToCheck(actual.location(), expected, LOCATION_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public CreateBucketResponseAssert hasLocationStartingWith(String expectedPrefix) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldThatStartsWith(actual.location(), expectedPrefix, LOCATION_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public CreateBucketResponseAssert hasLocationEndingWith(String expectedSuffix) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldThatEndsWith(actual.location(), expectedSuffix, LOCATION_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public CreateBucketResponseAssert hasLocationContaining(String expectedSubstring) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldThatContains(actual.location(), expectedSubstring, LOCATION_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public CreateBucketResponseAssert hasLocationMatching(String expectedRegex) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldThatMatchesRegex(actual.location(), expectedRegex, LOCATION_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }
}
