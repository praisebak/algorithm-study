import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = 0;
        
        //흐리멍텅한 감으로 풀려고해서 머리가 아프고 재미가 없었던거구나
        
        //9:00시작
        //동일한 시간에 중복된 사람 있을 수 있음
        
    
        //n번 운행한다
        
        //시뮬레이션하면된다
        
        /**
        첫번째 버스 = 9시00
        8:00 탐
        //자리가 남음
        //9시에 콘 탄다고 기록
        
        두번째 버스 = 9시 10분
        9:09 탐 
        9:10 탐
        
        자리없음
        못탄다고 가정하고 가장 마지막사람인 0910전에 와야함

//현재 버스에서 탈수있는사람 다 계산
    //자리가 남는다 = 버스 오는시간으로 콘 기상
    //자리가 남지 않는다 = 마지막 사람보다 +1 하도록한다
    
        **/
        
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(String s: timetable){
            String[] sArr = s.split(":");
            int h = Integer.parseInt(sArr[0]) * 60;
            int min = Integer.parseInt(sArr[1]);
            int time = h+min;
            que.add(time);
        }
        
        int busTime = 9 * 60;
        for(int bus=1;bus<=n;bus++){
            int rideCount = 0;
            int prevTime = 0;
            while(!que.isEmpty() && rideCount != m && busTime >= que.peek()){
                rideCount++;
                prevTime = que.poll();
            }
            
            if(rideCount == m){
                answer = (prevTime-1);
            }else{
                answer = busTime;
            }
            busTime += t;
        }
        int hS = (answer / 60);
        int hM = (answer % 60);
        
        StringBuilder sb = new StringBuilder();
        if(hS < 10){
            sb.append("0");
        }
        sb.append(hS);
        sb.append(":");
        if(hM < 10){
            sb.append("0");
        }
        sb.append(hM);
        return sb.toString();
    }
}