import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}


class Solve{

    private int[] arr;
    private Integer[] tmp2;

    public void solve() throws IOException{
        BufferedReader bufferedReader=  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = bufferedReader.readLine().split(" ");
        arr = new int[N];
        int[] tmp = new int[N];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
            tmp[i] = arr[i];
            set.add(arr[i]);
        }

        tmp2 = set.toArray(new Integer[0]);
        Arrays.sort(tmp2);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int idx = binSearch(tmp[i]);
            stringBuilder.append(idx).append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    private int binSearch(int val) {
        int left = 0;
        int right = tmp2.length-1;

        int answer = Integer.MAX_VALUE;
        while (left <= right){
            int mid = (left + right) /2;
            if(val <= tmp2[mid]){
                right = mid-1;
                if(val == tmp2[mid]){
                    answer = Math.min(answer,mid);
                }
            }else{
                left = mid +1;
            }
        }
        return answer;
    }
}
