import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    int[] monthDay28 = {2};
    int[] monthDay30 = {4,6,9,11};


    class Time implements Comparable<Time>{
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            if(start - o.start == 0){
                return o.end - end;
            }
            return start - o.start;
        }
    }
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = Integer.parseInt(s);
        List<Time> timeList = new ArrayList<>();
        for(int i=0;i<N;i++){
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s, " ");
            int m1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());

            m1 = getMonthToDay(m1);
            m2 = getMonthToDay(m2);

            Time time = new Time(m1+d1,m2+d2);
            timeList.add(time);
        }

        Collections.sort(timeList);

        int prevEndTime = getMonthToDay(3) + 1;
        int objEndTime = getMonthToDay(12) + 1;

        int answer = 0;
        for(int i=0;i<N;i++){
            if(prevEndTime >= objEndTime) break;
            int curEndTime = prevEndTime;

            boolean insertFlowerFlag = true;

            for(int j=i;j<N;j++){
                int nextEndTime = timeList.get(j).end;
                int nextStartTime = timeList.get(j).start;

                //이전에 선택된 꽃이 끝나는 시간보다 빨리 늦게 시작하면 안됨 (겹쳐야함)
                if(curEndTime < nextStartTime){
                    break;
                }

                //더 나은 시간 있으면
                //이미 해당 꽃으로 정해졌으니까 시작을 그냥 굳이 같은걸로 안봐도됨
                if(nextEndTime > prevEndTime) {
                    prevEndTime = nextEndTime;
                    insertFlowerFlag = true;
                    i = j;
                }
            }

            //하나라도 설치해야함
            if(insertFlowerFlag){
                answer++;
            }else{
                break;
            }
        }

        //objEndTime 넘었으면
        if(objEndTime <= prevEndTime){
            System.out.println(answer);
        }else{
            System.out.println(0);
        }
    }

    private int getMonthToDay(int m1) {
        return m1 * 100;
    }
}
