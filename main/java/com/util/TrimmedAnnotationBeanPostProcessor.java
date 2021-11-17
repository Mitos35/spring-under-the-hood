package com.util;

import com.demo.annotation.Trimmed;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanType = bean.getClass();
        if (beanType.isAnnotationPresent(Trimmed.class)) {
            return createLoggingProxy(beanType);
        }
        return bean;
    }

    private Object createLoggingProxy(Class<?> beanType) {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(beanType);
        MethodInterceptor interceptor = (obj, method, args, proxy) -> {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass() == String.class) {
                    args[i] = ((String) args[i]).trim();
                }
            }
            Class<?> returnType = method.getReturnType();
            if (returnType.equals(String.class)) {
                return proxy.invokeSuper(obj, args).toString().trim();
            }
            return proxy.invokeSuper(obj, args);
        };
        enhancer.setCallback(interceptor);
        return beanType.cast(enhancer.create());
    }
}
