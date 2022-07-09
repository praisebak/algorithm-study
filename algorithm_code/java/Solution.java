import java.util.ArrayList;
import java.util.Collections;

class Solution
{

    public String solution(int n, int t, int m, String[] timetable)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(String str : timetable)
        {
            list.add(convertTimeToSec(str));
        }
        Collections.sort(list);

        int curTimeSec = 9 * 3600;
        int secAddUnit = 60 * t;
        int shuttleSeat = 0;

        for(Integer sec : list)
        {
            if(sec <= curTimeSec)
            {
                shuttleSeat++;
            }

            if(shuttleSeat == m)
            {
                shuttleSeat = 0;
            }

        }

        String answer = "";
        return answer;
    }

    private int convertTimeToSec(String str)
    {
        int hourToSec = Integer.parseInt(str.split(":")[0]) * 3600;
        int minToSec = Integer.parseInt(str.split(":")[1])  * 60;
        int sec = hourToSec + minToSec;
        return sec;
    }

}
