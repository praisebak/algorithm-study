package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Main_B1976{

    public static void main(String[] args) throws IOException{
        B1976_여행가자 b1976 = new B1976_여행가자();
        b1976.solve();
    }
}

class B1976_여행가자 {
    int N;

    int M;

    int[] objCity;
    int[] parent;

    public void union(int a,int b){
        a = find(a);
        b = find(b);
        if(a > b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }

    public int find(int a){
        if(parent[a] == a){
            return a;
        }else{
            return find(parent[a]);
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());
        parent = new int[N+1];
        for(int i = 1;i<=N;i++){
            parent[i] = i;
        }
        String[] inputArr;
        for(int i=1;i<=N;i++){
            inputArr = bufferedReader.readLine().split(" ");
            for(int j=1;j<=inputArr.length;j++){
                boolean isConnected =  inputArr[j-1].equals("1");
                if(isConnected){
                    union(i,j);
                }
            }
        }

        inputArr = bufferedReader.readLine().split(" ");
        objCity = new int[M];
        int start = 0;
        for(int i=0;i<M;i++){
            objCity[i] = Integer.parseInt(inputArr[i]);
            if(i == 0){
                start = find(objCity[i]);
            }else{
                if(find(objCity[i]) != start){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");

    }
}



