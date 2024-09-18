import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Solve solve = new Solve();
        solve.init();
    }
}
class Pair{
    int h;
    int count;
    Pair(int h,int count){
        this.h = h;
        this.count =count;
    }
}

class Solve{
    int N;
    int[] arr;
    Stack<Pair> stack = new Stack<>();

    void init() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        long count = 0;

        for (int i = 0; i < N; i++)
        {
            int v = Integer.parseInt(bufferedReader.readLine());
            Pair p = new Pair(v,1);

            while(!stack.isEmpty() && stack.peek().h <= v){
                Pair prev = stack.pop();
                count += prev.count;
                if(prev.h == v){
                    p.count += prev.count;
                }
            }

            if(!stack.isEmpty()){
                count++;
            }
            stack.push(p);
        }

        System.out.println(count);
    }



}
