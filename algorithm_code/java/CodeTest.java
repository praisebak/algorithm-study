public class CodeTest
{
    public static void main(String[] args)
    {
        int idxCount = 0;
        int mulNum = 1;
        while(true)
        {
            if(x / mulNum >= 1)
            {
                mulNum *= 10;
                idxCount++;
            }
            else
            {
                break;
            }
        }
        System.out.println("몇자리수인가요?" + idxCount);
        boolean answer = true;
    }
}

