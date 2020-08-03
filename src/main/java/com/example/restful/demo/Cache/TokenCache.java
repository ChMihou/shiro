package com.example.restful.demo.Cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
 * @Description token本地缓存，使用guava缓存实现
 * */
//使用直接 TokenCache.setKey("Token_"+username,forgetToken);即可
public class TokenCache {
    //    创建logback的logger
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    //    声明一个静态的内存块,guava里面的本地缓存
    private static LoadingCache<String, String> localcache =
            //构建本地缓存，调用链的方式 ,1000是设置缓存的初始化容量，maximumSize是设置缓存最大容量，当超过了最大容量，guava将使用LRU算法（最少使用算法），来移除缓存项
            //expireAfterAccess(12,TimeUnit.HOURS)设置缓存有效期为12个小时
            CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12,TimeUnit.HOURS)
                    //build里面要实现一个匿名抽象类
                    .build(new CacheLoader<String, String>() {
//                   这个方法是默认的数据加载实现,get的时候，如果key没有对应的值，就调用这个方法进行加载

                        @Override
                        public String load(String s) throws Exception {
//                        为什么要把return的null值写成字符串，因为到时候用null去.equal的时候，会报空指针异常
                            return "null";
                        }
                    });

    /*
     * 添加本地缓存
     * */
    public static void setKey(String key, String value) {
        localcache.put(key, value);
    }

    /*
     * 得到本地缓存
     * */
    public static String getKey(String key) {
        String value = null;
        try {
            value= localcache.get(key);
            if ("null".equals(value)) {
                return  null;
            }
            return value;
        } catch (ExecutionException e) {
            logger.error("getKey()方法错误",e);
        }
        return null;
    }
}
//    Guava Cache的回收策略
//
//有两种回收策略：
//
//        一种是基于容量的回收CacheBuilder.maximumSize(Long)。设置缓存最大容量，当超过最大容量，缓存将尝试回收最近没有使用或总体上很少使用的缓存项。
//
//        第二种定时回收
//
//        expireAfterAccess(long,TimeUnit):缓存项在给定时间内没有被读写访问，则回收。请注意这种缓存的回收顺序和基于大小回收一样。
//
//          expireAfterWrite(long,TimeUnit):缓存项在给定时间内没有被写访问（创建或覆盖），则回收。如果认为缓存数据总是在固定时候后变的陈旧不可用，这种回收是可取的。
//
//        实例中用的是第一种策略，通过设置缓存最大容量，当超过了最大容量，guava将使用LRU算法来减少缓存项
//
//         
//
//        LRU算法原理
//
//        LRU（Least recently used，最近最少使用的）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高。
//
//        最常见的实现是使用一个链表保存缓存数据：
//        1 新数据插入到链表头部
//        2 每当缓存命中（即缓存数据被访问），则将数据移到链表头部
//        3 当链表满的时候，将链表尾部的数据丢弃