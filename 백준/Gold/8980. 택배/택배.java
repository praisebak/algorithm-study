import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.spi.AbstractResourceBundleProvider;
import javax.swing.Box;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{

    class Box{
        int end;
        int val;

        public Box(int end, int val) {
            this.end = end;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "end=" + end +
                    ", val=" + val +
                    '}';
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int TOWN = Integer.parseInt(sArr[0]);
        int MAX_WEIGHT = Integer.parseInt(sArr[1]);
        int T = Integer.parseInt(bufferedReader.readLine());

        Map<Integer,List<Box>> townBox = new HashMap<>();

        for (int i = 0; i < T; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            int val = Integer.parseInt(sArr[2]);
            List<Box> orDefault = townBox.getOrDefault(start, new ArrayList<>());
            orDefault.add(new Box(end, val));

            townBox.put(start,orDefault);
        }

        for(int key : townBox.keySet()){
            List<Box> boxList = townBox.get(key);
            Collections.sort(boxList, Comparator.comparingInt(o -> o.end));
        }

        List<Box> currentBoxs = new ArrayList<>();
        int currentWeight = 0;

        int answer = 0;
        for(int i=1;i<=TOWN;i++){
            //내리기

            for (Box curBox : currentBoxs){
                if(curBox.end == i){
//                    System.out.println(i + "마을 박스 내리기 = " + curBox.toString());
                    answer += curBox.val;
                    currentWeight -= curBox.val;
                    curBox.val = 0;
                }
            }

            //i번 마을에서 실을 수 있는 박스
            for(Box box : townBox.getOrDefault(i,new ArrayList<>())){
//                System.out.println(i + "마을 실을 박스 = " + box.toString() + " 무게 = "+ currentWeight);
                //되는대로 담기
                if(MAX_WEIGHT > currentWeight){
                    //다 실었을때 초과하는 경우
                    //차액만 실어야함
                    if(box.val + currentWeight > MAX_WEIGHT){
//                        System.out.println("다실었으면 초과함");
                        box.val -= MAX_WEIGHT - currentWeight;
                        currentBoxs.add(new Box(box.end,MAX_WEIGHT - currentWeight));
                        currentWeight = MAX_WEIGHT;

                    //다 실었는데 초과안함
                    // 다담기
                    }else{
                        int tmp = box.val;
//                        System.out.println("다 실으면 초과안함");
                        currentWeight += box.val;
                        currentBoxs.add(new Box(box.end,tmp));
                        box.val = 0;
                    }
                }


                int len = currentBoxs.size();
                //내가 실었던 박스들중에서 개선할거 있는지 찾아보기
                for(int currentIdx= 0;currentIdx < len;currentIdx++){
                    Box currentBox = currentBoxs.get(currentIdx);
                    //개선할거 있음 - 어떻게 개선하지?
                    //내거를 더 빨리 도착하는 박스랑 바꾸면됨
                    if(currentBox.end > box.end){
//                        System.out.println(i + "마을 개선할 박스 있음 = " + box.toString() + " 과 내거 바꾸기 = " + currentBox.toString());

                        //다음 박스가 더 크면 일단 최대한 바꾸기
                        if(box.val > currentBox.val){
                            int tmpCurrentBox = currentBox.val;
                            box.val -= currentBox.val;
                            currentBox.val = 0;
                            currentBoxs.add(new Box(box.end,tmpCurrentBox));
                        //내 박스가 더 큼
                        }else{
                            int tmpBox = box.val;
                            box.val = 0;
                            currentBox.val -= tmpBox;
                            currentBoxs.add(new Box(box.end,tmpBox));
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
