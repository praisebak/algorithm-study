import java.util.*;
import java.io.*;
class Solution {
    class Node implements Comparable<Node>{
        int idx;
        int size;
        public Node(int idx,int size){
            this.idx=idx;
            this.size=size;
        }
        
        @Override
        public int compareTo(Node other){
            return size - other.size;
        }
    }
    
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1,s.length());
        s = s.substring(0,s.length()-1);
        s = s.replace("{","");
        
        String[] sArr = s.split("},");
        
        List<Node> list = new ArrayList<>();
        
        int idx = 0;
        for(String cur : sArr){
            String tmp = cur.replace("} ","");
            String[] splited = tmp.split(",");
            list.add(new Node(idx++,splited.length));

        }
        Collections.sort(list);

        idx=0;
        
        List<Integer> results = new ArrayList<>();
        
        Set<String> set = new HashSet<>(); 
        for(Node cur : list){

            sArr[cur.idx] = sArr[cur.idx].replace("}","");
            String[] splited = sArr[cur.idx].split(",");
            
            for(int i=0;i<splited.length;i++){
                if(set.contains(splited[i])) continue;
                
                results.add(Integer.parseInt(splited[i]));
                    
                set.add(splited[i]);
            }
            
            
            idx++;
        }
        int[] result=  new int[results.size()];
        for(int i=0;i<results.size();i++){
            result[i] = results.get(i);
        }
        return result;
    }
}