import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    void hanoi(int n,int from,int to,int mid)
    {
        if(n == 1)
        {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(from);
            tmp.add(to);
            list.add(tmp);
            return;
        }

        hanoi(n-1, from, mid,to);
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(from);
        tmp.add(to);
        list.add(tmp);
        hanoi(n-1, mid, to,from);

    }
    public int[][] solution(int n) {
        hanoi(n,1,3,2);
        int[][] answer = list.stream().map(row -> row.stream().mapToInt(Integer::intValue).toArray())
        .toArray(int[][]::new);
        return answer;
    }
}