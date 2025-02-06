package com.ziine.admin.auth.domain;

import io.jsonwebtoken.Claims;

public record Admin(
    String name
) {

    private static final String ADMIN_NAME_CLAIM_KEY = "name"; // JWT에서 Admin 이름을 담는 key

    public static Admin fromClaims(final Claims claims) {
        final String adminName = claims.get(ADMIN_NAME_CLAIM_KEY, String.class);
        return new Admin(adminName);
    }
}
