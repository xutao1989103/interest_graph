package com.interest.util;

import com.interest.model.GraphItem;
import com.interest.model.InterestGraph;
import com.interest.model.InterestPoint;
import com.interest.model.User;

import java.util.*;

/**
 * Created by 431 on 2015/4/27.
 */
public class GraphUtil {

    private static Integer MAX_STEP = 1000;
    private static final Integer STOP_PERCENT = 30;

    private static Random random = new Random();

    public static Map<Integer,Integer> getTopKinterests(User user,InterestGraph graph, Integer k){
        Map<Integer,Integer> result ;
        List<User> users = graph.getUsers();
        List<InterestPoint> interestPoints = graph.getInterestPoints();
        if(interestPoints.size()>1000) MAX_STEP = 5000;
        if(interestPoints.size()>5000) MAX_STEP = 10000;
        int start = users.indexOf(user);
        Map resultMap = initResultMap(interestPoints);
        for(int i = 0; i < MAX_STEP; i++){
            try{
                walk(graph, start, resultMap, 0);
            }catch (Exception e){
                continue;
            }
        }
        if(k>resultMap.size()) k = resultMap.size();
        if(k>30) k=30;
        result = getTopKfromMap(resultMap,k);
        return result;
    }

    private static Map<Integer,Integer> initResultMap(List<InterestPoint> interestPoints){
        HashMap<Integer,Integer> result = new HashMap<Integer, Integer>();
        for(InterestPoint ip: interestPoints){
            result.put(ip.getInterestId(), 0);
        }
        return result;
    }

    private static void walk(InterestGraph graph,Integer start, Map resultMap, int times){
        List<InterestPoint> interestPoints = graph.getInterestPoints();
        if(stopOrNot()) throw new RuntimeException("end");
        if(times%2==0){
            Map  rowMap = graph.getRowCount(start);
            int nextRowNum = getNextInt(rowMap);
            GraphItem item = (GraphItem) rowMap.get(nextRowNum);
            Integer interestId = interestPoints.get(nextRowNum).getInterestId();
            resultMap.put(interestId,(Integer)resultMap.get(interestId)+1);
            walk(graph, item.getPosition(), resultMap, times+1);
        }else {
            Map columnMap = graph.gerColumnCount(start);
            int nextColumnNum = getNextInt(columnMap);
            GraphItem item = (GraphItem) columnMap.get(nextColumnNum);
            walk(graph, item.getPosition(), resultMap, times+1);
        }
    }

    private static int getNextInt(Map<Integer,GraphItem> map){
        int result = 0;
        if(map == null || map.size()==0) return result;
        int sum = 0;
        int i=0;
        int[] arr = new int[map.size()];
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, GraphItem> entry = (Map.Entry<Integer, GraphItem>)it.next();
            sum += entry.getValue().getWeight();
            arr[i++]=sum;
        }
        int ran = random.nextInt(sum);
        for(int j = 0; j<arr.length; j++){
            if(ran<arr[j]){
                result = j;
                break;
            }
        }
        return result;
    }

    private static boolean stopOrNot(){
        return random.nextInt(100) < STOP_PERCENT ? true: false;
    }

    private static Map<Integer,Integer> getTopKfromMap(Map map, int k){
        Map<Integer,Integer> result = new LinkedHashMap<Integer, Integer>();
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        ValueComparator vc = new ValueComparator();
        Collections.sort(list, vc);
        for(int i =0; i<k; i++){
            Map.Entry<Integer,Integer> temp = list.get(i);
            result.put(temp.getKey(), temp.getValue());
        }
        return result;
    }

    private static class ValueComparator implements Comparator<Map.Entry<Integer,Integer>>
    {
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    }
}
