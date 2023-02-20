public class p72414 {
    class Solution {

        int timeToInt(String time) {
            String[] times = time.split(":");
            int toSec = 3600;
            int totalTime = 0;
            for(String t : times) {
                int num = Integer.parseInt(t);
                totalTime += num*toSec;
                toSec /= 60;
            }
            return totalTime;
        }
        
        String timeToString(int time) {
            String t = "";
            int hour = time/3600;
            time %= 3600;
            if(hour <10) t+= "0"+ hour +":";
            else t += hour+":";
            
            int minute = time/60;
            time %= 60;
            if(minute <10) t+= "0"+ minute+":";
            else t += minute+":";
            
            int second = time;
            if(second <10) t+= "0"+ second;
            else t += second;
            
            return t;
        }
    
        public String solution(String play_time, String adv_time, String[] logs) {
            String answer = "";
            int maxRange = timeToInt(play_time);
            int advRange = timeToInt(adv_time);
            long[] view = new long[maxRange+1];
    
            for(String log : logs)
            {
                int startInt = timeToInt(log.substring(0,8));
                int endInt = timeToInt(log.substring(9,9+8));
                view[startInt]++;
                view[endInt]--;
            }
    
            for(int i=1;i<=maxRange;i++)
            {
                view[i] = view[i] + view[i-1]; 
            }
    
            for(int i=1;i<=maxRange;i++)
            {
                view[i] = view[i] + view[i-1]; 
            }
    
            long sumView = view[advRange -1];
            long maxView = sumView;
            int answerTime = 0;
            for(int i=1;i<=maxRange - advRange;i++)
            {
                sumView = view[i + advRange-1] - view[i-1];
                if(maxView < sumView)
                {
                    maxView = sumView;
                    answerTime = i;
                }
            }
    
            answer = timeToString(answerTime);
            return answer;
        }
    }
}
