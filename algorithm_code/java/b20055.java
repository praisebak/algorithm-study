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
        Iterator<Pair> iter = upBelt.descendingIterator();
        //맨마지막은 어차피 못움직임(내리는 위치니까)
        Pair prev = iter.next();
        int prevRobot = prev.getValue();
        for(; iter.hasNext();)
        {
            Pair curBelt = iter.next();
            if(prevRobot == 0 && curBelt.getKey() != 0 && curBelt.getValue() == 1)
            {
                curBelt.setKey(curBelt.getKey()-1);
                curBelt.setValue(0); 
                prev.setValue(1);
            }
            prev = curBelt;
            prevRobot = curBelt.getValue();
        }
    }

    static void robotAdd()
    {
    }

    static void checkCount()
    {
        
    }

    static void solve()
    {  
        int countK = 0;
        int level = 0;
        while(countK < K)
        {
            rotation();
            robotMove();
            robotAdd();
            checkCount();
            level++;
        }
        System.out.println(level);
    }

    static void printAll()
    {
        Iterator<Pair> iter = upBelt.iterator();
        

    }


    public static void main(String args[])
    {
        inputFromIO();
        printAll();
        solve();
    }
}
