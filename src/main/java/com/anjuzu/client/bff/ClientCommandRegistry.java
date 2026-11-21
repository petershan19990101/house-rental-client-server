package com.anjuzu.client.bff;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/** 白名单注册表：只有客户端 8 条自有命令可被 client-bff 解析，其余一律拒绝。 */
public final class ClientCommandRegistry {

    private static final Set<String> NAMES;

    static {
        Set<String> names = new LinkedHashSet<>();
        for (ClientCommand command : ClientCommand.values()) {
            names.add(command.name());
        }
        NAMES = Collections.unmodifiableSet(names);
    }

    private ClientCommandRegistry() {
    }

    public static Set<String> names() {
        return NAMES;
    }

    public static int size() {
        return NAMES.size();
    }

    public static boolean allows(String command) {
        return command != null && NAMES.contains(command);
    }

    public static ClientCommand resolve(String command) {
        if (!allows(command)) {
            throw new IllegalArgumentException("command not in client whitelist: " + command);
        }
        return ClientCommand.valueOf(command);
    }
}
