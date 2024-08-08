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
    public int[] solution(long[] numbers) {
        int[] answer = {};
        int mul = 1;
        int prev = 1;
        for (int i = 1; i <= 31; i += mul){
            //leafNode = 0, 1, 3
            dfs("",i,prev);
            mul *= 2;
            prev = i;
        }


        for(String s : list){
            List<String> result = new ArrayList<>();
            treeDepth(s,0,result);

            StringBuilder stringBuilder = new StringBuilder();
            result.forEach(stringBuilder::append);

            long val = Long.parseLong(stringBuilder.toString(),2);
            numSet.add(val);
        }

        List<Integer> resultList = new ArrayList<>();

        for(long l : numbers){
            if(numSet.contains(l)){
                resultList.add(1);
            }else{
                resultList.add(0);
            }
        }

        return resultList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void treeDepth(String s,int idx,List<String> result) {
        if(idx >= s.length()) return;
        treeDepth(s,idx*2+1,result);
        result.add(s.charAt(idx) +"");
        treeDepth(s,idx*2+2,result);
    }

    /**
     * 루트 노드가 0인경우를 고려해야하는지?
     * 일단 루트 노드가 0인경우는 없다고 가정하고 시작
     *
     * @param i
     * @param s
     * @param leafNode
     */
    private void dfs(String s, int maxLen, int leafNode) {
        if(maxLen <= s.length()){
            //루트 노드 0인경우 제외
            list.add(s);
            return;
        }

        //비어있다면 0이아닌 1부터 시작하도록
        if(!s.isEmpty() && leafNode <= s.length()){
            dfs(s+"0",maxLen, leafNode);
        }
        dfs(s+"1",maxLen, leafNode);
    }
}
