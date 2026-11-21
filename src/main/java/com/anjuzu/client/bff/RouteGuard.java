package com.anjuzu.client.bff;

/** 路由物理隔离守卫：client-bff 只接受 /client-api 前缀，管理端路由一律不可达。 */
public final class RouteGuard {

    public static final String CLIENT_PREFIX = "/client-api";
    public static final String ADMIN_PREFIX = "/admin-api";

    private RouteGuard() {
    }

    public static boolean isBlocked(String path) {
        return path == null || !path.startsWith(CLIENT_PREFIX);
    }

    public static boolean isAdminPath(String path) {
        return path != null && path.startsWith(ADMIN_PREFIX);
    }
}
