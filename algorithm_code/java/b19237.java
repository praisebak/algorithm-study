/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/

import java.util.*;
import java.io.*;


public class Main
{
	public static void main(String[] args) throws IOException {
        SolveB19237 solveB19237 = new SolveB19237();
        solveB19237.init();
        solveB19237.solve();
	}
	
}


class Shark implements Comparable<Shark>{  
    int y;
    int x; 
    int num;
    int dir;
    int[][] dArr;
    
    Shark(int y,int x,int num){
        this.y = y;
        this.x = x;
        this.num = num;
        dArr = new int[4][4];
    }
    
    @Override
    public int compareTo(Shark s){
        return this.num - s.num;
    }
    
}

class Smell{
    int num;
    int time;
    Smell(int sharkNum,int leftTime){
        this.num = sharkNum;
        this.time = leftTime;
    }
}


class SolveB19237{
    ArrayList<Shark>[][] sharkMap;
    Smell[][] smellMap;
    ArrayList<Shark> sharkList = new ArrayList<>();
    

    int N; 
    int M;
    int K;
    
    //위 아래 왼쪽 오른쪽
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0}; 
    
    public void init() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sharkMap = new ArrayList[N][N];
        smellMap = new Smell[N][N];

        for(int i=0;i<N;i++){
            st= new StringTokenizer(in.readLine());
            for(int j=0;j<N;j++){
                sharkMap[i][j] = new ArrayList<>();
                int num = 0;
                smellMap[i][j] = new Smell(0,0);
                if((num = Integer.parseInt(st.nextToken())) == 0) continue;
                Shark shark = new Shark(i,j,num);
                smellMap[i][j] = new Smell(num,K);
                //map과 sharkList 둘다 관리해줘야함
                sharkList.add(shark);
            }
        }
        
        //collections.sort
        Collections.sort(sharkList);
        int idx = 0;
        
        st = new StringTokenizer(in.readLine());
        
        for(Shark s : sharkList){
            s.dir = Integer.parseInt(st.nextToken()) -1;
        }

        for(Shark s : sharkList){
            int[][] arr = s.dArr;
            for(int i=0;i<4;i++){
                st= new StringTokenizer(in.readLine());
                for(int j=0;j<4;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken()) -1;
                }
            }    
        }
    }
    
    public void solve(){
        int time = 0;
        
        while(++time <= 1000){
            
            sharkMove();

            //여러개면 쳐내고
            sharkBattle();

            //냄새뿌리기
            smellSpread();
            
            // System.out.println("한 턴 끝");
            if(sharkList.size() == 1 && sharkList.get(0).num == 1){
                System.out.println(time);
                return;
            }
            
        }
        System.out.println(-1);
        
        
    }
    
    private void smellSpread(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                Smell s = smellMap[i][j];
                if(s.num == 0) continue;
                s.time--;
                if(s.time == 0){
                    s.num = 0;
                }
            }
        }
        
        for(Shark s : sharkList){
            smellMap[s.y][s.x] = new Smell(s.num,K);
        }
    }
    
    private void sharkBattle(){

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                ArrayList<Shark> sList = sharkMap[i][j];
                List<Shark> deleteSharkList = new ArrayList<>();

                if(sList.size() < 1) continue;
                // System.out.println("상어 걸러내기");
                // System.out.println(sharkList.size());
                
                Shark minShark = sList.get(0);
                
                for(Shark s : sList){
                    if(s.num < minShark.num){
                        minShark = s;
                        deleteSharkList.add(minShark);
                    }else if(s.num > minShark.num){
                        deleteSharkList.add(s);
                    }
                }
                
                
                for(Shark s : deleteSharkList){
                    sharkList.remove(s);
                }
                
                sharkMap[i][j] = new ArrayList<>();
                sharkMap[i][j].add(minShark);

                // System.out.println(sharkList.size());

                
                
            }
        }
        
    }
    
    private void sharkMove(){
        for(Shark s : sharkList){
            int nextDir = getDir(s);
    
            int nY = s.y + dy[nextDir];
            int nX = s.x + dx[nextDir];

            //이전 맵에서 삭제
            sharkMap[s.y][s.x].remove(s);
            s.y = nY;
            s.x = nX;
            s.dir = nextDir;
            
            //이동
            sharkMap[s.y][s.x].add(s);
        }
    }
    
    private int getDir(Shark s){
        int curDir = s.dir;
        int curNum = s.num;
        for(int i=0;i<4;i++){
            int nextDir = s.dArr[curDir][i];
            int nY = s.y + dy[nextDir];
            int nX = s.x + dx[nextDir];
            if(nY < 0 || nX < 0 || nY >= N  || nX >= N) continue;
            //냄새 없는 칸
            if(smellMap[nY][nX].num == 0){
                // System.out.println("냄새 없는 칸 = " + s.y + ", " + s.x + " -> " + nY + "," + nX);
                return nextDir;
            }
        }
        
        for(int i=0;i<4;i++){
            int nextDir = s.dArr[curDir][i];
            int nY = s.y + dy[nextDir];
            int nX = s.x + dx[nextDir];
            if(nY < 0 || nX < 0 || nY >= N  || nX >= N) continue;
            //자신의 냄새가 있는 칸
            if(smellMap[nY][nX].num == curNum){
                // System.out.println("자신의 냄새 있는 칸 = " + s.y + ", " + s.x + " -> " + nY + "," + nX);


                return nextDir;
            }
        }
        // System.out.println("자신의 방향");
        return curDir;
    }
    
    
}


