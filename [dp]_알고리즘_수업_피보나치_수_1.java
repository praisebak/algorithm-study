import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    private int recursiveCallCount;  
    private int dynamicCallCount;   
    private int[] fibonacciCache; 

    public static void main(String[] args) throws IOException {
        Main program = new Main();
        program.run();
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        fibonacciCache = new int[n];  
        reader.close();

        recursiveCallCount = 0;
        dynamicCallCount = 0;

        calculateFibonacciRecursive(n);
        calculateFibonacciDynamic(n);

        System.out.println(recursiveCallCount + " " + dynamicCallCount);
    }

    public int calculateFibonacciRecursive(int n) {
        if (n == 1 || n == 2) {
            recursiveCallCount++;  
            return 1;
        } else {
            return calculateFibonacciRecursive(n - 1) + calculateFibonacciRecursive(n - 2);
        }
    }

    public int calculateFibonacciDynamic(int n) {
        fibonacciCache[0] = 1;
        fibonacciCache[1] = 1;

        for (int i = 2; i < n; i++) {
            dynamicCallCount++;
            fibonacciCache[i] = fibonacciCache[i - 1] + fibonacciCache[i - 2];
        }
        return fibonacciCache[n - 1];  
    }
}
