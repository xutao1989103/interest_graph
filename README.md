兴趣图谱
=============
#一、系统总体概况
    下图是系统总体结构图：

#二、具体设计
##1、 兴趣引擎层设计
兴趣引擎分为三层，从下到上依次为兴趣搜集子系统、兴趣图谱构建子系统、兴趣图谱应用子系统。
兴趣搜集子系统，根据不同的业务会有不同的搜集方法，比如音乐推荐业务就是跟第三方音乐播放软件导入歌单；阅读推荐就需要根据用户信息用网络爬虫获取用户的阅读习惯等。兴趣搜集子系统需要跟业务适配。
兴趣图谱构建子系统，是整个兴趣引擎的核心，只有构建出高效合理的兴趣图谱才能有效的对上层提供服务。而且兴趣图谱要适配各种业务需求所以要做到高度抽象，结构灵活，动态更新。
兴趣图谱应用子系统，主要是推荐算法，根据兴趣图谱提供推荐和个性化服务。
#2、 应用层设计
应用分为服务层、控制层、前端。
服务层，根据兴趣引擎的应用子系统提供一项具体的服务，这层是可扩展的，当有新的服务增加时可新建一个项目。
控制层，组装服务根据用户的设置和兴趣提供可组装的个性化服务
前端，web前端主要用来给用户配置个人兴趣用，而用户的体验主要在App端。



#三、数据结构
##1、 兴趣结构
根据兴趣的不同粒度，将兴趣组织成树的结构：
public class Interest{
    private Integer InterestId;//id
    private String nodeName;//名字
    private List<String> tags;//该兴趣对应的tags
    private boolean isLeaf;//是否叶子节点
    private Interest parentNode;//父亲节点
    private List<Interest> childNodes;//子节点
}
##2、 兴趣图谱结构
兴趣图谱是一个图，但是里面的节点类型有两种，分别代表用户和兴趣，图中边只能是用户和兴趣的连接，相同类型的节点不能直接相连。采用邻接矩阵法来表示。
public class InterestGraph{
    private int maxLen;  //矩阵的最大长度
    private int edgeNum; //边的条数
    private List<User> users;//用户列表
    private List<interest> interests;//兴趣列表，只能是兴趣树中的叶子
    private int edges[][];  //边
}

#四、接口设计
##1、兴趣搜集子系统：
public interface IinterestGather{
    public List<Interest> gather(Object obj);
    public void save(User user, List interests);
}
##2、兴趣构建子系统：
public class IinterestBuild{
    public InterestGraph build();
}
##3、兴趣应用子系统：
public class IinterestApply{
    public getInterestsByUserId(Integer userId);
    public getNearbyUsersByUserId(Integer userId);
    public getUsersByInterestId(Integer interestId);
}
