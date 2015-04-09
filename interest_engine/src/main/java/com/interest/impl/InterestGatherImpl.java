package com.interest.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.interest.enums.Status;
import com.interest.model.Input;
import com.interest.model.Interest;
import com.interest.model.Type;
import com.interest.model.User;
import com.interest.service.InterestGather;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class InterestGatherImpl implements InterestGather {
    @Override
    public List<Interest> gather(final Input input) {
        List<Interest> interests =
        Lists.transform(input.toList(), new Function<Type,Interest>() {
            @Override
            public Interest apply(Type input) {
                Interest interest = new Interest.InterestBuilder(input.getName()).withTags(input.getTags()).build();
                return interest;
            }
        });
        return interests;
    }

    @Override
    public Status save(User user, List interests) {
        return null;
    }
}
