package dev.joss.awssert.sfn;

import org.assertj.core.api.AbstractAssert;
import software.amazon.awssdk.services.sfn.model.GetExecutionHistoryResponse;

public class GetExecutionHistoryResponseAssert extends AbstractAssert<GetExecutionHistoryResponseAssert, GetExecutionHistoryResponse> {
    private GetExecutionHistoryResponseAssert(GetExecutionHistoryResponse actual) {
        super(actual, GetExecutionHistoryResponseAssert.class);
    }
}
