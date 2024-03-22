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
        this.cashes = new HashMap<>();
    }
    private Object cashedValue;
    private HashMap<String, Object> cashes;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        if(m.isAnnotationPresent(Cachable.class)){
            Utils<Object,String> cache = new Utils<>();
            if(cache.put(m.getName()))
                System.out.println("Found @Cachable annotation ");

        } else {

        if(m.isAnnotationPresent(Mutator.class))
            System.out.println("proxy = " + proxy + ", method = " + method + ", args = " + Arrays.toString(args));

        return method.invoke(obj, args);

    }
}