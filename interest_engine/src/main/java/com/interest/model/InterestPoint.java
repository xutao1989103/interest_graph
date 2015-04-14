package com.interest.model;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class InterestPoint {
    private Integer interestId;
    private Integer parentId;
    private String nodeName;
    private List<String> tags;
    private boolean isLeaf;
    private InterestPoint parentNode;
    private List<InterestPoint> childNodes;
    private InterestPoint(InterestBuilder builder){
        this.nodeName = builder.nodeName;
        this.tags = builder.tags;
        this.isLeaf = builder.isLeaf;
        this.parentNode = builder.parentNode;
        this.childNodes = builder.childNodes;
    }

    public InterestPoint(){
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

        InterestPoint interestPoint = (InterestPoint) o;

        if (isLeaf != interestPoint.isLeaf) return false;
        if (!nodeName.equals(interestPoint.nodeName)) return false;

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

    public InterestPoint getParentNode() {
        return parentNode;
    }

    public List<InterestPoint> getChildNodes() {
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
        private InterestPoint parentNode;
        private List<InterestPoint> childNodes;

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
        public InterestBuilder withParentNode(InterestPoint parentNode){
            this.parentNode = parentNode;
            return this;
        }
        public InterestBuilder withChildNodes(List<InterestPoint> childNodes){
            this.childNodes = childNodes;
            return this;
        }
        public InterestPoint build(){
            return new InterestPoint(this);
        }
    }
}
