import java.util.*;
    import java.util.stream.Collectors;
    class MinConditionPair
    {
        int sumMin;
        int prevMin;
        String condition;
        MinConditionPair(int prevMin,String condition)
        {
            this.prevMin = prevMin;
            this.condition = condition;
        }

    }

    class Solution 
    {
        int standardMin;
        int standardFee;
        int unitMin;
        int unitFee;
        HashMap<String,MinConditionPair> map = new HashMap<>();
        public int[] solution(int[] fees, String[] records) 
        {
            
            standardMin = fees[0];
            standardFee = fees[1];
            unitMin = fees[2];
            unitFee = fees[3];
            

            int[] answer = calByRecords(records);
            return answer;
        }

        int[] calByRecords(String[] records)
        {
            //carNum : startMin,condition
            //요금에 대해서 계산 ->
            //in과 out
            for(int i=0;i<records.length;i++)
            {  
                //IN이면 값 넣기만함 OUT이 되면 계산
                int min = getMin(records[i]);
                String[] data = records[i].split(" ");
                String carNum = data[1];
                String condition = data[2];
                MinConditionPair minAndCondition = map.getOrDefault(carNum,new MinConditionPair(min,condition));
                
                //IN이므로 데이터 입력 
                if(condition.equals("IN"))
                {
                    minAndCondition.prevMin = min;
                    minAndCondition.condition = condition;
                }
                else
                {
                    minAndCondition.condition = condition;
                    //계산
                    int prevMin = minAndCondition.prevMin;
                    int nextMin = getMin(records[i]);
                    minAndCondition.sumMin += nextMin - prevMin;
                }
                map.put(carNum,minAndCondition);
            }

            //남아있는 것이 IN인애들 처리 
            for(String key : map.keySet())
            {
                MinConditionPair minAndCondition = map.get(key);
                if(minAndCondition.condition.equals("IN"))
                {
                    minAndCondition.sumMin +=  getMin("23:59") - minAndCondition.prevMin;
                    map.put(key,minAndCondition);
                }
            }
            TreeMap<String, MinConditionPair> sorted = new TreeMap<>();
            sorted.putAll(map);

            ArrayList<Integer> result = new ArrayList<>();

            for(String key : sorted.keySet())
            {
                MinConditionPair minAndCondition = map.get(key);
                int resultNum = standardFee;
                if(minAndCondition.sumMin > standardMin)
                {
                    int exceedMin = minAndCondition.sumMin - standardMin;
                    exceedMin = (int)Math.ceil((double)exceedMin / unitMin);
                    resultNum = exceedMin * unitFee + standardFee ;
                }
                result.add(resultNum);

            }

            return result.stream().mapToInt(i ->i).toArray();
            

        }

        public int getMin(String record)
        {
            String minPart = record.split(" ")[0];
            int min = 0;
            min = Integer.parseInt(minPart.split(":")[0]) * 60 + Integer.parseInt(minPart.split(":")[1]);
            return min;
        }


        public static void main(String[] args) 
        {

            
        }
    }