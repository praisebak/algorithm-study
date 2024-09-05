import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ClientInfoStatus;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    private int N;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(s);

        int[] arr = new int[N];
        int[] lis = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        lis[0] = arr[0];

        int lisLen = 0;
        for(int i=1;i<N;i++){
            int cur = arr[i];
            if(cur > lis[lisLen]){
                lisLen++;
                lis[lisLen] = cur;
            }else{
                int idx = binSearch(0,lisLen,arr[i],lis);
                lis[idx] = cur;
            }
        }
        System.out.println(lisLen+1);


    }

    private int binSearch(int start, int end, int val,int[] lis) {
        int mid = 0;
        while(start < end){
            mid = (start + end) / 2;
            if(lis[mid] < val){
                start = mid +1;
            }else{
                end = mid;
            }
        }
        return end;
    }

}
