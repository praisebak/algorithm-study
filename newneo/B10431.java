package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



class B10431 {
    int P;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(bufferedReader.readLine());

        for(int i=0;i<P;i++){
            String s = bufferedReader.readLine();
            String[] splited = s.split(" ");
            int num = Integer.parseInt(splited[0]);

            List<Integer> list = new ArrayList<>();

            int count = 0;
            for(int j=1;j<=20;j++){
                int val = Integer.parseInt(splited[j]);
                if(list.size() == 0){
                    list.add(val);
                }else{
                    int index = list.size()-1;
                    for(;index >= 0; index--){
                        if(list.get(index) > val)
                            count++;
                        else{
                            break;
                        }
                    }

                    list.add(index+1,val);
                }
            }

            System.out.println(num + " " + count);
        }
    }
}



