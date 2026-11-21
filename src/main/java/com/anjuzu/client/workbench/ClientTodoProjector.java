package com.anjuzu.client.workbench;

import com.anjuzu.client.bff.ClientCommand;
import com.anjuzu.client.bff.CommandDispatchTable;

import java.time.Instant;

/** 客户端每次提交都必须投影管理端待办，幂等键保证重复提交不新增待办。 */
public final class ClientTodoProjector {

    public AdminTodoProjection project(String partyId, ClientCommand command, String idempotencyKey) {
        return new AdminTodoProjection(partyId, command.name(), CommandDispatchTable.todoTypeOf(command),
                idempotencyKey, AdminTodoProjection.STATUS_OPEN, Instant.now());
    }

    public String idempotencyKey(String partyId, ClientCommand command, String clientRequestId) {
        return partyId + ":" + command.name() + ":" + clientRequestId;
    }
}
