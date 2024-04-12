package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class B2138_second {
    int N;
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String start = br.readLine();
        String answer = br.readLine();

        boolean[] aCase = strToBoolean(start);
        boolean[] bCase = strToBoolean(start);
        boolean[] answerCase = strToBoolean(answer);

        switchOn(aCase,0);
        int aSwitch = 1;
        int bSwitch = 0;
        for(int i=1;i<N;i++) {
            //이전값이 내가 원하는값과 다르다면 스위치누르고 아니면 안누름
            if (aCase[i - 1] != answerCase[i - 1]) {
                switchOn(aCase, i);
                aSwitch++;
            }

            if (bCase[i - 1] != answerCase[i - 1]) {
                switchOn(bCase, i);
                bSwitch++;
            }

            if (Arrays.equals(answerCase, aCase) && Arrays.equals(answerCase, bCase)){
                System.out.println(Math.min(aSwitch, bSwitch));
                return;
            } else if(Arrays.equals(answerCase,aCase) ) {
                System.out.println(aSwitch);
                return;
            }else if(Arrays.equals(answerCase,bCase) ){
                System.out.println(bSwitch);
                return;
            }
        }

        System.out.println(-1);






    }

    public boolean[] strToBoolean(String s){
        boolean[] newBoolean = new boolean[s.length()];
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '0'){
                newBoolean[i] = false;
            }else{
                newBoolean[i] = true;
            }
        }
        return newBoolean;
    }

    public void switchOn(boolean[] aCase, int i){
        if(i == 0){
            aCase[0] = !aCase[0];
            aCase[1] = !aCase[1];
        }else if(i == N-1){
            aCase[i] = !aCase[i];
            aCase[i-1] = !aCase[i-1];
        }else{
            aCase[i-1] = !aCase[i-1];
            aCase[i] = !aCase[i];
            aCase[i+1] = !aCase[i+1];
        }
    }

}




