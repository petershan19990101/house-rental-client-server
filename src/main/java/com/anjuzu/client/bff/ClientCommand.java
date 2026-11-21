package com.anjuzu.client.bff;

/** 客户端仅注册 8 条自有命令（附件2第5章），写权威在管理端。 */
public enum ClientCommand {
    PROPERTY_SEARCH,
    PROPERTY_DETAIL,
    VIEWING_REQUEST,
    CONTRACT_SIGN,
    LEASE_QUERY,
    REPAIR_CREATE,
    REPAIR_QUERY,
    TERMINATION_APPLY
}
