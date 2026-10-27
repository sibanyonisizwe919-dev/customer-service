package com.example.view;

/**
 * View model for error responses (e.g. 404 not found).
 *
 * {"error": "..."} — a stable, documented shape clients can rely on.
 */
public record ErrorResponse(String error) {
}
