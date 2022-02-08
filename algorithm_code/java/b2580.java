import java.util.*;

class Solu
{
    int sudoku[][] = new int[9][9];

    void printAll()
    {
        for(int i=0;i<sudoku.length;i++)
        {
            for(int j=0;j<sudoku[i].length;j++)
            {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
        System.exit(0);

    }

    void solution()
    {
        dfs(0,0);
    }

    boolean isValid(int row,int cal,int val)
    {

        for(int j=0;j<9;j++)
        {
            if(val == sudoku[row][j])
            {
                return false;
            }
            if(val == sudoku[j][cal])
            {
                return false;
            }
        }

        //같은 칸 내에서 계산
        //몇번째 네모냐
        //0 1 2
        //3 4 5
        //6 7 8
        int startRow = (row / 3) * 3;
        int startCal = (cal / 3) * 3;

        for(int i=startRow;i<=startRow+2;i++)
        {
            for(int j=startCal;j<=startCal+2;j++)
            {
                if(sudoku[i][j] == val)
                {
                    return false;
                }
            }
        }
        return true;
    }

    void dfs(int row,int cal)
    {  
        if(cal == 9)
        {
            dfs(row+1,0);
            return;
        }
        if(row == 9)
        {
            printAll();
        }
         
        if(sudoku[row][cal] == 0)
        {
            for(int k=1;k<=9;k++)
            {
                    if(isValid(row, cal, k))
                    {
                        sudoku[row][cal] = k;
                        dfs(row,cal+1);
                    }
            }
            sudoku[row][cal] = 0; 
            return;
        }
        dfs(row,cal+1);
    }

    
}

class Main
{
    

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Solu solution = new Solu();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                int curVal = sc.nextInt();
                solution.sudoku[i][j] = curVal;
            }       
        }
        sc.close();

        solution.solution();

    }

}