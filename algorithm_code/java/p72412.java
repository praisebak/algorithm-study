import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



class Solution 
{
    HashMap<String,ArrayList<Integer> > map = new HashMap<>();

    public int[] solution(String[] info, String[] query) 
    {
        enterInfoIntoMap(info);
        ArrayList<Integer> result = findByMap(query);
        return result.stream().mapToInt(i->i).toArray();
        /*
        enterInfoInHashMap(info);
        System.out.println(listMap.size());
        int[] answer = findByMap(query);
        return answer;
        */
    }

    private void enterInfoIntoMap(String[] infos) 
    {
        for(String info : infos)
        {
            String[] eachInfo = info.split(" ");
            String[] language = {eachInfo[0] , "-"};
            String[] objPart = {eachInfo[1],"-"};
            String[] jobExp = {eachInfo[2],"-"};
            String[] likeFood = {eachInfo[3],"-"};
            Integer score = Integer.parseInt(eachInfo[4]);
            for(String l : language)
            {
                for(String p : objPart)
                {
                    for(String e : jobExp)
                    {
                        for(String f : likeFood)
                        {
                            String[] modifiedList = {l,p,e,f};
                            String joinStr = String.join(" ",modifiedList);
                            ArrayList<Integer> tmpList = map.getOrDefault(joinStr,new ArrayList<Integer>());
                            tmpList.add(score);
                            map.put(joinStr,tmpList);
                        }
                    }
                }
            }

            
        }
        //values로 바로 접근하는방법 새로 알게됨
            
        for(ArrayList<Integer> scoreList : map.values())
        {
            //파라미터에 null을 줌으로써 기본값인 오름차순으로 정렬
            //아마 될건데 혹시 모르니 체크
            scoreList.sort(null);
        }

    }

    private ArrayList<Integer> findByMap(String[] queries) 
    {
        ArrayList<Integer> result = new ArrayList<>();
        for(String query : queries)
        {
            String[] eachInfo = query.split(" and ");
            int target = Integer.parseInt(eachInfo[3].split(" ")[1]);
            eachInfo[3] = eachInfo[3].split(" ")[0];
            String resultKey = String.join(" ", eachInfo);
            int peopleNum = 0;
            if(map.containsKey(resultKey))
            {
                ArrayList<Integer> list = map.get(resultKey);
                //binarySearch
                peopleNum = getFindPeopleNumByBinSearch(list,target);
            }
            result.add(peopleNum);

        }
        return result;

    }

    private int getFindPeopleNumByBinSearch(ArrayList<Integer> list,int targetNum) 
    {
        int right = list.size();
        int left = 0;
        int mid = 0;
        while(right > left)
        {
            mid = (left + right) / 2;
            if(list.get(mid) >= targetNum)
            {
                right = mid;
            }
            else
            {   
                left = mid+1;
            }
        }
        return list.size() - left;

    }

    public static void main(String[] args) 
    {
        Solution solution = new Solution();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        solution.solution(info, query);
        
    }


    /*
    private int[] findByMap(String[] query) 
    {
        int commonVal = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<listMap.size();i++)
        {
            for(String str : listMap.get(i).keySet())
            {
                System.out.print(str + " " );
            }
            System.out.println();
        }

        for(int i=0;i<query.length;i++)
        {
            String[] condition = query[i].split(" and ");

            for (int j = 0; j < condition.length ; j++) 
            {
                HashMap<String,Integer> map = listMap.get(j);
                System.out.println(condition[j] + " ->" + map.get(condition[j]));
                if(map.get(condition[j]) != null )
                {
                    if(map.)
                    commonVal = Math.min(map.get(condition[j]),commonVal);
                }
            }
            list.add(commonVal);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
    */

    /*
    private void enterInfoInHashMap(String[] info) 
    {
        //"java and backend and junior and pizza 100"
        //java backend junior pizza 100
        HashMap<String,ArrayList<Integer> > = new HashMap<>();
        for(int i=0;i<info.length;i++)
        {
            String[] condition;
            condition = info[i].split(" ");
            for(int j=0;j<condition.length-1;j++)
            {
                int value = Integer.parseInt([condition.length-1]);
                
                if(listMap.size() != condition.length)
                {

                    listMap.add(new HashMap<String,Integer>());
                }
                String val = condition[j];
                if(val.equals("-"))
                {
                    for(String key:  listMap.get(j).keySet())
                    {
                        listMap.get(j).put(key,listMap.get(j).get(key)+1);
                    }
                }
                HashMap<String,Integer> tmpMap = listMap.get(j);
                tmpMap.put(condition[j],tmpMap.getOrDefault(condition[j], 0)+1);
            }
        }
    }
    */ 

    
}