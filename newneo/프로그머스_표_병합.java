import java.util.*;
class Solution {

    private final int MAX = 51;
    private final String UPDATE = "UPDATE";
    private final String MERGE = "MERGE";
    private final String UNMERGE = "UNMERGE";
    private final String PRINT = "PRINT";

    String[][] map;

    List<String> printResult = new ArrayList<>();

    public String[] solution(String[] commands) {
        map = new String[MAX][MAX];

        for (int i = 0; i < MAX; i++) {
            Arrays.fill(map[i],"");
        }
        mergeInfoMap.put(0,new ArrayList<>());


        String[] answer = {};
        for(String command : commands){
            String[] splitedCommand = command.split(" ");
            String operator = splitedCommand[0];

            String operand;
            switch(operator){
                case UPDATE:
                    operand = command.split(operator)[1].trim();
                    update(operand);
                    break;
                case MERGE:
                    operand = command.split(operator)[1].trim() ;
                    merge(operand);
                    break;
                case UNMERGE:
                    operand = command.split(operator)[1].trim();
                    unmerge(operand);
                    break;
                default:
                    operand = command.split(operator)[1].trim();
                    print(operand);

                    break;
            }

            if(!operator.equals("UPDATE")){
                printAll();
            }



        }



        return printResult.toArray(new String[0]);
    }

    public void update(String operand){
        String[] operandArr = operand.split(" ");
        //쉘만 바꾸는 명령어
        if(operandArr.length > 2 ){
            int r1 = Integer.parseInt(operandArr[0]);
            int c1 = Integer.parseInt(operandArr[1]);
            int idx = mergeIdxMap[r1][c1];

            String val = operandArr[2];

            //System.out.println("idx = " + idx);
            //System.out.println(val);

            if(idx != 0){
                //System.out.println("prev idx = " + idx +"," + "val = " + mergeValMap.get(idx)  );
                mergeValMap.put(idx,val);
                //System.out.println("after idx = " + idx +"," + "val = " + mergeValMap.get(idx)  );

            }else{
                map[r1][c1] = val;
            }


            //merge면 뭔가 해야함
        }else{
            String valA = operandArr[0];
            String valB = operandArr[1];

            for(int i=1;i<MAX;i++){
                for(int j=1;j<MAX;j++){
                    int idx = mergeIdxMap[i][j];

                    String curVal = map[i][j];
                    if(idx != 0){
                        curVal = mergeValMap.get(idx);
                    }

                    if(curVal.equals(valA)){
                        if(idx != 0){
                            mergeValMap.put(idx,valB);
                        }else{
                            map[i][j] = valB;
                        }
                    }
                }
            }
        }
    }



    //idx접근하기 위한 맵
    int[][] mergeIdxMap = new int[51][51];
    int curIdx = 1;

    //idx merge에 대한 문자열 리스트
    HashMap<Integer,List<String>> mergeInfoMap = new HashMap<>();
    HashMap<Integer,String> mergeValMap = new HashMap<>();


