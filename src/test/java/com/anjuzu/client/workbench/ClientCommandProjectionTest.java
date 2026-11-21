package com.anjuzu.client.workbench;

import com.anjuzu.client.bff.ClientCommand;
import com.anjuzu.client.bff.CommandDispatchTable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientCommandProjectionTest {

    @Test
    void everyWhitelistedCommandProjectsExactlyOneTodoType() {
        assertEquals(8, CommandDispatchTable.size());
        for (ClientCommand command : ClientCommand.values()) {
            assertNotNull(CommandDispatchTable.todoTypeOf(command));
        }
    }

    @Test
    void projectionContractMatchesServerTodo() {
        assertEquals(List.of("partyId", "command", "todoType", "idempotencyKey", "status", "createdAt"),
                AdminTodoProjection.CONTRACT_FIELDS);
        ClientTodoProjector projector = new ClientTodoProjector();
        AdminTodoProjection projection = projector.project("party-1", ClientCommand.TERMINATION_APPLY, "idem-1");
        assertEquals("TERMINATION_TODO", projection.todoType());
        assertEquals("OPEN", projection.status());
        assertThrows(IllegalArgumentException.class,
                () -> projector.project(" ", ClientCommand.REPAIR_CREATE, "idem-2"));
    }
}
