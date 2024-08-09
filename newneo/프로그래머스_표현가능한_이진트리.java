import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 탐색 문제임

 **/
class Solution {

    HashSet<Long> numSet = new HashSet<>();
    int maxN = 14;
    List<String> list = new ArrayList<>();
    boolean result = false;

    public int[] solution(long[] numbers) {
        List<Integer> resultList = new ArrayList<>();
        int[] answer = {};
        

        for(long n : numbers) {
            StringBuilder sb = new StringBuilder();
            String s = Long.toBinaryString(n);

            if(n == 0 && s.length() == 1) {
                resultList.add(0);
                continue;
            }

            int mul = 1;
            int size = s.length();

            if(size == 1)   {if(n == 0) result = false;}
            else if(size <= 3) for(int j=0; j<3-size; j++) sb.append("0");
            else if(size <= 7) for(int j=0; j<7-size; j++) sb.append("0");
            else if(size <= 15) for(int j=0; j<15-size; j++) sb.append("0");
            else if(size <= 31) for(int j=0; j<31-size; j++) sb.append("0");
            else if(size <= 63) for(int j=0; j<63-size; j++) sb.append("0");

            sb.append(s);
            result = true;
            treeDepth(sb.toString(),0,sb.toString().length()-1,sb.toString().length());
            if(result){
                resultList.add(1);
            }else{
                resultList.add(0);
            }

        }
        return resultList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void treeDepth(String s,int start,int end,int size) {
        int half = size / 2;
        if(half == 0) return;

        if(s.charAt(start + half) == '0'){
            for (int i = start; i <= end ; i++) {
                if(s.charAt(i) == '1'){
                    result = false;
                }
            }
        }

        treeDepth(s,start,end-1-half,half);
        treeDepth(s,start+half+1,end,half);
    }



}
