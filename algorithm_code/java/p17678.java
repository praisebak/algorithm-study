import java.util.ArrayList;
import java.util.Collections;

class Solution
{
    ArrayList<Integer> list = new ArrayList<>();

    public String solution(int n, int t, int m, String[] timetable)
    {

        setListSortedTime(timetable);
        int shuttleN = 1;
        int curShuttleSeat = 0;
        int startSec = 9 * 3600;
        int tmpMinPossbleTime = 9 * 3600;
        String answer = "";
        

        for(int i=0;i<list.size() && shuttleN <= n;i++)
        {
            int cur = list.get(i);
            //자리 수 남음
            if(startSec < cur)
            {
                tmpMinPossbleTime = startSec;
                shuttleN++;
                curShuttleSeat = 0;
                startSec += t * 60;
                i--;
                continue;
            }

            curShuttleSeat++;

            if(m == curShuttleSeat)
            {
                tmpMinPossbleTime = cur - 60;
                startSec += t * 60;
                curShuttleSeat = 0;
                shuttleN++;     
            }
        }

        


        // 셔틀은 왔는데 그때 도착아무도 안해서 못탄경우는? 무시 왜냐 마지막에 탄사람보다 빨리타면되니까.
        
        //자리 없어
        if(shuttleN > n)
        {
            answer =  parseSecToTime(tmpMinPossbleTime);
        }
        else
        { 
            int lastShuttleSec = 9 * 3600;
            lastShuttleSec += t * (n-1) * 60;
            answer = parseSecToTime(lastShuttleSec);
        }

        return answer;
    }


    private String parseSecToTime(int lastShuttleSec) {
        String result = "";
        int hour = lastShuttleSec / 3600;

        if(hour < 10)
        {
            result = "0";
        }
        result += hour + "";
        lastShuttleSec = lastShuttleSec % 3600;

        int min = lastShuttleSec / 60;
        result += ":";

        if(min < 10)
        {
            result += "0";
        }
        result += min;
        return result;
    }






    private void setListSortedTime(String[] timetable) 
    {
        for(String str : timetable)
        {
            int result = convertTimeToSec(str);
            if(result != -1)
            {
                list.add(result);
            }
            Collections.sort(list);
        }

    }

    private int convertTimeToSec(String str)
    {
        int hour = Integer.parseInt(str.split(":")[0]) * 3600;
        int min = Integer.parseInt(str.split(":")[1])  * 60;
        int sec = hour + min;
        if(sec <= 18 * 3600)
        {
            return sec;
        }
        return -1;
    }

}