import java.util.Scanner;
import java.util.Deque;
import java.util.Iterator;

class Pair
{
    Integer key;
    Integer value;

    public Pair(int key,int value)
    {
        this.key = key;
        this.value = value;
    }

    public Integer getKey()
    {
        return this.key;
    }
    public Integer getValue()
    {
        return this.value;
    }

    public void setKey(int key)
    {
        this.key = key;
    }
    public void setValue(int val)
    {
        this.value = val; 
    }
}


class b20055
{
    static int N,K;
    static Deque<Pair> upBelt;
    static Deque<Integer> downBelt;

    static Scanner sc;


    for(Iterator<Pair> iter = upBelt.descendingIterator(); iter.hasNext();)

    static void inputFromIO()
    {
        sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for(int i=0;i<N;i++)
        {  
            upBelt.add(new Pair(sc.nextInt(),0));
        }
        for(int i=0;i<N;i++)
        {
            downBelt.add(sc.nextInt());
        }
    }
    //회전할때는 내리는 위치에 있는애만 내리고
    //올리는 애는 안올림
    static void rotation()
    {
        int downFirst = downBelt.pollFirst();
        Pair upLast = upBelt.pollLast();
        int upLastVal = upLast.getKey();
        downBelt.addLast(upLastVal);
        upBelt.addFirst(new Pair(downFirst,0));
    }

    static void robotMove()
    {
        Itertaion iter = upBelt.Las


    }

    static void solve()
    {  
        int countK = 0;
        int level = 0;
        while(countK < K)
        {
            rotation();
            robotMove();
            level++;
            
        }
    }

    public static void main(String args[])
    {
        inputFromIO();
        solve();
    }
}
