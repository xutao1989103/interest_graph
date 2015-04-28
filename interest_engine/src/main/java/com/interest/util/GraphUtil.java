package com.interest.util;

import com.interest.model.InterestGraph;
import com.interest.model.InterestPoint;
import com.interest.model.User;

import java.util.*;

/**
 * Created by 431 on 2015/4/27.
 */
public class GraphUtil {

    private static final Integer MAX_STEP = 1000;
    private static final Integer STOP_PERCENT = 30;

    private static Random random = new Random();

    public static Map<Integer,Integer> getTopKinterests(User user,InterestGraph graph, Integer k){
        Map<Integer,Integer> result ;
        List<User> users = graph.getUsers();
        List<InterestPoint> interestPoints = graph.getInterestPoints();
        int start = users.indexOf(user);
        Map resultMap = initResultMap(interestPoints);
        for(int i = 0; i < MAX_STEP; i++){
            try{
                walk(graph, start, resultMap, i);
            }catch (Exception e){
                continue;
            }


        }
        if(k>resultMap.size()) k = resultMap.size();
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
            int rowCount = rowMap.size();
            int nextRowNum = random.nextInt(rowCount);
            Integer interestId = interestPoints.get(nextRowNum).getInterestId();
            resultMap.put(interestId,(Integer)resultMap.get(interestId)+1);
            walk(graph, (Integer)rowMap.get(nextRowNum), resultMap, times+1);
        }else {
            Map columnMap = graph.gerColumnCount(start);
            int columnCount = columnMap.size();
            int nextColumnNum = random.nextInt(columnCount);
            walk(graph, (Integer)columnMap.get(nextColumnNum), resultMap, times+1);
        }
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
