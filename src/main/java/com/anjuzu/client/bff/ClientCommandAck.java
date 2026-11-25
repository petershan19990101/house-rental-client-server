package com.anjuzu.client.bff;

/** 客户端 BFF 命令受理回执 DTO，字段顺序与原 Map 构造顺序一致。 */
public record ClientCommandAck(String command, String status, String adminTodoId) {
}
