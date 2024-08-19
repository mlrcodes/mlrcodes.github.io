package com.miguel.raffles.Exceptions.Handler;

import lombok.Builder;

import java.util.Map;

@Builder
public record Errors (
        Map<String, String> errors
) {
}
