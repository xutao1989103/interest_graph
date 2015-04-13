package com.interest.model;

import com.interest.service.InterestBuild;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class Interest {
    private Integer interestId;
    private Integer parentId;
    private String nodeName;
    private List<String> tags;
    private boolean isLeaf;
    private Interest parentNode;
    private List<Interest> childNodes;
    private Interest(InterestBuilder builder){
        this.nodeName = builder.nodeName;
        this.tags = builder.tags;
        this.isLeaf = builder.isLeaf;
        this.parentNode = builder.parentNode;
        this.childNodes = builder.childNodes;
    }

    public Interest(){
    }

    @Override
    public String toString() {
        return "Interest{" +
                "iterestId=" + interestId +
                ", nodeName='" + nodeName + '\'' +
                ", tags=" + tags +
                ", isLeaf=" + isLeaf +
                ", parentNode=" + parentNode +
                ", childNodes=" + childNodes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interest interest = (Interest) o;

        if (isLeaf != interest.isLeaf) return false;
        if (!nodeName.equals(interest.nodeName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nodeName.hashCode();
        result = 31 * result + (isLeaf ? 1 : 0);
        return result;
    }
    public Integer getInterestId() {
        return interestId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public Interest getParentNode() {
        return parentNode;
    }

    public List<Interest> getChildNodes() {
        return childNodes;
    }

    public Integer getParentId() {
        return parentId;
    }

    // the build class
    public static class InterestBuilder{
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
