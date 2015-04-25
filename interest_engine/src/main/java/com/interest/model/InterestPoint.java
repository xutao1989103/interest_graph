package com.interest.model;

import com.interest.service.InterestBuild;

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
    private Type type;
    private InterestPoint(InterestBuilder builder){
        this.parentId = builder.parentId;
        this.nodeName = builder.nodeName;
        this.tags = builder.tags;
        this.isLeaf = builder.isLeaf;
        this.parentNode = builder.parentNode;
        this.childNodes = builder.childNodes;
        this.type = builder.type;
    }

    public InterestPoint(){
    }

    @Override
    public String toString() {
        return "InterestPoint{" +
                "interestId=" + interestId +
                ", parentId=" + parentId +
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

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public void setParentNode(InterestPoint parentNode) {
        this.parentNode = parentNode;
    }

    public void setChildNodes(List<InterestPoint> childNodes) {
        this.childNodes = childNodes;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    // the build class
    public static class InterestBuilder{
        private String nodeName;
        private Integer parentId;
        private List<String> tags;
        private boolean isLeaf;
        private InterestPoint parentNode;
        private List<InterestPoint> childNodes;
        private Type type;

        public InterestBuilder (String nodeName){
            this.nodeName = nodeName;
        }
        public InterestBuilder withType(Type type){
            this.type = type;
            return this;
        }
        public InterestBuilder withTags(List<String> tags){
            this.tags = tags;
            return this;
        }
        public InterestBuilder withIsLeaf(boolean isLeaf){
            this.isLeaf = isLeaf;
            return this;
        }
        public InterestBuilder withParentId(Integer parentId){
            this.parentId = parentId;
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
