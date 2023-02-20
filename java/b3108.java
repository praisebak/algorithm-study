import java.util.*;

class Loca
{
    int x1;
    int x2;
    int y1;
    int y2;

    Loca(int x1,int x2,int y1,int y2)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}

class Main
{
    static boolean[] isVisit;
    static Loca[] map;


    static Boolean isAbleToDrawAtOnce(int i,int j)
    {
        boolean result = true;

        Loca cL = map[i];
        Loca nL = map[j];

        if(cL.x1< nL.x1 && nL.x2 < cL.x2 && cL.y1 < nL.y1 && nL.y2 < cL.y2 ||
         cL.x1 > nL.x1 && nL.x2 > cL.x2 && cL.y1 > nL.y1 && nL.y2 > cL.y2 ||
         cL.x2 < nL.x1 || cL.x1 > nL.x2 || cL.y1 > nL.y2 || cL.y2 < nL.y1)
         {
             result = false;
         }
        return result;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int N = 0;
        N = sc.nextInt();
        int curX1 = 0;
        int curX2 = 0;
        int curY1 = 0;
        int curY2 = 0;
        Queue<Integer> que = new LinkedList<>();
        isVisit = new boolean[N+1];
        map = new Loca[N+1];
        map[0] = new Loca(0,0,0,0);

        for(int i=1;i<=N;i++)
        {
            curX1 = sc.nextInt();
            curY1 = sc.nextInt();
            curX2 = sc.nextInt();
            curY2 = sc.nextInt();

            Loca loca = new Loca(curX1,curX2,curY1,curY2);
            map[i] = loca;
        }

        int count = 0;
        for(int i=0;i<=N;i++)
        {
            if(isVisit[i])
            {
                continue;
            }
            isVisit[i] = true;
            que.add(i);
            while(!que.isEmpty())
            {
                int cur = que.poll();
                for(int j=0;j<=N;j++)
                {
                    if(cur == j 
                    || !isAbleToDrawAtOnce(cur,j)
                    || isVisit[j])  
                    {
                        continue;
                    }
                    isVisit[j] = true;
                    que.add(j);
                }
            }
            count++;
        }
        System.out.println(count-1);
    }


}