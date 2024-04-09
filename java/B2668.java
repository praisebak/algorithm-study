package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        new B2668().solve();
    }
}

class B2668 {

    int N;
    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] upArr = new int[N+1];
        int[] downArr = new int[N+1];

        for(int i=1;i<=N;i++){
            downArr[i] = Integer.parseInt(br.readLine());
        }


//        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                boolean[] upVisit = new boolean[N+1];
                boolean[] downVisit = new boolean[N+1];

                int index = j;
                int nextIndex = downArr[j];


                int count = 0;

                while (!upVisit[index] && !downVisit[nextIndex]){
                    upVisit[index] = true;
                    downVisit[nextIndex] = true;

                    System.out.println("현재 인덱스 = " + nextIndex);
                    //다음 인덱스
                    index = downArr[index];
                    System.out.println("다음 인덱스 = " + index);


                    if(upVisit[nextIndex]){
                        count++;

                    }
                }
                count = count * 2;
                System.out.println(count);
            }
//        }



    }
}

