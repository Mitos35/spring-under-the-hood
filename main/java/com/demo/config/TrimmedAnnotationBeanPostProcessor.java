package com.demo.config;

import com.demo.annotation.Trimmed;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanType = bean.getClass();
        if (beanType.isAnnotationPresent(Trimmed.class)) {
            System.out.println("I NEED TO CREATE A PROXY OF CLASS " + beanType);
            return createLoggingProxy(beanType);
        }
        return bean;
    }

    private Object createLoggingProxy(Class<?> beanType) {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(beanType);
        MethodInterceptor interceptor = (Object obj, Method method, Object[] args, MethodProxy proxy) -> {
            System.out.println("Calling method " + method.getName() + " of a class " + beanType.getSimpleName());
            return proxy.invokeSuper(obj, args);
        };
        enhancer.setCallback(interceptor);
        return beanType.cast(enhancer.create());
    }
}
