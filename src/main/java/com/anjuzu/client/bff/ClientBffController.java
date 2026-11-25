package com.anjuzu.client.bff;

import com.anjuzu.client.workbench.TodoProjectionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/client-api/commands")
public class ClientBffController {

    private final TodoProjectionService todoProjectionService;

    public ClientBffController(TodoProjectionService todoProjectionService) {
        this.todoProjectionService = todoProjectionService;
    }

    @PostMapping("/{command}")
    public ClientCommandAck submit(@PathVariable String command, @RequestParam String partyId) {
        ClientCommand resolved = ClientCommandRegistry.resolve(command);
        String todoId = todoProjectionService.project(partyId, resolved.name());
        return new ClientCommandAck(resolved.name(), "ACCEPTED", todoId);
    }
}
