import java.util.*;
import java.io.*;
class Solution {
    int maxScore = 0;
    int N;

    int[][] land;
    public int solution(int[][] land) {
        N = land.length;
        this.land = land;

        int[] newArr = new int[4];
        int[] tmpTmp= new int[4];
        dfs(land, 0,newArr,tmpTmp);


        return maxScore;
    }

    // DFS 탐색
    void dfs(int[][] land, int row2,int[] tmpMaxArr,int[] tmpTmp) {
        // 종료 조건: 마지막 행까지 도달
        for(int row=0;row< N;row++){
                for (int col = 0; col < 4; col++) {
                int prevMax = 0;
                for(int i=0;i<4;i++){
                    if(i == col) continue;
                    prevMax = Math.max(prevMax,tmpTmp[i]);
                }

                tmpMaxArr[col] = land[row][col] + prevMax;
                maxScore = Math.max(tmpMaxArr[col],maxScore);
            }

            for(int i=0;i<4;i++){
                tmpTmp[i] = tmpMaxArr[i];
            }
        }
    }
}
