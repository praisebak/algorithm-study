class Solution
{
    int repeatCount = 0;
    int removeCount = 0;
    public int[] solution(String s) 
    {
        recursiveFind(s,0);
        int[] answer = {repeatCount,removeCount};
        System.out.println(repeatCount + "," + removeCount);
        return answer;
    }
    private void recursiveFind(String s,int depth) 
    {
        
        if(s.equals("1"))
        {
            this.repeatCount = depth;
            return;
        }
        System.out.print(s + " -> " );

        
        removeCount += s.chars()
            .filter(c -> c == '0')
            .count();  
        s = s.replace("0", "");

        s = sToBinary(s.length());
        System.out.println(s);
        recursiveFind(s,depth+1);
    }
    private String sToBinary(int lenNum) 
    {

        //100
        //4
        //4 / 2 == 2 나머지 0
        //2 % 2 == 1
        //1 % 2 == 1
        String result = "";
        while(lenNum!= 0)
        {
            result += (lenNum % 2) + "";
            lenNum /= 2;
        }
        StringBuffer sb = new StringBuffer(result);
        return sb.reverse().toString();
    }
}