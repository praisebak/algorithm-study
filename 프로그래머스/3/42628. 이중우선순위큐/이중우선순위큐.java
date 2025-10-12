import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        Map<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>((o1,o2) -> o2 - o1);
        
        for(String s : operations){
            String leftStr = s.split(" ")[0];
            int r = Integer.parseInt(s.split(" ")[1]);
            
            
            while(!right.isEmpty() && map.get(right.peek()) <= 0){
                right.poll();
            }

            while(!left.isEmpty() && map.get(left.peek()) == 0){
                left.poll();
            }
            if(leftStr.equals("I")){
                map.put(r,map.getOrDefault(r,0)+1);
                left.add(r);
                right.add(r);
            }
            if(leftStr.equals("D")){
               

                if(r == -1){
                    if(!left.isEmpty()){
                        int num = left.peek();
                        map.put(num,map.get(num)-1);
                    }
                }else{
                    if(!right.isEmpty()){
                        int num = right.peek();
                        map.put(num,map.get(num)-1);
                    }
                }
            }
            
        }
        while(!right.isEmpty() && map.get(right.peek()) <= 0){
            right.poll();
        }

        while(!left.isEmpty() && map.get(left.peek()) == 0){
            left.poll();
        }
        int lastLeft = 0;
        int lastRight = 0;
        if(!left.isEmpty()){
            lastLeft = left.poll();
        }
        if(!right.isEmpty()){
            lastRight = right.poll();
        }
        return new int[]{lastRight,lastLeft};
    }
}