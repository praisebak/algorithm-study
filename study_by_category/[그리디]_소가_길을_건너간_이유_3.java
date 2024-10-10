import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve= new Solve();
        solve.solve();

    }
}

class Solve{
    class Cow{
        int startTime;
        int time;
        public Cow(int startTime,int time){
            this.startTime = startTime;
            this.time = time;
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<Cow> cowList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int startTime = Integer.parseInt(sArr[0]);
            int time = Integer.parseInt(sArr[1]);
            Cow cow = new Cow(startTime,time);
            cowList.add(cow);
        }

        Collections.sort(cowList, (o1,o2) ->{
            if(o1.startTime == o2.startTime) return o2.time - o1.time;
            return o1.startTime-  o2.startTime;
        });

        int curTime = 0;
        for(Cow cow : cowList){
            curTime = Math.max(curTime,cow.startTime);
            curTime += cow.time;
        }

        System.out.println(curTime);



    }
}
