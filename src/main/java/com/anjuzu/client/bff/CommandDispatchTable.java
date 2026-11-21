package com.anjuzu.client.bff;

import java.util.Map;

/** 8 条客户端命令到管理端待办类型的表驱动映射，禁止散落 if/else。 */
public final class CommandDispatchTable {

    private static final Map<ClientCommand, String> TODO_TYPES = Map.ofEntries(
            Map.entry(ClientCommand.PROPERTY_SEARCH, "PROPERTY_QUERY_TODO"),
            Map.entry(ClientCommand.PROPERTY_DETAIL, "PROPERTY_QUERY_TODO"),
            Map.entry(ClientCommand.VIEWING_REQUEST, "VIEWING_TODO"),
            Map.entry(ClientCommand.CONTRACT_SIGN, "CONTRACT_TODO"),
            Map.entry(ClientCommand.LEASE_QUERY, "LEASE_QUERY_TODO"),
            Map.entry(ClientCommand.REPAIR_CREATE, "REPAIR_TODO"),
            Map.entry(ClientCommand.REPAIR_QUERY, "REPAIR_QUERY_TODO"),
            Map.entry(ClientCommand.TERMINATION_APPLY, "TERMINATION_TODO")
    );

    private CommandDispatchTable() {
    }

    public static String todoTypeOf(ClientCommand command) {
        String todoType = TODO_TYPES.get(command);
        if (todoType == null) {
            throw new IllegalArgumentException("no admin todo type for command: " + command);
        }
        return todoType;
    }

    public static int size() {
        return TODO_TYPES.size();
    }
}
