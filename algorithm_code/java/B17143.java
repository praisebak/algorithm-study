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
        SolveB17143 solveB17143 = new SolveB17143();
        solveB17143.init();
        solveB17143.solve();
	}
}

class Shark{
    int r;
    int c;
    int s;
    int d;
    int z;
    boolean isAlive;
    int idx;
    Shark(int r,int c,int s,int d,int z){
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
        this.isAlive = true;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Shark other = (Shark) obj;
        return this.z == other.z;
    }
}


class SolveB17143{
    int answer = 0;
    //sharkList와 sharkMap 관리
    List<Shark> sharkList = new ArrayList<>();
    ArrayList<Shark>[][] sharkMap;
    int R;
    int C;
    int M;
    
    //경계에 만났을때 짝수면 +1 홀수면 -1로.
    
    //위아래오른쪽왼쪽
    int[] dx = {0,0,1,-1};
    int[] dy = {-1,1,0,0};
    
    void init() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        sharkMap = new ArrayList[R][C];
        
        
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sharkMap[i][j] = new ArrayList<>();
            }
        }
        
        for(int i=0;i<M;i++){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r,c,s,d,z);
            shark.idx = i;
            sharkList.add(shark);
            sharkMap[r][c].add(shark);            
        }
    }
    
    
    
    void solve(){
        
        for(int i=0;i<C;i++){
            moveFishMan(i);  
            
            moveShark();    
            
            //상어 중복 체크
            removeDupliShark();
            
            // for(int r=0;r<R;r++){
            //     for(int c=0;c<C;c++){
            //         if(sharkMap[r][c].size() == 0){
            //             System.out.print(0);    
            //         }else{
            //             System.out.print(sharkMap[r][c].get(0).z);    
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println();

        }  
        System.out.println(answer);
        
        
        
        
    }
    
    void removeDupliShark(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(sharkMap[i][j].size() > 1){
                    Shark maxShark = null;
                    
                    for(Shark s : sharkMap[i][j]){
                        if(!s.isAlive) continue;
                        
                        if(maxShark == null){
                            maxShark = s;
                        }else{
                            if(maxShark.z < s.z){
                                maxShark = s;
                            }
                        }
                    }
                    
                    for(Shark s : sharkMap[i][j]){
                        if(!s.isAlive) continue;
                        if(s.z != maxShark.z) {
                            s.isAlive = false;
                        }
                    }


                    
                    // //이게 문제라고 생각함
                    // for(Shark ns: sharkMap[i][j]){
                    //     //크기가 같으면 컨티뉴!
                    //     if(ns.z == maxShark.z) continue;
                    //     else{
                    //         sharkList.remove(ns);
                    //     }
                        
                    // }
                    
                    sharkMap[i][j].clear();
                    sharkMap[i][j].add(maxShark);
                }
            }
        }
    }
    
    
    
    void moveFishMan(int c){
        for(int i=0;i<R;i++){
            List<Shark> curSharkList = sharkMap[i][c];
            if(curSharkList.size() == 0) continue;
            
            for(Shark s : curSharkList){
                if(!s.isAlive) continue;
                answer += s.z;
                s.isAlive = false;
                break;
            }

            sharkMap[i][c].clear();
            return;
        }
    }
    
    void moveShark(){
        for(int si = 0;si<sharkList.size();si++){
            Shark s = sharkList.get(si);
            if(!s.isAlive) continue;
            
            int cY = s.r;
            int cX = s.c;
            int cD = s.d;
            
            int speed = s.s; // 시간초과로 최소한의 이동을 위해 나머지 연산
            if(s.d == 0 || s.d == 1) // 상 하
            	speed %= (R-1) * 2; 
            else if(s.d == 2 || s.d == 3) // 좌 우
            	speed %= (C-1) * 2;
                        
            for(int i=0;i<speed;i++){
                
                int nY = dy[cD] + cY;
                int nX = dx[cD] + cX;
                
                if(nY < 0 || nX < 0 || nY >= R || nX >= C) {
                    if(cD % 2 == 0) cD++;
                    else cD--;
                    
                    nY = dy[cD] + cY;
                    nX = dx[cD] + cX;
                }
                cY = nY;
                cX = nX;
            }
            
            sharkMap[s.r][s.c].remove(s);
            
            s.r = cY;
            s.c = cX;
            s.d = cD;
            
            sharkMap[s.r][s.c].add(s);
        }    
        
        
        
    }
    
}




