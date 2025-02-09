package com.ziine.global.error.presentation;


import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

@Slf4j
public class GlobalAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(
        final Throwable ex,
        final Method method,
        final Object... params
    ) {
        log.error("[UnexpectedException] Message: {}", ex.getMessage());
    }
}
