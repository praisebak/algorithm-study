import java.util.*;


class Solution {
    class Fee{
        int carNumber;
        int time;
        boolean isIn;
        public Fee(int curNumber,int time,boolean isIn){
            this.carNumber = curNumber;
            this.time=time;
            this.isIn = isIn;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        
        //원하는것 = 요금 계산하기

        //조건 = 입고후 출차내역없으면 23:59
            //입고 했으면 다음은 무조건 out이래 그럼 Integer,String으로 깔끔하게 해도 될것같은데
            //만약 inputMap에 남아있는게 in이면 23:59로 처리하기
        Map<Integer,Fee> inputMap = new HashMap<>();
        Map<Integer,Integer> timeMap = new HashMap<>();
        Map<Integer,Integer> moneyMap = new HashMap<>();

        
        for(String s : records){
            String[] sArr = s.split(" ");
            int time = getTime(sArr[0]);
            int carNum =  Integer.parseInt(sArr[1]);
            boolean isIn =  sArr[2].equals("IN");

            
            //이게 in이면 지금 온거는 out이라는뜻
            if(inputMap.get(carNum) != null && inputMap.get(carNum).isIn){
                int sumTime = time - inputMap.get(carNum).time;
                
                timeMap.put(carNum,timeMap.getOrDefault(carNum,0) + sumTime);
                
                inputMap.put(carNum,new Fee(carNum,sumTime,isIn));
            }else{
                inputMap.put(carNum,new Fee(carNum,time,isIn));                 
            }
        }  
        
        int lastTime = 23 * 60 + 59;
        int[] answer = new int[inputMap.size()];
        
        List<Integer> sorted = new ArrayList<>(inputMap.keySet());
        Collections.sort(sorted);
        for(Integer i : sorted){
            if(inputMap.get(i).isIn){
                int sumTime = timeMap.getOrDefault(i,0) + lastTime - inputMap.get(i).time;
                timeMap.put(i,sumTime);
            }
        }
        
        Map<Integer,Integer> sumMap = new HashMap<>();
        
        int idx = 0;
        for(Integer i : sorted ){
            int time = timeMap.get(i);
            answer[idx++] = cal(time,fees);
        }
            //이거 할려면 뭘로하는게 좋을까 Map<Integer,List<Integer>>
        
            //누적 시간이 기본 시간 이하라면 기본 요금 청구
            //초과면 단위 시간마다 단위 요금
        //올림해야한다 = a보다 큰 최소의 정수로
        

        
        return answer;
    }
    
    public int cal(int time,int[] fees){
        int result = 0;
        
        if(time >= fees[0]){
            result += fees[1];
            time -= fees[0];
        }else{
            return fees[1];
        }
        
        int times = (int) Math.ceil((double) time / fees[2]);
        
        result += fees[3] * times;

        return result;
    }
    
    public int getTime(String s){
        String[] aArr = s.split(":");
        int hour = Integer.parseInt(aArr[0]) * 60;
        int min = Integer.parseInt(aArr[1]);
        
        return hour + min;
    }
}