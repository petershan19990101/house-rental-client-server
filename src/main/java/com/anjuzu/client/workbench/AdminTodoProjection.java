package com.anjuzu.client.workbench;

import java.time.Instant;
import java.util.List;

/** 客户端提交投影到管理端待办的跨仓契约，字段与服务端 WorkbenchTodo.CONTRACT_FIELDS 一致。 */
public record AdminTodoProjection(String partyId, String command, String todoType, String idempotencyKey,
                                  String status, Instant createdAt) {

    public static final List<String> CONTRACT_FIELDS = List.of(
            "partyId", "command", "todoType", "idempotencyKey", "status", "createdAt");

    public static final String STATUS_OPEN = "OPEN";

    public AdminTodoProjection {
        if (partyId == null || partyId.isBlank()) {
            throw new IllegalArgumentException("partyId required");
        }
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("command required");
        }
        if (todoType == null || todoType.isBlank()) {
            throw new IllegalArgumentException("todoType required");
        }
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("idempotencyKey required");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status required");
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
