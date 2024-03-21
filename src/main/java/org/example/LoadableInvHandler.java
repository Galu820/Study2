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
            int c = new Utils(obj);
        }

        return method.invoke(obj, args);

    }
}