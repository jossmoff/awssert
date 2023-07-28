package dev.joss.awssert.sfn;

import org.assertj.core.api.AbstractAssert;
import software.amazon.awssdk.services.sfn.model.GetExecutionHistoryResponse;
import software.amazon.awssdk.services.sfn.model.HistoryEvent;

public class GetExecutionHistoryResponseAssert
    extends AbstractAssert<GetExecutionHistoryResponseAssert, GetExecutionHistoryResponse> {
  private GetExecutionHistoryResponseAssert(GetExecutionHistoryResponse actual) {
    super(actual, GetExecutionHistoryResponseAssert.class);
  }

  public static GetExecutionHistoryResponseAssert assertThat(
      GetExecutionHistoryResponse actual) {
    return new GetExecutionHistoryResponseAssert(actual);
  }

  public GetExecutionHistoryResponseAssert hasEvents() {
    isNotNull();
    if (!actual.hasEvents()) {
      failWithMessage("Expected events to be present but was <%s>", actual.events());
    }
    return this;
  }

  public GetExecutionHistoryResponseAssert hasNoEvents() {
    isNotNull();
    if (actual.hasEvents()) {
      failWithMessage("Expected no events to be present but was <%s>", actual.events());
    }
    return this;
  }

  public GetExecutionHistoryResponseAssert hasEvent(HistoryEvent expectedEvent) {
    isNotNull();
    checkActualEventsContainsEvent(expectedEvent);
    return this;
  }

  public GetExecutionHistoryResponseAssert hasEvents(HistoryEvent... expectedEvents) {
    isNotNull();
    for (HistoryEvent expectedEvent : expectedEvents) {
      checkActualEventsContainsEvent(expectedEvent);
    }
    return this;
  }

  private void checkActualEventsContainsEvent(HistoryEvent event) {
    if (!actual.events().contains(event)) {
      failWithMessage("Expected event %s to be present but was not found", event);
    }
  }
}
