package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class LoadableInvHandler implements InvocationHandler
{
    private Object obj;

    LoadableInvHandler(Object obj){
        this.obj = obj;
        this.caches = new HashMap<>();
    }
    private Object cachedValue;
    private HashMap<String, Object> caches;
    boolean hasMutatorAnn = false;
    boolean hasCacheAnn = false;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        if(m.isAnnotationPresent(Cachable.class)){
            hasCacheAnn = true;
            //Utils<Object,String> cache = new Utils<>();
            //if(cache.put(m.getName()))
            System.out.println("Found @Cachable annotation ");
        }

        if (m.isAnnotationPresent(Mutator.class)) {
            hasMutatorAnn = true;
            System.out.println("Found @Mutator annotation ");
        }

        if (hasMutatorAnn) {
            System.out.println("Not cashed val from mutator ");
            return method.invoke(obj, args);
        }

        if (hasCacheAnn) {
            if(!caches.containsKey(m.getName())){
                System.out.println("Not cached value: ");
                cachedValue = method.invoke(obj, args);
                caches.put(m.getName(),cachedValue);
                return cachedValue;
            } else {
                System.out.println("Cached value: ");
                return caches.get(m.getName());
            }

        }

        return method.invoke(obj, args);

    }
}