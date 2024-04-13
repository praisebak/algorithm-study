/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class B1655
{

    List<Integer> arr = new ArrayList<>();
    int size = 0;

    //size 0일때는 그냥 넣고 아니면 search한다
    public void binarySearchAndInsert(int val, int start, int end){
        if(arr.isEmpty()){
            arr.add(val);
            return;
        }

        while (start <= end){
            int mid = (start + end) / 2;
            //왼쪽이나 오른쪽이 -1이라면 거기가 들어갈곳

            int curVal = arr.get(mid);
            if(isInsert(mid,curVal)) return;

            if(curVal > val){
                binarySearchAndInsert(val,mid+1,end);
            }else{
                binarySearchAndInsert(val,0,mid-1);
            }
        }
    }

    private boolean isValid(int idx){
        if(idx < 0 || idx == arr.size()){
            return false;
        }
        return true;
    }

    private boolean isInsert(int idx,int val) {
        if(arr.get(idx) == val){
            //중앙에넣는다
            arr.add(idx,val);
        }
        //현재값과 그 사이값으로 들어갈수있는지 확인
        //왼쪽값과 현재값 사이에 있는지
        if(isValid(idx-1)){
            if(arr.get(idx-1) < val && arr.get(idx) > val){
                arr.add(idx-1);
                return true;
            }
        }
        if(isValid(idx+1)){
            if(arr.get(idx) > val && arr.get(idx+1) < val){
                arr.add(idx);
                return true;
            }


        }

        //처음인경우
        if(idx == 0){
            if(arr.get(0) > val){
                arr.add(0,val);
            }else{
                arr.add(1,val);
            }
            return true;

        }
        //끝인경우
        int last = arr.size()-1;
        if(idx == last)
        {
            int lastVal = arr.get(last);
            if(lastVal > val){
                arr.add(last-1,val);
            }else{
                arr.add(last,val);
            }
            return true;
        }
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int i=0;i<N;i++){
            int val = sc.nextInt();
            binarySearchAndInsert(val,0,i);
            System.out.println(arr.get(size/2));
        }

    }
}
