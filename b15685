import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static boolean[][] visit;

    //우상좌하
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visit = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();
            curve(x,y,d,g);
        }

        int count = 0;
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(visit[i][j] && visit[i+1][j+1] && visit[i+1][j] && visit[i][j+1]) count++;
            }
        }
        System.out.println(count);

    }

    private static void curve(int x, int y, int d, int g) {
        List<Curve> prevCurveList = new ArrayList<>();
        List<Curve> newCurveList = new ArrayList<>();
        visit[y][x] = true;
        //prevLastCurve
        Curve pLC = new Curve(y,x,d);
        //System.out.println("시작점 = " + pLC.toString());

        //각 세대는 전 세대에서
        for (int curG = 0; curG <= g; curG++) {
            int nR = pLC.r;
            int nC = pLC.c;
            int nD = pLC.d;
            //System.out.println("현재 세대 = " + curG);
            if(curG == 0){
                Curve curve = new Curve(nR + dy[nD],nC + dx[nD] , pLC.d);
                //System.out.println(curve.toString());
                prevCurveList.add(curve);
                visit[curve.r][curve.c] = true;
                pLC = curve;
                continue;
            }

            //전세대 끝점부터 시작해서
            for (int i=prevCurveList.size()-1;i != -1;i--){
                nD = (prevCurveList.get(i).d +1) % 4;
                nR += dy[nD];
                nC += dx[nD];
                Curve c = new Curve(nR,nC,nD);
                newCurveList.add(c);
                visit[nR][nC] = true;
                pLC = c;
                //System.out.println(c.toString());
            }
            prevCurveList.addAll(newCurveList);
            newCurveList = new ArrayList<>();
            //System.out.println("다음 드래곤 커브로");
        }



    }


}

class Curve {
     int r;
     int c;
     int d;

    public Curve(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Curve{" +
                "r=" + r +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
