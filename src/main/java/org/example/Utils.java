package org.example;

import java.lang.reflect.*;
import java.util.Map;
import java.util.WeakHashMap;

public class Utils<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {

        if (cache.containsKey(key)==false)
        {
            cache.put(key, clazz.getConstructor(key.getClass()).newInstance(key));
        }
        return cache.get(key);
    }

    public boolean put(V obj) {

        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);

            cache.put((K)method.invoke(obj), obj);
            return cache.containsKey((K)method.invoke(obj));

        } catch (Exception e) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
