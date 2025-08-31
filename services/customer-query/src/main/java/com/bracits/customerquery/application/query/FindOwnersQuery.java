package com.bracits.customerquery.application.query;

public record FindOwnersQuery(
        String name,
        String email,
        int page,
        int size) {
}