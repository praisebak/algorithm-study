class p12939 {
    public String solution(String s) 
    {
        String[] strArray = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int tmp = 0;
        for(int i=0;i<strArray.length;i++)
        {
            tmp = Integer.parseInt(strArray[i]);
            min = Math.min(min,tmp);
            max = Math.max(max,tmp);
        }
        String answer = Integer.toString(min) + " " + Integer.toString(max);
        return answer;
    }

}