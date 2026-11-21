package com.anjuzu.client;

import com.anjuzu.client.bff.ClientCommand;
import com.anjuzu.client.bff.ClientCommandRegistry;
import com.anjuzu.client.bff.RouteGuard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientBffContractTest {

    @Test
    void clientRegistersExactlyEightCommands() {
        assertEquals(8, ClientCommand.values().length);
        assertEquals(8, ClientCommandRegistry.names().size());
    }

    @Test
    void nonWhitelistedCommandIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> ClientCommandRegistry.resolve("PROPERTY_CREATE"));
        assertEquals(ClientCommand.LEASE_QUERY, ClientCommandRegistry.resolve("LEASE_QUERY"));
    }

    @Test
    void adminRoutesAreBlockedOnClientBff() {
        assertTrue(RouteGuard.isBlocked("/admin-api/property/transitions"));
        assertTrue(RouteGuard.isAdminPath("/admin-api/property/transitions"));
        assertFalse(RouteGuard.isBlocked("/client-api/commands/LEASE_QUERY"));
    }
}
