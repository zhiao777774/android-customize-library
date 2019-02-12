package com.example.hsu.customize.usableClass;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BilInqMap<K,V> {
    private final static String DEFAULT_VALUE = "1";
    //Key Map (LinkedHashMap is Ordered Map, so we can use Key or Value to find index)
    private final Map<K,V> kMap = new LinkedHashMap<>();
    //Value Map (LinkedHashMap is Ordered Map, so we can use Key or Value to find index. ConcurrentHashMap is synchronized)
    private final Map<V,ConcurrentHashMap<K,String>> vMap = new LinkedHashMap<>();

    public boolean put(K key, V value) {

        if(key == null || value == null) {
            return false;
        }

        if(containsKey(key)) {
            vMap.get(kMap.get(key)).remove(key);
        }
        kMap.put(key, value);

        if(containsValue(value)) {
            vMap.get(value).put(key, DEFAULT_VALUE);
        } else {
            ConcurrentHashMap<K,String> set = new ConcurrentHashMap<>();
            set.put(key, DEFAULT_VALUE);
            vMap.put(value, set);
        }
        return true;
    }

    public boolean containsKey(K key) {
        return kMap.containsKey(key);
    }

    public boolean containsValue(V value) {
        return vMap.containsKey(value);
    }

    public Set<K> keySet(){
        return kMap.keySet();
    }

    public Set<V> valueSet() {
        return vMap.keySet();
    }

    public Collection<V> values(){
        return kMap.values();
    }

    public Set<Map.Entry<K,V>> entrySet(){
        return kMap.entrySet();
    }

    public V getByKey(K key) {
        return kMap.get(key);
    }

    public ConcurrentHashMap<K,String> getByValue(V value) {
        return vMap.get(value);
    }

    public int indexOf(K key){
        int count = 0;

        if(key != null & containsKey(key)){
            for(K k : keySet()){
                if(key.equals(k)) return count;
                count++;
            }
        }
        return -1;
    }

    public int[] allIndexOf(V value){
        int count = 0;
        int indexCount = 0;

        if(value != null & containsValue(value)){
            int length = getByValue(value).keySet().size();
            int[] indexSet = new int[length];

            for(V v : valueSet()){
                if(value.equals(v)) {
                    indexSet[indexCount] = count;
                    indexCount++;
                }
                count++;
            }
            return indexSet;
        }
        return null;
    }

    public boolean equals(Object o){
        return kMap.equals(o);
    }

    public void clear(){
        kMap.clear();
        vMap.clear();
    }

    public boolean isEmpty(){
        return kMap.isEmpty();
    }

    public int size(){
        return kMap.size();
    }

    public String toString() {
        return kMap.toString();
    }
}
