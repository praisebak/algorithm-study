package newneo;

import java.util.Scanner;

class B20437 {
    private int T;

    public void solve(){
        Scanner sc = new Scanner(System.in);
        T = Integer.parseInt(sc.nextLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<T;i++){
            int[] count = new int[27];
            String s = sc.nextLine();
            int K = Integer.parseInt(sc.nextLine());
            int answerA = Integer.MAX_VALUE;
            int answerB = -1;

            for(int j=0;j<s.length();j++){
                count[s.charAt(j) - 'a']++;
            }

            for(int start=0;start< s.length();start++){
                if(count[s.charAt(start) -'a'] < K){
                    continue;
                }


                int countSameWord =0;
                for(int compareIdx = start;compareIdx < s.length();compareIdx++){
                    if(s.charAt(compareIdx) == s.charAt(start)){
                        countSameWord++;
                    }

                    if(countSameWord == K){
                        answerA = Math.min(answerA,compareIdx - start +1);
                        answerB = Math.max(answerB,compareIdx - start +1);
                        break;
                    }
                }
            }

            if(answerA == Integer.MAX_VALUE || answerB == -1 ){
                sb.append(-1 + "\n");
            }else{
                sb.append(answerA + " " + answerB + "\n");
            }
        }

        System.out.println(sb.toString());
    }
}

