import java.util.Scanner;
import java.util.ArrayDeque;
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
    static Deque<Pair> upBelt = new ArrayDeque<>();
    static Deque<Integer> downBelt = new ArrayDeque<>();
    static Scanner sc;
    static int countK = 0;

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
            downBelt.addFirst(sc.nextInt());
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
        Pair afterUpLast = upBelt.getLast();
        if(afterUpLast.value == 1)
        {
            afterUpLast.value = 0;
        }
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
            if(prevRobot == 0 && prev.getKey() > 0 && curBelt.getValue() == 1) 
            {
                prev.setKey(prev.getKey()-1);
                curBelt.setValue(0); 
                prev.setValue(1);
                if(prev.getKey() == 0)
                {
                    countK++;
                }
            }
            prev = curBelt;
            prevRobot = curBelt.getValue();

        }
        prevRobot = upBelt.getLast().value;
        if(prevRobot == 1)
        {
            upBelt.getLast().value = 0;
        }
    }

    static void robotAdd()
    {
        Pair first = upBelt.getFirst();
        if(first.key >= 1)
        {
            first.setValue(1);
            first.key--;
            if(first.key== 0)
            {
                countK++;
            }
        }
    }

    static void solve()
    {  
        int level = 0;
        while(countK < K)
        {
            //System.out.println("***********\nbefore");
            //printAll();
            rotation();
            //System.out.println("\nafter");
            //printAll();
            robotMove();
            //System.out.println("\nafter robot move");
            //printAll();
            robotAdd();
            //System.out.println("\nafter robot add");
            //printAll();
            level++;
            //System.out.println();
            //sc.next();
            //countK = printAll();

        }
        System.out.println(level);
    }

    

    public static void main(String args[])
    {
        inputFromIO();
        solve();
    }
}
