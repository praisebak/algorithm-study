import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        solve();
    }
    public static void solve() throws IOException
    {
        int N;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        Stack<Integer> s = new Stack<>();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
        {
            int v = Integer.parseInt(st.nextToken());
            arr[i] = v;
        }


        for (int i = 0; i < N; i++)
        {
            while (!s.isEmpty() && arr[s.peek()] < arr[i]){
                arr[s.pop()] = arr[i];
            }
            s.push(i);
        }

        while (!s.isEmpty()){
            arr[s.pop()] = -1;
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int v: arr)
        {
            bufferedWriter.write(v + " ");
        }
        bufferedWriter.flush();
        bufferedWriter.close();


    }
}
