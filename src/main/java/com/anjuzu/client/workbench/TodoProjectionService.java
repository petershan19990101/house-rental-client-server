package com.anjuzu.client.workbench;

import org.springframework.stereotype.Service;

/** 客户端每次提交都必须投影管理端待办（附件2第5章）。 */
@Service
public class TodoProjectionService {

    public String project(String partyId, String command) {
        if (partyId == null || partyId.isBlank()) {
            throw new IllegalArgumentException("partyId required");
        }
        return "admin-todo:" + partyId + ":" + command;
    }
}
