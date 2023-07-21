package com.study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B17825 {
    public static void main(String[] args) throws IOException {
        SolveB17825 solveB17825 = new SolveB17825();
        solveB17825.init();
        solveB17825.solve();
    }

}

//


class SolveB17825{

    //이 케이스는 값이 같고 blue 같으면 중복 그 외는 같은 수이기만하면 됨
    HashSet<Integer> blueDiffCase = new HashSet<>();

    ArrayList<Integer> diceList = new ArrayList<>();

    ArrayList<Integer>[] mapList;

    int answer = Integer.MIN_VALUE;

    void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()){
            diceList.add(Integer.parseInt( st.nextToken()));
        }

        blueDiffCase.add(22);
        blueDiffCase.add(24);
        blueDiffCase.add(26);
        blueDiffCase.add(28);

        mapList = new ArrayList[4];

        for (int i = 0; i < 4; i++) {
            mapList[i] = new ArrayList<>();
        }

        mapList[1].addAll(Arrays.asList(new Integer[]{10,13,16,19,25,30,35,40,-1}));
        mapList[2].addAll(Arrays.asList(new Integer[]{20,22,24,25,30,35,40,-1}));
        mapList[3].addAll(Arrays.asList(new Integer[]{30,28,27,26,25,30,35,40,-1}));

        for(int i=0;i<=40;i+=2){
            mapList[0].add(i);
        }
        mapList[0].add(-1);
    }

    void solve(){
        permutation(new int[10],0);
        System.out.println(answer);
    }

    private void permutation(int[] horseArr, int length) {

        int[] tmp = {0,0,1,0,2,2,2,0,2,2};
        if(length == 10){
//            for (int i = 0; i < 10; i++)
//            {
////                if(tmp[i] != horseArr[i]) return;
//            }
            cal(horseArr);
            return;
        }

        for(int i=0;i<4;i++){
            horseArr[length] = i;
            permutation(horseArr,length+1);
        }


    }

    void cal(int[] horseArr){
        int[] horseLoca = {0,0,0,0};
        int[] mapIdx = new int[4];
        int result = 0;
        boolean[] isBlue = new boolean[4];

        for(int i=0;i<horseArr.length;i++){
            //주사위 칸 수
            int curMove = diceList.get(i);
            //선택된 말 인덱스
            int selectedHorse = horseArr[i];

            //도착 칸에 있지 않은 말을 골라 이동해야함 그러므로 이 시도는 잘못됐다
            if(horseLoca[selectedHorse] == -1){
                return;
            }

            //다른 말이 이동마치는 칸에 있는지 확인한다
            //이동 마치는 칸

            //몇번째 맵을 사용해서 이동하는지
            int curMapIdx = mapIdx[selectedHorse];
            int curVal = mapList[curMapIdx].get(horseLoca[selectedHorse]);

            if(!isBlue[selectedHorse] && curVal % 10 == 0 && curVal < 40 && curVal >= 10){
                isBlue[selectedHorse] = true;
                horseLoca[selectedHorse] = 0;
                mapIdx[selectedHorse] = curVal / 10;
                curMapIdx = mapIdx[selectedHorse];
            }

            int nextMove = curMove + horseLoca[selectedHorse];
            //도착지점을 넘어섬
            if(nextMove >= mapList[curMapIdx].size()){
                nextMove = mapList[curMapIdx].size()-1;
            }
            curVal = mapList[curMapIdx].get(nextMove);

            for(int h = 0;h<4;h++){
                //자기자신이면 스킵
                if(h == selectedHorse) continue;
                if(horseLoca[h] == -1) continue;
                int nVal = mapList[mapIdx[h]].get(horseLoca[h]);

                if(nVal == curVal){

                    if(nVal == 40){
                        if(isBlue[h] == !isBlue[selectedHorse]){
                            return;
                        }
                    }

                    if(isBlue[h] == isBlue[selectedHorse]){
                        return;
                    }else if(!isBlue[h] == !isBlue[selectedHorse]){
                        return;
                    }
                }
            }

            //칸 이동
            horseLoca[selectedHorse] = nextMove;

            //도착
            if(curVal == -1){
                horseLoca[selectedHorse] = -1;
            }

            if(curVal != -1){
                result += curVal;
            }

        }

        if(result > answer){
//            for (int i = 0; i < horseArr.length; i++) {
//                System.out.print(horseArr[i] + " ");
//            }
//            System.out.println();
//            System.out.println(result);
//            System.out.println();
        }
        answer = Math.max(result,answer);


    }

}
