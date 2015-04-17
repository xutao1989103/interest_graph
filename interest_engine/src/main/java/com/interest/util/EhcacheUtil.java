package com.interest.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * Created by 431 on 2015/4/17.
 */
public class EhcacheUtil {
    private static final String appointPath = "classpath:conf/ehcache.xml";
    private CacheManager cacheManager;
    private static EhcacheUtil ehcacheUtil;

    private EhcacheUtil(){
        cacheManager = CacheManager.create();
    }

    public static EhcacheUtil getInstance(){
        if(ehcacheUtil == null){
            ehcacheUtil = new EhcacheUtil();
        }
        return ehcacheUtil;
    }

    public Cache getCache(String name)throws IllegalStateException{
        return cacheManager.getCache(name);
    }

    public Object getObjectCached(String cacheName, Serializable key){
        Cache cache = getCache(cacheName);
        if(cache!=null){
            try {
                Element elem = cache.get(key);
                if(elem!=null && !cache.isExpired(elem)){
                    return elem.getObjectValue();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public synchronized void put(String cacheName, Object key, Object value){
        Cache cache = getCache(cacheName);
        if(cache == null){
            cache = new Cache(cacheName, 1000000,true,true,0,0);
            cacheManager.addCache(cache);
        }else {
            cache.remove(key);
            Element element = new Element(key, value);
            cache.put(element);
        }
    }

    public Element getElement(String cacheName, Object key){
        Cache cache1 = getCache(cacheName);
        return cache1.get(key);
    }

    public void removeElement(String cacheName, Object key){
        Cache cache = getCache(cacheName);
        if(cache!=null){
            cache.remove(key);
        }
    }

    public void removeCache(String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            cacheManager.removeCache(cacheName);
        }
    }

    public void shutdown(){
        if(cacheManager!=null){
            cacheManager.shutdown();
        }
    }
}
