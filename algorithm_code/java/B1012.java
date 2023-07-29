
import java.util.*;
import java.io.*;



public class Main{
    public static void main(String[] args) throws IOException{
        SolveB1012 solveB1012 = new SolveB1012();
        solveB1012.init();
    }


}

class SolveB1012{
    int N;
    int M;
    int K;
    
    int[][] tmpMap;
    boolean[][] isVisit;
    public void init() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        int T = Integer.parseInt(in.readLine());
        
        for(int t=0;t<T;t++){
            int answer = 0;

            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            for(int k=0;k<K;k++){
                st = new StringTokenizer(in.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r][c] = 1;
            }

            tmpMap = map;
            isVisit = new boolean[N][M];
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j] == 1 && !isVisit[i][j]){
                        isVisit[i][j] = true;
                        bfs(i,j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);


        }

    }


    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    class Point{
        int y;
        int x;
        Point(int y,int x){
        
            this.y = y;
            this.x =x;
        
        }
    }
    
    public void bfs(int r,int c){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r,c));
        
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i=0;i<4;i++){
                int nY = p.y + dy[i]; 
                int nX = p.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                
                if(isVisit[nY][nX]) continue;
                
                if(tmpMap[nY][nX] == 0) continue;
                isVisit[nY][nX] = true;
                q.add(new Point(nY,nX));
            }
        }

        
    }


}
