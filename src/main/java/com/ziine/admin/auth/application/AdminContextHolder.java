package com.ziine.admin.auth.application;

import com.ziine.admin.auth.domain.Admin;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminContextHolder {

    private static final ThreadLocal<Admin> ADMIN_THREAD_LOCAL = new ThreadLocal<>();

    public static void setAdmin(final Admin admin) {
        ADMIN_THREAD_LOCAL.set(admin);
    }

    public static Optional<Admin> getAdmin() {
        return Optional.ofNullable(ADMIN_THREAD_LOCAL.get());
    }

    public static void clear() {
        ADMIN_THREAD_LOCAL.remove();
    }
}
