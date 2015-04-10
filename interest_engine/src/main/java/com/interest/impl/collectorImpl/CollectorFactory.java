package com.interest.impl.collectorImpl;

import com.interest.enums.CollectorEnum;
import com.interest.service.Collector;

/**
 * Created by 431 on 2015/4/10.
 */
public class CollectorFactory {
    public static Collector create(CollectorEnum collectorEnum){
        return new FileCollector();
    }
}
