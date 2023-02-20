import java.io.CharConversionException;
import java.nio.charset.CharacterCodingException;
import java.util.*;

class Solution 
{
    HashMap<Character,Integer> operatorMap = new HashMap<>();
    ArrayList<Character> operList = new ArrayList<>();
    ArrayList<Character> observedOper = new ArrayList<>();
    ArrayList<Long> numList = new ArrayList<>();
    Character[] validOper ={'+','-','*'};
    long max = Long.MIN_VALUE;

    void setOperator(String expression)
    {
        Integer operMapCheckVal = 0;
        
        String numStr = "";
        
        for(int i=0;i<expression.length();i++)
        {
            operMapCheckVal = operatorMap.get(expression.charAt(i));
            Character curCh = expression.charAt(i);
            //
            if(operMapCheckVal != null)
            {
                if(operMapCheckVal == 0) 
                {
                    observedOper.add(curCh);                   
                    operatorMap.put(curCh,1);
                }
                operList.add(curCh);
                numList.add(Long.parseLong(numStr));
                numStr = "";
            }
            else
            {
                numStr += curCh;
            }
        }        
        numList.add(Long.parseLong(numStr));

    }

    long getConvertedIntFromStr(String operatorStr,String expression)
    {
        ArrayList<Character> tmpOperList = new ArrayList<>();
        ArrayList<Long> tmpNumList = new ArrayList<>();
        Character curOper;
        Long convertedLong = 0L;
        Long result = 0L;
        tmpNumList.addAll(numList);
        tmpOperList.addAll(operList);
        
        for(int i=0;i<operatorStr.length();i++)
        {
            curOper = operatorStr.charAt(i);
            for(int j=0;j<tmpOperList.size();j++)
            {
                if(tmpOperList.get(j) == curOper)
                {

                    convertedLong = getConvertedInt(tmpNumList.get(j), tmpNumList.get(j+1),tmpOperList.remove(j));
                    tmpNumList.remove(j+1);

                    tmpNumList.remove(j);
                    tmpNumList.add(j, convertedLong);
                    j--;
                }
            }
        }
        result = tmpNumList.get(0);
        return Math.abs(result);
    }

    private long getConvertedInt(Long numA,Long numB,Character oper) 
    {
        if(oper=='+')
        {
            return (numA) + (numB);
        }
        else if(oper=='-')
        {
            return (numA) - (numB);
        }
        else
        {
            return (numA) * (numB);
        }
    }

    //curOperator 요소의 인덱스 순으로 우선순위를 가지는 연산자를 표현
    void dfs(String curOperator,String expression,boolean[] visit)
    {
        if(curOperator.length() == observedOper.size())
        {
            max = Math.max(getConvertedIntFromStr(curOperator,expression),max);
        }
        for(int i=0;i<observedOper.size();i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                dfs(curOperator + observedOper.get(i) , expression, visit);
                visit[i] = false;
            }
        }

    }

    public long solution(String expression) 
    {
        operatorMap.put('*', 0);
        operatorMap.put('+', 0);
        operatorMap.put('-', 0);

        boolean visit[] = new boolean[3];
        setOperator(expression);
        dfs("",expression,visit);
        
        long answer = max;
        System.out.println(answer);
        return answer;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("50*6-3*2");
    }
 }
