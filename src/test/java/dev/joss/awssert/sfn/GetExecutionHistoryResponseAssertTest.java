package dev.joss.awssert.sfn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import software.amazon.awssdk.services.sfn.model.GetExecutionHistoryResponse;
import software.amazon.awssdk.services.sfn.model.HistoryEvent;

import static dev.joss.awssert.sfn.GetExecutionHistoryResponseAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetExecutionHistoryResponseAssertTest {

    @Test
    void checkActualEventsContainsEvent() {
        HistoryEvent event = HistoryEvent.builder().build();
        GetExecutionHistoryResponse actual = GetExecutionHistoryResponse.builder().events(event).build();

        assertThat(actual).hasEvent(event);
    }

    @Test
    void checkActualEventsDoesNotContainsEvent() {
        HistoryEvent event = HistoryEvent.builder().build();
        GetExecutionHistoryResponse actual = GetExecutionHistoryResponse.builder().events(event).build();
        HistoryEvent notPresentEvent = HistoryEvent.builder().id(1000L).build();

        AssertionError assertionError = failsAssertion(() -> assertThat(actual).hasEvent(notPresentEvent));

        String expectedMessage = String.format("Expected event %s to be present but was not found", notPresentEvent.toString());
        assertEquals(expectedMessage, assertionError.getMessage());
    }

    public AssertionError failsAssertion(Executable executable) {
        return assertThrows(AssertionError.class, executable);
    }
}