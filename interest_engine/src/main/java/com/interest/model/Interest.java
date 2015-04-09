package com.interest.model;

import com.interest.service.InterestBuild;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class Interest {
    private Integer interestId;
    private String nodeName;
    private List<String> tags;
    private boolean isLeaf;
    private Interest parentNode;
    private List<Interest> childNodes;
    private Interest(InterestBuilder builder){
        this.interestId = builder.iterestId;
        this.nodeName = builder.nodeName;
        this.tags = builder.tags;
        this.isLeaf = builder.isLeaf;
        this.parentNode = builder.parentNode;
        this.childNodes = builder.childNodes;
    }
    // the build class
    public static class InterestBuilder{
        private Integer iterestId;
        private String nodeName;
        private List<String> tags;
        private boolean isLeaf;
        private Interest parentNode;
        private List<Interest> childNodes;

        public InterestBuilder (String nodeName){
            this.nodeName = nodeName;
        }
        public InterestBuilder withTags(List<String> tags){
            this.tags = tags;
            return this;
        }
        public InterestBuilder withIsLeaf(boolean isLeaf){
            this.isLeaf = isLeaf;
            return this;
        }
        public InterestBuilder withParentNode(Interest parentNode){
            this.parentNode = parentNode;
            return this;
        }
        public InterestBuilder withChildNodes(List<Interest> childNodes){
            this.childNodes = childNodes;
            return this;
        }
        public Interest build(){
            return new Interest(this);
        }
    }
}
