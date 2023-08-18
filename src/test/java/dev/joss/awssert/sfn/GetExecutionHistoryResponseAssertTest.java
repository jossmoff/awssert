package dev.joss.awssert.sfn;

import static dev.joss.awssert.sfn.GetExecutionHistoryResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import software.amazon.awssdk.services.sfn.model.GetExecutionHistoryResponse;
import software.amazon.awssdk.services.sfn.model.HistoryEvent;

class GetExecutionHistoryResponseAssertTest {

  @Test
  void checkHasSomeEventsAssertionPassesWithSomeEvents() {
    HistoryEvent event = HistoryEvent.builder().build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event).build();

    assertThat(actual).hasSomeEvents();
  }

  @Test
  void checkHasSomeEventsAssertionFailsWithoutAnyEvents() {
    GetExecutionHistoryResponse actual = GetExecutionHistoryResponse.builder().build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasSomeEvents());

    String expectedMessage = "Expected events to be present but was <[]>";
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasNoEventsAssertionPassesWithNoEvents() {
    GetExecutionHistoryResponse actual = GetExecutionHistoryResponse.builder().build();

    assertThat(actual).hasNoEvents();
  }

  @Test
  void checkHasNoEventsAssertionFailsWithSomeEvents() {
    HistoryEvent event = HistoryEvent.builder().build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event).build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasNoEvents());

    String expectedMessage =
        String.format("Expected no events to be present but was <[%s]>", event);
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasSomeEventAssertionPassesWithSpecificEvent() {
    HistoryEvent event = HistoryEvent.builder().build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event).build();

    assertThat(actual).hasEvent(event);
  }

  @Test
  void checkHasSomeEventAssertionPassesWithWrongEvent() {
    HistoryEvent event = HistoryEvent.builder().build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event).build();
    HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).hasEvent(notPresentEvent));

    String expectedMessage =
        String.format(
            "Expected event %s to be present but was not found", notPresentEvent);
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasSomeEventsAssertionPassesWithSpecificEvents() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();

    assertThat(actual).hasEvents(event1, event2);
  }

  @Test
  void checkHasSomeEventsAssertionPassesWithWrongEvent() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();
    HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

    AssertionError assertionError1 =
        failsAssertion(() -> assertThat(actual).hasEvents(event1, notPresentEvent));
    String expectedMessage1 =
        String.format(
            "Expected event %s to be present but was not found", notPresentEvent);
    assertThat(assertionError1.getMessage()).isEqualTo(expectedMessage1);

    AssertionError assertionError2 =
        failsAssertion(() -> assertThat(actual).hasEvents(event1, notPresentEvent));
    String expectedMessage2 =
        String.format(
            "Expected event %s to be present but was not found", notPresentEvent);
    assertThat(assertionError2.getMessage()).isEqualTo(expectedMessage2);
  }

  public AssertionError failsAssertion(Executable executable) {
    return assertThrows(AssertionError.class, executable);
  }
}
