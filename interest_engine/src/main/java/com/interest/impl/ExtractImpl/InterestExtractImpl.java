package com.interest.impl.ExtractImpl;

import com.interest.model.InterestPoint;
import com.interest.model.Type;
import com.interest.service.InterestExtract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
public class InterestExtractImpl implements InterestExtract {
    @Override
    public List<InterestPoint> extract(Type type) {
        List<InterestPoint> result = new ArrayList<InterestPoint>();
        String sb = type.getName();
        String[] strings = sb.split(" - ");
        if(strings.length==2){
            InterestPoint parent = new InterestPoint.InterestBuilder(strings[0]).withIsLeaf(false).build();
            InterestPoint son = new InterestPoint.InterestBuilder(strings[1]).withIsLeaf(true).withParentNode(parent).build();
            result.add(parent);
            result.add(son);
        }else {
            InterestPoint son = new InterestPoint.InterestBuilder(sb).withIsLeaf(true).build();
            result.add(son);
        }
        return result;
    }
}
