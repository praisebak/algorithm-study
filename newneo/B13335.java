package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B13335 {

    class Truck{
        int time;
        int weight;

        public Truck(int time,int weight){
            this.weight = weight;
            this.time = time;
        }
    }

    public void solve() throws IOException {
        int N;
        int W;
        int L;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedStr =  bufferedReader.readLine().split(" ");
        N = Integer.parseInt(splitedStr[0]);
        W = Integer.parseInt(splitedStr[1]);
        L = Integer.parseInt(splitedStr[2]);
        List<Truck> trucks = new ArrayList<>();

        splitedStr =  bufferedReader.readLine().split(" ");
        for(int i=0;i<N;i++){
            int w = Integer.parseInt(splitedStr[i]);
            trucks.add(new Truck(W,w));
        }

        int curTime = 0;
        int curTruckIdx = 0;
        //모든 트럭을 옮기기까지 현재 다리위 무게
        int curWeight = 0;

        //현재 올라탄 트럭리스트
        List<Truck> onBreidgeTruckList = new ArrayList<>();

        //트럭을 올린다.(처음엔 0이고 올리면 1인데 이건 다음턴에 확인해야함
        //트럭이 건넜는지 확인한다.

        int movedTruckCount = 0;
        //다 옮기기전이라면 계속 시도
        while (movedTruckCount != N){
//            System.out.println("현재 시간 = " + curTime + " 무게 = " + curWeight);

            boolean isFirst = true;

            //먼저 옮기고 다음트럭을 올리는식임
            for(int i=0;i<onBreidgeTruckList.size();i++){
                Truck checkTruck = onBreidgeTruckList.get(i);
                //트럭 다 지나감 끝남
                if(checkTruck.time <= 1 && isFirst){
                    isFirst = false;
//                    System.out.println(checkTruck.weight + " 트럭 다 건넘");
                    onBreidgeTruckList.remove(i);
                    curWeight-= checkTruck.weight;
                    movedTruckCount++;
                    i--;
                }else{
                    checkTruck.time--;
                }
            }


            //다음 트럭 이동시키는 부분
            for(int i=curTruckIdx;i<N;i++){
                Truck curTruck = trucks.get(i);
                if(curWeight + curTruck.weight <= L){
//                    System.out.println(curTruck.weight + "인 트럭 다리에 올라탐");
                    onBreidgeTruckList.add(curTruck);
                    curWeight += curTruck.weight;
                    curTruckIdx++;
                    break;
                }else{
                    break;
                }
            }
            curTime++;
        }
        System.out.println(curTime);
    }
    public static void main(String[] args) throws IOException {
        B13335 b13335 = new B13335();
        b13335.solve();
    }
}
