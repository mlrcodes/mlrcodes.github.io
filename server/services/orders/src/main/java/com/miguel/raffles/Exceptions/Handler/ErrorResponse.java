package com.miguel.raffles.Exceptions.Handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> error
) {

}
