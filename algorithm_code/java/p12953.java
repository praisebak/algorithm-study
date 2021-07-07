class p12953 {


    static int gcd(int numA,int numB)
    {
        int result = 0;
        if(numA % numB == 0)
        {
            return numB;
        }
        else
        {
            result = gcd(numB,numA % numB);
        }
        return result;
    }
    static int lcd(int numA, int numB) {
        int gcd = 0;
        gcd = gcd(numA,numB);
        
        return (numA * numB)/gcd;
    }
    
    public int solution(int[] arr) {
        
        int answer = 0;
        int numA = arr[0];
        for(int i = 1;i<arr.length;i++)
        {
            numA = lcd(numA,arr[i]);   
        }
        answer = numA;
        return answer;
    }

}