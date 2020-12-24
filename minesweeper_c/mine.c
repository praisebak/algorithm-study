#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main(){
    int const row = 9;
    int const col = 9;
    int board[9][9];

    printf("9x9 지뢰찾기 입니다\n");
    printf("빙\n");

    init(board,row,col);
    bomb_set(board,row,col);
    board_set(board,row,col);
    print_board(board,row,col);

    cursor(board,row,col);


    return 0;

}



void cursor(int (*board)[9], int row,int col){



}


void board_set(int (*board)[9],int row,int col) {
    int i=0;
    int j=0;
    int bomb_count =0;
    int k=0;
    int l=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            bomb_count = 0;
            if(board[i][j] != -1){
                for(k=-1;k<2;k++){
                    for(l=-1;l<2;l++){

                        if(i+k > 0 && i+k < row){
                            if(j+l > 0 && j+l < col){
                                if(board[i+k][j+l] == -1){
                                    bomb_count++;
                                }
                            }
                        }
                    }
                }
            }
            if(board[i][j] != -1)
            {
                board[i][j] = bomb_count;
            }
        }
    }

}

void print_board(int (*board)[9],int row,int col) {
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            printf("%d ",board[i][j]);
            if(board[i][j] != -1){
                printf(" ");
            }

        }
        printf("\n");
    }
}




void init(int (*board)[9],int row,int col){
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            board[i][j]=0;
        }
    }





}


void bomb_set(int (*board)[9],int row,int col){
    int i=0;
    int j=0;
    srand((int) time(NULL));

    int count = 0;
    int rand_row = 0;
    int rand_col = 0;
    for(count=0;count<10;count++){
        rand_row = (int) rand() % 9;
        rand_col = (int) rand() % 9;
        if(board[rand_row][rand_col] == -1){
            count--;
        }else{
            board[rand_row][rand_col] = -1;
        }

    }

}

