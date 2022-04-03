import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class p17860 
{
    ArrayList<String> cacheList = new ArrayList<>();
    HashMap<String,Integer> strIdxMap = new HashMap<>();

    //9시 30분 시작
    public int solution(int cacheSize, String[] cities) 
    {
        int time = 0;

        if(cacheSize == 0)
        {
            time +=cities.length * 5;
            return time;
        }

        for(int i=0;i<cities.length;i++)
        {
            String curStr = cities[i].toLowerCase();
            //캐쉬가 적중했을 경우
            if(strIdxMap.getOrDefault(curStr, -1) != -1)
            {
                time++;
                //일단 현재 적중된 애를 삭제하고
                //새로운 요소를 제일 앞에 둠으로써 갱신한다
                int curIdx = strIdxMap.get(curStr);
                cacheList.remove(curIdx);
                //여기에 인덱스 값들을 다시 초기화해준다

                for(String key : strIdxMap.keySet())
                {
                    int eachIdx = strIdxMap.get(key);
                    if(strIdxMap.get(key) != -1)
                    {
                        if(curIdx > eachIdx)
                        {
                            eachIdx++;
                        }
                        strIdxMap.put(key,eachIdx);
                    }
                }
                cacheList.add(0,curStr);
                strIdxMap.put(curStr,0);
            }
            //적중하지 못했을 경우
            else
            {
                time+=5;
                if(cacheSize == cacheList.size())
                {
                    String removedStr = "";
                    //마지막 캐쉬를 삭제
                    removedStr = cacheList.remove(cacheList.size()-1);
                    strIdxMap.put(removedStr,-1);
                }
                //새로 들어온 캐쉬 추가
                //인덱스 갱신 해야겠지요

                for(String key : strIdxMap.keySet())
                {
                    int eachIdx = strIdxMap.get(key);
                    if(strIdxMap.get(key) != -1)
                    {
                        strIdxMap.put(key,eachIdx+1);
                    }
                }
                cacheList.add(0,curStr);
                strIdxMap.put(curStr,0);
            }

        }
        int answer = time;
        return answer;
    }


            /*
            //존재하지 않거나 캐쉬에 존재하지 않음으로 갱신해준다
            if(strIdxMap.getOrDefault(curStr, -1) == -1)
            {
                //마지막에 있는 값을 -1로 처리
                if(cacheList.size() >=  cacheSize)
                {
                    strIdxMap.put(cacheList.get(cacheSize-1), -1);
                    cacheList.remove(cacheSize-1);
                }                
                strIdxMap.put(curStr,0);
                time+=5;
            }          
            //이미 있는 요소이므로 캐쉬를 갱신해준다
            else
            {
                int removeIdx = strIdxMap.get(curStr);
                cacheList.remove(removeIdx);
                //한칸씩 갱신
                for(String strKey : strIdxMap.keySet())
                {
                    int val = strIdxMap.getOrDefault(strKey,-1);
                    if(val == -1)
                    {
                        continue;
                    }
                    if(removeIdx > val)
                    {
                        val--;
                    }
                    strIdxMap.put(strKey,val);
                }

                strIdxMap.put(curStr, 0);
                time++;
            }
            cacheList.add(0,curStr);


            

        }
        


        int answer = time;
        return answer;
        */
    
    
}   
