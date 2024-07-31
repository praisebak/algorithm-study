import java.util.*;
import java.util.stream.Collectors;

class Solution {


    private int N;
    public int[] solution(int[][] dice) {
        int[] answer = {};
        N = dice.length;

        List<String> permList = new ArrayList<>();

        for(int i=0;i<N;i++){
            //주사위 선택하는 dfs
            dfs(permList,i,i + " ",1);
        }

        int resultMax = 0;
        for(String s : permList){
            int[] curDiceArray = Arrays.stream(s.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] oppoDiceArray = getOppoDiceArray(curDiceArray);
            int winCount = 0;
            int loseCount = 0;

            HashMap<Integer, Integer> myScoreCnt = calculateScoreCnt(curDiceArray, 0, N / 2,dice);
            HashMap<Integer, Integer> oppScoreCnt = calculateScoreCnt(oppoDiceArray, 0, N / 2,dice);

            for (int m : myScoreCnt.keySet()) {
                for (int o : oppScoreCnt.keySet()) {
                    if (m > o) {
                        winCount += (myScoreCnt.get(m) * oppScoreCnt.get(o));
                    } else if (m < o) {
                        loseCount += (myScoreCnt.get(m) * oppScoreCnt.get(o));
                    }
                }
            }

            if (resultMax < winCount) {
                resultMax = winCount;
                answer = curDiceArray;
            }
            if (resultMax < loseCount) {
                resultMax = loseCount;
                answer = oppoDiceArray;
            }
        }
        for(int i=0;i<answer.length;i++){
            answer[i]++;
        }





        return answer;
    }
    private ArrayList<Integer> score = new ArrayList<>();
    private HashMap<Integer, Integer> calculateScoreCnt(int[] combi, int curDepth, int maxDepth,int[][] dice) {
        if (curDepth == maxDepth) {
            HashMap<Integer, Integer> scoreCnt = new HashMap<>();
            for (int s : score) {
                if (!scoreCnt.containsKey(s)) {
                    scoreCnt.put(s, 1);
                } else {
                    scoreCnt.put(s, scoreCnt.get(s) + 1);
                }
            }
            score.clear();
            return scoreCnt;
        }
        int idx = combi[curDepth];
        if (score.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                score.add(dice[idx][i]);
            }
        } else {
            int size = score.size();
            for (int i = 0; i < size; i++) {
                int s = score.remove(0);
                for (int j = 0; j < 6; j++) {
                    score.add(dice[idx][j] + s);
                }
            }
        }
        return calculateScoreCnt(combi, curDepth + 1, maxDepth,dice);
    }

    private int[] getOppoDiceArray(int[] intArray) {
        boolean[] visit = new boolean[N+1];

        for (int i = 0; i < intArray.length; i++) {
            visit[intArray[i]] = true;
        }
        int[] resultArr = new int[N/2];

        int i=0;
        for (int j = 0; j < N; j++) {
            if(!visit[j]) {
                resultArr[i] = j;
                i++;
            }
        }
        return resultArr;
    }

    public void dfs(List<String> permList,int curNum,String s,int depth){
        if(depth == N /2){
            permList.add(s);
            return;
        }

        for(int i=curNum +1;i<N;i++){
            dfs(permList,i,s + i +  " ",depth+1);
        }

    }
}

