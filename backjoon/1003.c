#include <stdio.h>
static int zero_count = 0;
static int one_count = 0;
static int count =0;
static int value_save[40];
int main(){
   int test_count = 0;
   int i=0;
   int n;
   scanf("%d",&test_count);
   int zero_count_arr[test_count];
   int one_count_arr[test_count];
   
   for(i=0;i<40;i++){
       value_save[i] = 0;
   }

   for(i=0;i<test_count;i++){
       zero_count = 0;
       one_count = 0;
       scanf("%d",&n);
       fibonacci(n);
       printf("ㅈㄹ=%d ㅇ=%d",zero_count,one_count);
       zero_count_arr[i] = zero_count;
       one_count_arr[i] = one_count;

   }
   for(i=0;i<40;i++){
       printf("%d ",value_save[i]);
   }

   for(i=0;i<test_count;i++){

      printf("%d %d\n",zero_count_arr[i],one_count_arr[i]);

   }
  

    return 0;
}

int fibonacci(int n){
    int result = 0;
    int n_minus_one = 0;
    int n_minus_two = 0;
    if (n==0) {
        return 0;
    }
    else if(n == 1){
        return 1;
    }else {
        if(value_save[n-1] != 0){
            n_minus_one = value_save[n-1];
        }else{
            n_minus_one = fibonacci(n-1);
            value_save[n-1] = n_minus_one;
        }
        if(value_save[n-2] != 0){
            n_minus_two = value_save[n-2];
        }else{
            n_minus_two = fibonacci(n-2);
           
            value_save[n-2] = n_minus_two;
          
        }
        if( n-1 == 1 || n-2 == 1){
            one_count++;
        }
        if(n-1 == 0 || n-2 == 0){
            zero_count++;
        }
        value_save[n] = n_minus_two + n_minus_one;
        return value_save[n];
    }
}
