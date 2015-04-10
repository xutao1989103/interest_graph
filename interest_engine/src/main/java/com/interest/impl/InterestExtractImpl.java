package com.interest.impl;

import com.interest.model.Interest;
import com.interest.model.Type;
import com.interest.service.InterestExtract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
public class InterestExtractImpl implements InterestExtract {
    @Override
    public List<Interest> extract(Type type) {
        List<Interest> result = new ArrayList<Interest>();
        String sb = type.getName();
        String[] strings = sb.split(" - ");
        if(strings.length==2){
            Interest parent = new Interest.InterestBuilder(strings[0]).withIsLeaf(false).build();
            Interest son = new Interest.InterestBuilder(strings[1]).withIsLeaf(true).withParentNode(parent).build();
            result.add(parent);
            result.add(son);
        }else {
            Interest son = new Interest.InterestBuilder(sb).withIsLeaf(true).build();
            result.add(son);
        }
        return result;
    }
}
