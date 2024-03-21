package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoadableInvHandler implements InvocationHandler
{
    private Object obj;
    LoadableInvHandler(Object obj){this.obj = obj;}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        //Annotation[] anns = m.getDeclaredAnnotations();
        if(Arrays.stream(m.getAnnotationsByType(Cachable.class)).count()>0)
        {
        //    Utils<obj, obj.getClass()> cache = new Utils<>();
        //    cache.put(obj.getClass());
            System.out.println("proxy = " + proxy + ", method = " + method + ", args = " + Arrays.toString(args));
        }
        //    Utils<obj, obj.getClass()> cache = new Utils<>();
        //    cache.getByKey(obj.getClass());
        if(Arrays.stream(m.getAnnotationsByType(Mutator.class)).count()>0)
            System.out.println("proxy = " + proxy + ", method = " + method + ", args = " + Arrays.toString(args));
        return method.invoke(obj, args);

    }
}