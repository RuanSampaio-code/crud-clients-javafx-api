package com.ruan.cep_service.configSpringJavaFx;

import org.springframework.context.ApplicationContext;

public class SpringContext {
    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
