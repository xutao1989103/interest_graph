package com.interest.impl.ExtractImpl;

import com.google.common.collect.Lists;
import com.interest.model.InterestPoint;
import com.interest.model.Music;
import com.interest.model.Type;
import com.interest.service.InterestExtract;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 431 on 2015/4/25.
 */
@Service("interestExtractFromJsonImpl")
public class InterestExtractFromJsonImpl implements InterestExtract {
    @Override
    public List<InterestPoint> extract(Type type) {
        List<InterestPoint> interestPointList = Lists.newArrayList();
        Music music = (Music) type;
        if(music.getName()!=null && !"".equals(music.getName())){
            if(music.getArtist()!=null && !"".equals(music.getArtist())){
                InterestPoint parent = new InterestPoint.InterestBuilder(music.getArtist()).withIsLeaf(false).withType(type).build();
                interestPointList.add(parent);
                InterestPoint son = new InterestPoint.InterestBuilder(music.getName()).withIsLeaf(true).withParentNode(parent).withType(type).build();
                interestPointList.add(son);
            }
        }
        return interestPointList;
    }
}
