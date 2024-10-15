import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    public void solve() throws IOException{
        HashMap<Integer,Integer> leftMap = new HashMap<>();
        HashMap<Integer,Integer> rightMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int obj = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");
        int A = Integer.parseInt(sArr[0]);
        int B = Integer.parseInt(sArr[1]);
        //perm가 아닌 comb로 짰어야했음
        int[] left = new int[A];
        int[] right = new int[B];
        for (int i = 0; i < A; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            left[i] = num;
        }

        for (int i = 0; i < B; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            right[i] = num;
        }

        //시작 idx
        for (int i = 1; i < A; i++) {

            for (int j = 0; j < A; j++) {
                int curSum = 0;
                for (int k = 0; k < i; k++) {
                    curSum += left[(j+k) % A];
                }
                leftMap.put(curSum,leftMap.getOrDefault(curSum,0)+1);
            }
        }

        for (int i = 1; i < B; i++) {

            for (int j = 0; j <B ; j++) {
                int curSum = 0;
                for (int k = 0; k < i; k++) {
                    curSum += right[(j + k) % B];
                }
                rightMap.put(curSum,rightMap.getOrDefault(curSum,0)+1);
            }
        }


        int all = 0;
        for (int i = 0; i < A; i++) all += left[i];
        leftMap.put(all,leftMap.getOrDefault(all,0)+1);
        all = 0;
        for (int i = 0; i < B; i++) all += right[i];
        rightMap.put(all,rightMap.getOrDefault(all,0)+1);


        long answer = 0;

        for(Integer i : rightMap.keySet()){

            if(i > obj) continue;
            if(i == obj){
                answer += rightMap.get(i);
            }
        }

        for(Integer i : leftMap.keySet()){
            if(i > obj) continue;
            if(i == obj){
                answer += leftMap.get(i);
            }
            if(rightMap.get(obj - i) != null){
                answer += (rightMap.get(obj-i) * leftMap.get(i));
            }
        }
        System.out.println(answer);



    }


}
