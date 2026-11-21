package com.anjuzu.client.bff;

import com.anjuzu.client.workbench.TodoProjectionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/client-api/commands")
public class ClientBffController {

    private final TodoProjectionService todoProjectionService;

    public ClientBffController(TodoProjectionService todoProjectionService) {
        this.todoProjectionService = todoProjectionService;
    }

    @PostMapping("/{command}")
    public Map<String, Object> submit(@PathVariable String command, @RequestParam String partyId) {
        ClientCommand resolved = ClientCommandRegistry.resolve(command);
        String todoId = todoProjectionService.project(partyId, resolved.name());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("command", resolved.name());
        body.put("status", "ACCEPTED");
        body.put("adminTodoId", todoId);
        return body;
    }
}
