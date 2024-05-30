package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B18428 {
    int N;
    int[][] arr;
    List<String> combList = new ArrayList<>();


    boolean check = false;
    class Node{
        int y;
        int x;
        Node(int y,int x){
            this.y =y;
            this.x =x;
        }
    }
    List<Node> nodeList = new ArrayList<>();

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N][N];
        for(int i=0;i<N;i++){
            String[] s = bufferedReader.readLine().split(" ");
            for(int j=0;j<s.length;j++){
                if(s[j].equals("X")){
                    arr[i][j] = 0;
                }else if(s[j].equals("S")){
                    arr[i][j] = 1;
                //T
                }else{
                    nodeList.add(new Node(i,j));
                    arr[i][j] = 2;
                }
            }
        }


        dfs(0);
        if(check){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }



    public void dfs(int depth){
        if(depth == 3){
            bfs(nodeList);
            return;
        }

        for(int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 0){
                    arr[i][j] = 3;
                    dfs(depth+1);
                    arr[i][j] = 0;
                }
            }
        }

    }

    private void bfs(List<Node> nodeList) {
        //선생님들 돌아다녀서 학생 만나면 return
        for(Node teacher: nodeList){
            for(int i=0;i<4;i++){
                int nY = teacher.y;
                int nX = teacher.x;
                while (isValid(nY,nX)){
                    if(arr[nY][nX] == 3){
                        break;
                        //학생만나면 끝
                    }else if(arr[nY][nX] == 1){
                        return;
                    }
                    nY += dy[i];
                    nX += dx[i];
                }
            }
        }
        check = true;
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};

    private boolean isValid(int y,int x) {
        if(y < 0 || x < 0 || y >=N || x >= N)return false;
        return true;
    }


    public static void main(String[] args) throws IOException {
        B18428 b18428 = new B18428();
        b18428.solve();
    }
}

