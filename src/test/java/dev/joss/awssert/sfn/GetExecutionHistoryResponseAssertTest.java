package dev.joss.awssert.sfn;

import static dev.joss.awssert.sfn.GetExecutionHistoryResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
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

    String expectedMessage = "Expected events to not be empty but was <[]>";
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

    String expectedMessage = String.format("Expected events to be empty but was <[%s]>", event);
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
  void checkHasSomeEventAssertionFailsWithWrongEvent() {
    HistoryEvent event = HistoryEvent.builder().build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event).build();
    HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).hasEvent(notPresentEvent));

    String expectedMessage =
        String.format("Expected event %s to be present but was not found", notPresentEvent);
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkDoesNotHaveEventAssertionPassesWithSpecificEvent() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1).build();

    assertThat(actual).doesNotHaveEvent(event2);
  }

  @Test
  void checkDoesNotHaveEventAssertionFailsWithWrongEvent() {
    HistoryEvent event = HistoryEvent.builder().id(1L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event).build();

    AssertionError assertionError =
        failsAssertion(() -> assertThat(actual).doesNotHaveEvent(event));

    String expectedMessage =
        String.format("Expected events to not contain <%s> but was <%s>", event, actual.events());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasAnyEventsAssertionPassesWithSpecificEvents() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();

    assertThat(actual).hasAnyEvents(event1);
    assertThat(actual).hasAnyEvents(event2);
    assertThat(actual).hasAnyEvents(event1, event2);
  }

  @Test
  void checkHasAnyEventsAssertionFailsWithWrongEvent() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1).build();
    HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

    AssertionError assertionError1 =
        failsAssertion(() -> assertThat(actual).hasAnyEvents(notPresentEvent));
    String expectedMessage1 =
        String.format(
            "Expected events to contain any of <%s> but was <%s>",
            List.of(notPresentEvent), actual.events());
    assertThat(assertionError1.getMessage()).isEqualTo(expectedMessage1);

    AssertionError assertionError2 =
        failsAssertion(() -> assertThat(actual).hasAnyEvents(notPresentEvent));
    String expectedMessage2 =
        String.format(
            "Expected events to contain any of <%s> but was <%s>",
            List.of(notPresentEvent), actual.events());
    assertThat(assertionError2.getMessage()).isEqualTo(expectedMessage2);
  }

  @Test
  void checkHasAllEventsAssertionPassesWithSpecificEvents() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();

    assertThat(actual).hasAllEvents(event1, event2);
  }

  @Test
  void checkHasAllEventsAssertionFailsWithWrongEvent() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();
    HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

    AssertionError assertionError1 =
        failsAssertion(() -> assertThat(actual).hasAllEvents(event1, notPresentEvent));
    String expectedMessage1 =
        String.format(
            "Expected events to contain all of <%s> but was <%s>",
            List.of(event1, notPresentEvent), actual.events());
    assertThat(assertionError1.getMessage()).isEqualTo(expectedMessage1);

    AssertionError assertionError2 =
        failsAssertion(() -> assertThat(actual).hasAllEvents(event2, notPresentEvent));
    String expectedMessage2 =
        String.format(
            "Expected events to contain all of <%s> but was <%s>",
            List.of(event2, notPresentEvent), actual.events());
    assertThat(assertionError2.getMessage()).isEqualTo(expectedMessage2);
  }

  @Test
  void checkHasExactlyEventsAssertionPassesWithSpecificEvents() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();

    assertThat(actual).hasExactlyEvents(event1, event2);
  }

  @Test
  void checkHasExactlyEventsAssertionFailsWithWrongEvent() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();
    HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

    AssertionError assertionError1 =
        failsAssertion(() -> assertThat(actual).hasExactlyEvents(event1, notPresentEvent));
    String expectedMessage1 =
        String.format(
            "Expected events to contain exactly <%s> but was <%s>",
            List.of(event1, notPresentEvent), actual.events());
    assertThat(assertionError1.getMessage()).isEqualTo(expectedMessage1);

    AssertionError assertionError2 =
        failsAssertion(() -> assertThat(actual).hasExactlyEvents(event2, notPresentEvent));
    String expectedMessage2 =
        String.format(
            "Expected events to contain exactly <%s> but was <%s>",
            List.of(event2, notPresentEvent), actual.events());
    assertThat(assertionError2.getMessage()).isEqualTo(expectedMessage2);
  }

  @Test
  void checkHasNoDuplicateEventsPassesWithDistinctEvents() {
    HistoryEvent event1 = HistoryEvent.builder().id(1L).build();
    HistoryEvent event2 = HistoryEvent.builder().id(2L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event1, event2).build();

    assertThat(actual).hasNoDuplicateEvents();
  }

  @Test
  void checkHasNoDuplicateEventsFailsWithDuplicateEvents() {
    HistoryEvent event = HistoryEvent.builder().id(1L).build();
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().events(event, event).build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasNoDuplicateEvents());
    String expectedMessage =
        String.format("Expected events to contain no duplicates but was <%s>", actual.events());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkHasNextTokenPassesWithToken() {
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().nextToken("token").build();

    assertThat(actual).hasNextToken();
  }

  @Test
  void checkHasNextTokenFailsWithoutToken() {
    GetExecutionHistoryResponse actual = GetExecutionHistoryResponse.builder().build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasNextToken());
    String expectedMessage =
        String.format(
            "Expected nextToken to not be null or empty but was <%s>", actual.nextToken());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  void checkDoesNotHaveNextTokenPassesWithoutToken() {
    GetExecutionHistoryResponse actual = GetExecutionHistoryResponse.builder().build();

    assertThat(actual).doesNotHaveNextToken();
  }

  @Test
  void checkDoesNotHaveNextTokenFailsWithToken() {
    GetExecutionHistoryResponse actual =
        GetExecutionHistoryResponse.builder().nextToken("token").build();

    AssertionError assertionError = failsAssertion(() -> assertThat(actual).doesNotHaveNextToken());
    String expectedMessage =
        String.format("Expected nextToken to be null or empty but was <%s>", actual.nextToken());
    assertThat(assertionError.getMessage()).isEqualTo(expectedMessage);
  }

  public AssertionError failsAssertion(Executable executable) {
    return assertThrows(AssertionError.class, executable);
  }
}
