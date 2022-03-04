class p19633 {
    class MusicInfo
    {
        int idx;
        int min;
    }
    public String solution(String m, String[] musicinfos) 
    {
        String answer = "(None)";
        String result = "";
        int resultMin = Integer.MIN_VALUE;
        m = replaceNote(m);
        for(int i=0;i<musicinfos.length;i++)
        {
            String[] infos = musicinfos[i].split(",");
            String startTime = infos[0];
            String endTime = infos[1];
            String title = infos[2];
            String note = infos[3];
            note = replaceNote(note);
            int min = getCalcuatedMin(startTime,endTime);
            String resultStr = playedMusicStr(min,note);
            if(resultStr.contains(m))
            {
                if(min > resultMin)
                {
                    resultMin = min;
                    answer = title;
                }
            }
        }



        return answer;
    }

    private String replaceNote(String m) 
    {
        m = m.replace("C#","c");
        m = m.replace("D#","d");
        m = m.replace("F#","f");
        m = m.replace("G#","g");
        m = m.replace("A#","a");

        return m;
    }

    private String playedMusicStr(int min, String note) 
    {
        int playedMin = 0;
        int nodeIdx = 0;
        String resultStr = "";
        
        while(min != playedMin)
        {
            resultStr += note.charAt(nodeIdx++) + "";
            playedMin++;
            if(nodeIdx == note.length() )
            {
                nodeIdx = 0;
            }
        }

        return resultStr;
    }

    private int getCalcuatedMin(String startTime, String endTime) 
    {
        String[] startTimeStr = startTime.split(":");
        String hourStr = startTimeStr[0];
        String startMinStr = startTimeStr[1];
        String[] endTimeStr = endTime.split(":");
        int hourToMin = Integer.parseInt(hourStr) * 60;
        int startMin = Integer.parseInt(startMinStr) + hourToMin;

        hourStr = endTimeStr[0];
        hourToMin = Integer.parseInt(hourStr) * 60;
        String endMinStr = endTimeStr[1];
        int endMin = Integer.parseInt(endMinStr) + hourToMin;

        int resultMin = endMin - startMin;
        return resultMin;
    }
}