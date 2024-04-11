package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class B9655 {
    int N;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        if(N % 2 == 0){
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }
    }
}

