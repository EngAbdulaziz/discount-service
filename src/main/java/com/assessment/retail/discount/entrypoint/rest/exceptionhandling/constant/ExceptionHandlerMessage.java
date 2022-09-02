package com.assessment.retail.discount.entrypoint.rest.exceptionhandling.constant;

public final class ExceptionHandlerMessage {

    public static final String DATA_ACCESS_EX_CAUGHT = "DataAccessException caught: {}";

    public static final String DECODING_EX_CAUGHT = "DecodingException caught: {}";

    public static final String SERVER_WEB_INPUT_EX_CAUGHT = "ServerWebInputException caught: {}";

    public static final String WEB_EXCHANGE_BIND_EX_CAUGHT = "WebExchangeBindException caught: {}";

    public static final String THROWABLE_CAUGHT = "Throwable caught ({})";

    public static final String JAVAX_CONSTRAINT_VIOLATION_EX_CAUGHT =
            "javax.validation.ConstraintViolationException caught: {}";

    private ExceptionHandlerMessage() {
    }
}
