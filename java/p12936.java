import java.util.ArrayList;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }
        //팩토리얼 계산 및 리스트에 값 미리 넣어두기

        int i = 0;
        long remain = k - 1;
        
        while (i < n) {
            //현재 자리수에 대한 팩토리얼 계산
            factorial = factorial / (n - i);
            
            //첫번째에 올 값 계산
            long value = remain / factorial;
            
            answer[i++] = list.get((int)value);
            list.remove((int)value);
            //다음 자릿수를 위해서 계산
            remain = remain % factorial;
        }

        return answer;
    }
}