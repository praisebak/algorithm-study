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
        SolveB19238 solveB19238 = new SolveB19238();
        solveB19238.init();
        solveB19238.solve();
	}
}


class Point{
    int y;
    int x;
    int fuel;
    Point(int y,int x,int fuel){
        this.y= y;
        this.x= x;
        this.fuel = fuel;
    }
}

class SolveB19238{
    int N;
    int M;
    int fuel;
    List<Point> startPoint = new ArrayList<>();
    List<Point> endPoint = new ArrayList<>();
    int[][] map;
    int startRow = 0;
    int startCal = 0;
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};
    
    void init() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(in.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(in.readLine());
        startRow = Integer.parseInt(st.nextToken())-1;
        startCal = Integer.parseInt(st.nextToken())-1;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            startPoint.add(new Point(r,c,0));
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            endPoint.add(new Point(r,c,0));
        }
    }
    
    void solve(){
        //택시와 시작점이 같다거나
        //시작점과 종료점이 같다거나
        
        while(fuel >= 0){
            int idx = findMinPoint(startRow,startCal);
            //System.out.println(idx);
            if(idx == -1){
                System.out.println(-1);
                return;
            }

            fuel -= startPoint.get(idx).fuel;      
            
            // System.out.println(idx + " 데리러 가는데 쓴 연료 : " + startPoint.get(idx).fuel);
            
            for(Point p : startPoint){
                p.fuel = 0;
            }
            
            int useFuel = goDist(idx);  
            if(useFuel == -1){
                System.out.println(-1);
                return;
            }
            // System.out.println(idx + "집으로 도착하는데 쓴 연료 : " + useFuel);

            if(fuel - useFuel < 0){
                System.out.println(-1);
                return;  
            }
            
            fuel -= useFuel;
            
            fuel += (useFuel * 2);
            
            //System.out.println("현재 연료 : " + fuel);
            startRow = endPoint.get(idx).y;
            startCal = endPoint.get(idx).x;
            
            startPoint.remove(idx);
            endPoint.remove(idx);
            if(startPoint.size() == 0) break;
        }
        
        System.out.println(fuel);
        
    }
    
    
    
    private int findMinPoint(int sR,int sC){
        boolean[][] isVisit = new boolean[N][N];
        Point minPoint = null;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sR,sC,0));
        isVisit[sR][sC] = true;
        
        int result = -1;
        
        //시작점과 택시 위치가 같을 경우
        int tmpResult = 0;
        for(Point s : startPoint){
            if(s.y == sR && s.x == sC){
                return tmpResult;
            }
            tmpResult++;
        }
        
        
        
        while(!q.isEmpty()){
            Point c = q.poll();

            for(int i=0;i<4;i++){
                int nY = c.y + dy[i];
                int nX = c.x + dx[i];
                
                if(!isValid(nY,nX)) continue;
                if(isVisit[nY][nX]) continue;
                if(map[nY][nX] == 1) continue;
                
                //위치 찾기
                int idx = 0;
                for(Point s : startPoint){
                    //같은 위치에 있다
                    if(s.y == nY && s.x == nX){
                        //System.out.println("선택지 : " + s.y + "," + s.x);
                        //여기있는 조건문들 체크
                        if(minPoint == null) {
                            s.fuel = c.fuel + 1;
                            minPoint = s;
                            result = idx;
                        }
                        else if(minPoint != null && minPoint.fuel >= (c.fuel + 1)){
                            if(minPoint.y > s.y){
                                s.fuel = c.fuel + 1;
                                minPoint = s;
                                result = idx;
                            }else if(minPoint.y == s.y && minPoint.x > s.x){
                                s.fuel = c.fuel + 1;
                                minPoint = s;
                                result = idx;
                            }
                        }
                    }
                    idx++;
                }

                if(this.fuel - (c.fuel + 1 ) < 0){
                    break;
                }
                q.add(new Point(nY,nX,c.fuel+1));
                isVisit[nY][nX] = true;
            }

        }
        return result;
    
        
    }
    
    private int goDist(int idx){
        Point curPoint = startPoint.get(idx);
        Queue<Point> q = new LinkedList<>();
        q.add(curPoint);
        Point eP = endPoint.get(idx);
        boolean[][] isVisit = new boolean[N][N];
        
        //시작점과 도착점이 같을 경우
        if(eP.y == curPoint.y && eP.x == curPoint.x) return 0;
        //System.out.println("eP : " + idx + "," + eP.y + "," + eP.x);
        
        while(!q.isEmpty()){
            Point c = q.poll();
            for(int i=0;i<4;i++){
                int nY = dy[i] + c.y;
                int nX = dx[i] + c.x;
                
                if(!isValid(nY,nX)) continue;
                if(isVisit[nY][nX]) continue;
                if(map[nY][nX] == 1) continue;
                
                if(this.fuel - (c.fuel + 1 ) < 0){
                    return -1;
                }
                
                if(nY == eP.y && nX == eP.x){
                    return (c.fuel + 1);
                }
                
                q.add(new Point(nY,nX,c.fuel + 1));
                isVisit[nY][nX] = true;

            }
            
        }
        
        //있으면 안됨
        return -1;
        
        
        
    }
    
    private boolean isValid(int r,int c){
        if(r < 0 || r >= N || c < 0 || c >= N) return false;
        return true;
    }
    
    
}
