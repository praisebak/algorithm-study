#include <stdio.h>
#include <stdlib.h>





int main()
{
   char from_a_txt[30];
   char from_b_txt[30];
   FILE *fp;
   FILE *fw;
   int ax;
   int ay;

   char origin[] = "circle \"c2\" (100,100) radius 3 filled";




   fp = fopen("points.txt", "r");
   //fw = fopen("MyAnimal.asu", 'w');

   while (fscanf(fp, "%s %s", from_a_txt, from_b_txt) != EOF){
   		sprintf(origin, "circle \"c2\" (%s,%s) radius 3 filled",from_a_txt,from_b_txt);
   		ax = atoi(from_a_txt);
   		ay = atoi(from_b_txt);
   		getDistance(f)
   }



   fclose(fp);




   return 0;
}