/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        SolveB19236 solveB19236 = new SolveB19236();
        solveB19236.init();
        solveB19236.solve();
    }
}

class Shark{
    int y;
    int x;
    int[][] map;
    int count;
    int[][] dir;
    int sDir;
    Shark(int y,int x,int[][] map,int[][] dir,int count){
        this.y = y;
        this.x = x;
        this.map = map;
        this.dir = dir;
        this.count = count;
    }
    
     Shark(int y,int x,int[][] map,int[][] dir,int count,int sDir){
        this.y = y;
        this.x = x;
        this.map = map;
        this.dir = dir;
        this.count = count;
        this.sDir = sDir;
    }
    
}

class SolveB19236{
    int[][] map = new int[4][4];
    int[][] dir = new int[4][4];
    int N;
    int M;
    Queue<Shark> sharkList = new LinkedList<>();
    int sR = 0;
    int sC = 0;


    void init() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0;i<4;i++){
            st = new StringTokenizer(in.readLine());
            for(int j=0;j<4;j++){
                int num = 0;
                num = Integer.parseInt(st.nextToken());
                dir[i][j] = Integer.parseInt(st.nextToken())-1;
                map[i][j] = num;
            }
        }
    }


    int[] dx = {0,-1,-1,-1,0,1,1,1};
    int[] dy = {-1,-1,0,1,1,1,0,-1};
    
     //상어 물고기 먹은 수
    int answer = 0;

    int[][] copyMap(int[][] maps){
        int[][] retMap = new int[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                retMap[i][j] = maps[i][j];
            }
        }
        return retMap;
    }


    void solve(){
        //물고기 이동
        //현재 물고기 번호,map,dir
        //먹었다
        answer = map[0][0];
        map[0][0] = 0;
        Shark init = new Shark(0,0,copyMap(map),null,answer);
        init.sDir = dir[0][0];
        dir[0][0] = 0;
        init.dir = copyMap(dir);
        sharkList.add(init);
        


        while(!sharkList.isEmpty()){
            Shark s = sharkList.poll();
            sR = s.y;
            sC = s.x;
            
            //System.out.println("시작");
            //System.out.println(sR + "," + sC + "," + s.sDir);
            print(map,dir);
            
            dfs(1,s.map,s.dir);
            // print(s.map,s.dir);
            // System.out.println();
            //System.out.println("물고기 변경후");
            print(map,dir);
            sharkMove(sR,sC,s.map,s.dir,s.sDir,s.count);

        }
        System.out.println(answer);
    }
    
  
    void print(int[][] maps,int[][] dirs){
        // System.out.println("print");
        // for(int i=0;i<4;i++){
        //     for(int j=0;j<4;j++){
        //         System.out.print(maps[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        // System.out.println();
        // for(int i=0;i<4;i++){
        //     for(int j=0;j<4;j++){
        //         System.out.print(dirs[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
    }
  
    void sharkMove(int r,int c,int[][] maps,int[][] dirs,int sDir,int eatCount){
        
        boolean yesAdd = false;
        //움직이는 양
        for(int i=1;i<=3;i++){
            
            int nY = r + dy[sDir] * i;
            int nX = c + dx[sDir] * i;
            
            //범위 초과
            if(!isValid(nY,nX)) {
                // System.out.println("상어 추가 ? " + yesAdd);

                return;
            }
            
            //물고기 없음
            if(maps[nY][nX] == 0) continue;
            
            //먹힌 물고기
            int eatenFish = maps[nY][nX];
            int newDir = dirs[nY][nX];
            int[][] tmpMap = copyMap(maps);
            int[][] tmpDir = copyMap(dirs);
            tmpDir[nY][nX] = 0;
            tmpMap[nY][nX] = 0;
            
            sharkList.add(new Shark(nY,nX, tmpMap,tmpDir,eatCount +eatenFish,newDir));
            yesAdd = true;
            // System.out.println("상어 이동 ->" +nY + "," + nX );
            // print(copyMap);
            // System.out.println(eatCount + "->" + (eatCount+eatenFish) + "\n");
            answer = Math.max(answer,eatCount + eatenFish);
        }
        // System.out.println("상어 추가 ? " + yesAdd);
        
    }

    boolean isValid(int y,int x){
        if(y < 0 || x < 0 || y >= 4 || x >= 4) return false;
        return true;
    }

    void dfs(int curFish,int[][] maps,int[][] dirs){
        if(curFish > 16){
            return;
        }
        
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(curFish != maps[i][j]) continue;
                
                //가능하면 진행
                for(int d=0;d<8;d++){
                    int dirV = (d + dirs[i][j]) % 8;
                    int nY = dy[dirV] + i;
                    int nX = dx[dirV] + j;

                    if(possible(nY,nX,maps,dirs)){
                        dirs[i][j] = dirV;
                        changeMapVal(maps,nY,i,nX,j);
                        changeMapVal(dirs,nY,i,nX,j);
                        //System.out.println(curFish + " : " + i + "," + j + " 와 " + nY + "," + nX + " 를 교환");
                        dfs(curFish+1,maps,dirs);
                        return;
                    }
                    
                }
                 
            }
        }
        dfs(curFish+1,maps,dirs);
    }

    private void changeMapVal(int[][]maps, int nY,int r ,int nX,int c){
        int tmp = maps[nY][nX];
        maps[nY][nX] = maps[r][c];
        maps[r][c] = tmp;
    }

    private boolean possible(int row,int cal,int[][] map,int[][] dir ){
        //영역 안
        if(row < 0 || row >= 4 || cal < 0 || cal >= 4) return false;
        //다른 물고기가 있는지,상어가 있는 곳은 아닌지
        if(row == sR && cal == sC) return false;
        
        return true;
    }

}
