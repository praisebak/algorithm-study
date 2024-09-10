    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    
    class Main{
        public static void main(String[] args) throws IOException{
            Solve solve = new Solve();
            solve.solve();
        }
    }
    class Solve{
        int N;
        int L;
        int R;
        int X;
        private int[] arr;
    
        public void solve() throws IOException{
    
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferedReader.readLine();
            String[] sArr = s.split(" ");
            N = Integer.parseInt(sArr[0]);
            L = Integer.parseInt(sArr[1]);
            R = Integer.parseInt(sArr[2]);
            X = Integer.parseInt(sArr[3]);
    
            arr = new int[N];
    
            s = bufferedReader.readLine();
            sArr = s.split(" ");
            for(int i=0;i<N;i++){
                arr[i]=Integer.parseInt(sArr[i]);
            }
            visit = new boolean[N];
            dfs(0,0, "",0,Integer.MAX_VALUE,0);
            System.out.println(answer);
        }
    
        boolean[] visit;
        int answer = 0;
        private void dfs(int cur, int depth, String s,int sum,int min,int max) {
            if(depth >= 2 && ((max - min) >= X) && sum >= L && sum <= R){
                answer++;
                //조건을 충족하지않는 2개 이상
            }
    
            if(depth >= N){
                return;
            }
    
    
            for(int i=cur;i<N;i++){
                if(visit[i]) continue;
                visit[i] = true;
                dfs(i,depth+1,s + " " + i + " ",sum + arr[i],Math.min(min,arr[i]),Math.max(max,arr[i]));
                visit[i] = false;
            }
    
    
        }
    
    
    }
