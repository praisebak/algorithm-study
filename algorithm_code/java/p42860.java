import java.util.Arrays;
import java.util.Scanner;

class p42860
{
    static boolean visit[];
    
    static int getLessAlphaMove(char curCh,char objCh)
    {
        //항상 curCh는 A 아니냐
        int fromRight = 26;
        int objChIdx = objCh - 65;
        return Math.min(objChIdx,fromRight - objChIdx);
    }

    static int[] getLessCursorMove(int curIdx,char[] name,char[] result)
    {
        int resultIdx = 0;
        int rightMove = curIdx;
        int leftMove = curIdx;
        int amountLeftMove = 0;
        int amountRightMove = 0;
        int resultArr[] = new int[2];

        while(result[leftMove] == name[leftMove])
        {
            if(leftMove == 0)
            {
                leftMove = name.length;
            }
            leftMove--;
            amountLeftMove++;
        }
        resultArr[0] = leftMove;
        resultArr[1] = amountLeftMove;
        
        while(result[rightMove] == name[rightMove])
        {
            if(rightMove == name.length-1)
            {
                rightMove = 0;
            }
            rightMove++;
            amountRightMove++;
        }

        if(amountRightMove < Math.abs(amountLeftMove))
        {
            resultArr[0] = rightMove;
            resultArr[1] = amountRightMove;
        }
        return resultArr;
    }



    static int solution(String name) {
        //a에서 obj로가느냐
        //z에서 obj로 가느냐
        //z에서 obj로 가느냐
        int cursor = 0;
        int alphaMove = 0;
        int cursorMoveAmount = 0;
        int move = 0;
        char[] result = new char[name.length()];
        
        for(int i=0;i<result.length;i++)
        {
            result[i] = 'A';
        }
        
        while(!Arrays.equals(result,name.toCharArray()))
        {   
            if(name.charAt(cursor) != result[cursor])
            {
                alphaMove = getLessAlphaMove('A',name.charAt(cursor));
                result[cursor] = (name.toCharArray())[cursor];
            } 
            move+= alphaMove;
            

            if(Arrays.equals(result,name.toCharArray()))
            {
                break;
            }
            int tmp[] = getLessCursorMove(cursor, name.toCharArray(),result);
            cursor = tmp[0];
            move+= tmp[1];
            alphaMove = 0;
        }

        int answer = move;
        return answer;
    }

    public static void main(String [] args)
    {
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        StringBuilder sb=  new StringBuilder();
        for(int i=0;i<N;i++)
        {
            String str = sc.next();   
            System.out.println(solution(str));
            
        }
        
    }



}