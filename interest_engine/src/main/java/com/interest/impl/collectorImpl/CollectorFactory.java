package com.interest.impl.collectorImpl;

import com.interest.enums.CollectorEnum;
import com.interest.service.Collector;

import static com.interest.enums.CollectorEnum.*;

/**
 * Created by 431 on 2015/4/10.
 */
public class CollectorFactory {
    public static Collector create(CollectorEnum collectorEnum){
        switch (collectorEnum){
            case FILE: return new FileCollector();
            case JSON: return new JsonCollector();
            default:return null;
        }
    }
}
