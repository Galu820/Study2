package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoadableInvHandler implements InvocationHandler
{
    private Object obj;
    LoadableInvHandler(Object obj){this.obj = obj;}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        //Annotation[] anns = m.getDeclaredAnnotations();
        Annotation[] anns = m.getAnnotationsByType(Mutator.class);
        for (Annotation a: anns)
        {
            Utils.Cache(obj, m);
        }

        return method.invoke(obj, args);

    }
}