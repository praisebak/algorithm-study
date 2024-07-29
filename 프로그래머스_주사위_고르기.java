import java.util.*;
class Solution {


    private int N;
    public int[] solution(int[][] dice) {
        int[] answer = {};
        N = dice.length;
        //결국 모든 경우의 수와

        //확률 계산
        //확률 계산 어떻게 하지?

        //확률 계산도 dfs로 해야한다


        List<String> permList = new ArrayList<>();

        for(int i=1;i<=N;i++){
            //주사위 선택하는 dfs
            dfs(permList,i,i + " ",1);

        }


        List<String> dicePermList = new ArrayList<>();
        List<String> opponentPermList = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            numDfs(dicePermList,i,i + " ", 1);
            numDfs(opponentPermList,i,i + " ", 1);
        }

        int resultMax = 0;
        for(String s : permList){
            int[] curDiceArray = Arrays.stream(s.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] oppoDiceArray = getOppoDiceArray(curDiceArray);
            int winCount = 0;

            for(String dicePermStr : dicePermList){
                int[] curDicePerm = Arrays.stream(dicePermStr.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                for (String oppoDicePermStr : opponentPermList) {
                    int[] oppoDicePerm = Arrays.stream(oppoDicePermStr.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    int myResult = 0;
                    int oppoResult = 0;
                    for (int j = 0; j < curDicePerm.length; j++) {
                        int myVal = dice[curDiceArray[j]-1][curDicePerm[j]-1];
                        int oppoVal = dice[oppoDiceArray[j]-1][oppoDicePerm[j]-1];
                        myResult += myVal;
                        oppoResult += oppoVal;
                    }
                    if(myResult > oppoResult) winCount++;
                }
            }

            if(winCount > resultMax){
                System.out.println(resultMax);
                resultMax = winCount;
                answer = curDiceArray;
            }
        }





        return answer;
    }

    private int[] getOppoDiceArray(int[] intArray) {
        boolean[] visit = new boolean[N+1];

        for (int i = 0; i < intArray.length; i++) {
            visit[intArray[i]] = true;
        }
        int[] resultArr = new int[N/2];

        int i=0;
        for (int j = 1; j <= N; j++) {
            if(!visit[j]) {
                resultArr[i] = j;
                i++;
            }
        }

        return resultArr;
    }


    public void numDfs(List<String> permList,int curNum,String s,int depth){
        if(depth == N /2){
            permList.add(s);
            return;
        }

        for(int i=1;i<=N;i++){
            numDfs(permList,i,s + i +  " ",depth+1);
        }

    }


    public void dfs(List<String> permList,int curNum,String s,int depth){
        if(depth == N /2){
            permList.add(s);
            return;
        }

        for(int i=curNum +1;i<=N;i++){
            dfs(permList,i,s + i +  " ",depth+1);
        }

    }
}

//해당 풀이보고 계선해보기
https://aram-su.tistory.com/27
