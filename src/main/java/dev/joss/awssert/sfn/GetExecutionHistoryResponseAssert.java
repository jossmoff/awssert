package dev.joss.awssert.sfn;

import dev.joss.awssert.mixins.CollectionFieldCheckMixin;
import dev.joss.awssert.mixins.StringFieldCheckMixin;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.AbstractAssert;
import software.amazon.awssdk.services.sfn.model.GetExecutionHistoryResponse;
import software.amazon.awssdk.services.sfn.model.HistoryEvent;

public class GetExecutionHistoryResponseAssert
    extends AbstractAssert<GetExecutionHistoryResponseAssert, GetExecutionHistoryResponse>
    implements CollectionFieldCheckMixin, StringFieldCheckMixin {

  private final String EVENTS_FIELD_NAME = "events";
  private final String TOKEN_FIELD_NAME = "nextToken";

  private GetExecutionHistoryResponseAssert(GetExecutionHistoryResponse actual) {
    super(actual, GetExecutionHistoryResponseAssert.class);
  }

  public static GetExecutionHistoryResponseAssert assertThat(GetExecutionHistoryResponse actual) {
    return new GetExecutionHistoryResponseAssert(actual);
  }

  public GetExecutionHistoryResponseAssert hasSomeEvents() {
    isNotNull();
    Optional<String> errorMessage = hasFieldThatIsNotEmpty(actual.events(), EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasNoEvents() {
    isNotNull();
    Optional<String> errorMessage = hasFieldThatIsEmpty(actual.events(), EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasEvent(HistoryEvent expectedEvent) {
    isNotNull();
    checkActualEventsContainsEvent(expectedEvent);
    return this;
  }

  public GetExecutionHistoryResponseAssert doesNotHaveEvent(HistoryEvent expectedEvent) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldNotContaining(actual.events(), expectedEvent, EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasAnyEvents(HistoryEvent... expectedEvents) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldContainingAnyOf(actual.events(), List.of(expectedEvents), EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasAllEvents(HistoryEvent... expectedEvents) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldContainingAllOf(actual.events(), List.of(expectedEvents), EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasExactlyEvents(HistoryEvent... expectedEvents) {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldContainingExactly(actual.events(), List.of(expectedEvents), EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasNoDuplicateEvents() {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldContainingNoDuplicates(actual.events(), EVENTS_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasNextToken() {
    isNotNull();
    Optional<String> errorMessage =
        hasFieldThatIsNotNullOrEmpty(actual.nextToken(), TOKEN_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  public GetExecutionHistoryResponseAssert doesNotHaveNextToken() {
    isNotNull();
    Optional<String> errorMessage = hasFieldThatIsNullOrEmpty(actual.nextToken(), TOKEN_FIELD_NAME);
    errorMessage.ifPresent(this::failWithMessage);
    return this;
  }

  private void checkActualEventsContainsEvent(HistoryEvent event) {
    if (!actual.events().contains(event)) {
      failWithMessage("Expected event %s to be present but was not found", event);
    }
  }
}