    public void merge(String operand){
        //System.out.println(operand);
        String[] splitedOperand = operand.split(" ");
        //System.out.println(splitedOperand.length);
        int r1 = Integer.parseInt(splitedOperand[0]);
        int c1 = Integer.parseInt(splitedOperand[1]);
        int r2 = Integer.parseInt(splitedOperand[2]);
        int c2 = Integer.parseInt(splitedOperand[3]);
        if(r1 == r2 && c1 == c2) return;


        //지금 merge할려는 idx
        int curMergeIdxA = mergeIdxMap[r1][c1];
        int curMergeIdxB = mergeIdxMap[r2][c2];
        //값이 더 큰쪽으로 항상 맞출려고함
        int mergeIdx = Math.max(curMergeIdxA,curMergeIdxB);

        mergeIdxMap[r1][c1] = curIdx;
        mergeIdxMap[r2][c2] = curIdx++;

        //병합할때 넣을 값
        String valA = curMergeIdxA == 0 ? map[r1][c1] : mergeValMap.get(curMergeIdxA);
        String valB = curMergeIdxB == 0 ? map[r2][c2] : mergeValMap.get(curMergeIdxB);
        String maxVal = valA;
        if(maxVal.isEmpty()){
            maxVal = valB;
        }
        //System.out.println("merge = " + maxVal + " " + r1 + "," + c1 + " and" + r2 + "," + c2);




        if(mergeIdx != 0){
            //기존 map 두개를 합치는 방식으로 구현하기
            List<String> tableListA = mergeInfoMap.get(curMergeIdxA);
            List<String> tableListB = mergeInfoMap.get(curMergeIdxB);

            if(curMergeIdxA == 0) tableListA.add(r1 + " " + c1);
            if(curMergeIdxB == 0) tableListB.add(r2 + " " + c2);

            //기존 맵들 비우기
            mergeInfoMap.remove(curMergeIdxA);
            mergeInfoMap.remove(curMergeIdxB);
            List<String> mergedList = mergeList(tableListA,tableListB);
            //새로운 머지차트를 생성하고
            mergeInfoMap.put(curIdx-1,mergedList);

            //해당 머지차트에 기존의 정보를 합친다(머지차트에 머지차트할 수 있기 때문에)
            for(String chartInfo : mergedList){
                String[] splitedChartInfoList = chartInfo.split(" ");
                int r = Integer.parseInt(splitedChartInfoList[0]);
                int c = Integer.parseInt(splitedChartInfoList[1]);
                mergeIdxMap[r][c] = curIdx-1;
            }
        }else{
            String a = r1 + " " + c1;
            String b = r2 + " " + c2;
            List<String> resultList = new ArrayList<>();
            resultList.add(a);
            resultList.add(b);

            mergeInfoMap.put(curIdx-1,resultList);
        }
        mergeValMap.put(curIdx-1,maxVal);
    }

    public List<String> mergeList(List<String> listA,List<String> listB){
        listA.addAll(listB);
        return listA;
    }

    public void print(String operand){
        int r = Integer.parseInt(operand.split(" ")[0]);
        int c = Integer.parseInt(operand.split(" ")[1]);
        printResult.add(mergeIdxMap[r][c] == 0 ?  (map[r][c].isEmpty() ? "EMPTY" : map[r][c]) : mergeValMap.get(mergeIdxMap[r][c]).isEmpty() ? "EMPTY" : mergeValMap.get(mergeIdxMap[r][c]));
    }

    public void unmerge(String operand){
        String[] tmp = operand.split(" ");
        int r1 = Integer.parseInt(tmp[0]);
        int c1 = Integer.parseInt(tmp[1]);
        int idx = mergeIdxMap[r1][c1];
        String curVal = mergeValMap.get(idx);
        if(curVal == null) curVal = "";
        //System.out.println("unmerge = " + r1 +"," + c1 + "," + idx+ " = " + curVal);

        if(idx != 0){
            List<String> mergedList = mergeInfoMap.get(idx);

            //정보 삭제
            for(String info : mergedList){

                int r = Integer.parseInt(info.split(" ")[0]);
                int c =  Integer.parseInt(info.split(" ")[1]);
                //System.out.println(" 정보 삭제 idx =" + r +"," + c);
                mergeIdxMap[r][c] = 0;
                map[r][c] = "";
            }

            if(mergeValMap.containsKey(idx)){
                mergeValMap.remove(idx);
            }
            if(mergeInfoMap.containsKey(idx)){
                mergeInfoMap.remove(idx);
            }
        }

        map[r1][c1] = curVal;
    }

    public void printAll(){
        for(int i=1;i<=50;i++){
            for(int j=1;j<=50;j++){
                int idx = mergeIdxMap[i][j];
                if(idx != 0){
                    //System.out.print(mergeValMap.get(idx) + " ");
                }else{
                    //System.out.print(map[i][j] + " ");
                }
            }
            //System.out.println();
        }

        //System.out.println("-------------- ");

        for (int i = 1; i <= 50 ; i++) {
            for (int j = 1; j <= 50 ; j++) {
                if(mergeIdxMap[i][j] != 0){
                    //System.out.println(i + "," + j + "," + mergeIdxMap[i][j] + " val = " + mergeValMap.get(mergeIdxMap[i][j]));
                }
            }

        }
        //System.out.println("---------");



    }

}
