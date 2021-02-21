#include <iostream>
#include <fstream>
using namespace std;

string name[100001] = NULL;
string sortedName[100001] = NULL;
int age[100001] = {0};
int sortedAge[100001] = {0};


void merge(int start,int end)
{
   int mid = (start+end)/2;
   int i = start;
   int j = mid+1;
   int k = start;

   while(i<=mid && j<=end)
   {
      if(age[i] > age[j])
      {
         sortedAge[k] = age[j];
         sortedName[k++] = name[j++];  
      }
      if(age[i] <= age[j])
      {
         sortedAge[k] = age[i];
         sortedName[k++] = name[i++];
      }
   }
   if (i > mid)
   {
      while(j <= end)
      {
         sortedAge[k] = age[j];
         sortedName[k++] = name[j++];

      }
   }
   if(j > end)
   {
      while(i <= mid)
      {
         sortedAge[k] = age[i];
         sortedName[k++] = name[i++];
      }
   }
   for(int i=start;i<=end;i++)
   {
      age[i] = sortedAge[i];
      name[i] = sortedName[i];
   }

}


void mergeSort(int start,int end)
{
   if(end > start)
   {
      int mid = (start+end)/2;
      mergeSort(start,mid);
      mergeSort(mid+1,end);
      merge(start,end);
   }


}


void solve(int n)
{
   for(int i=1;i<=n;i++)
   {
      cin >> age[i] >> name[i];
   }

   ifstream readFile;
   readFile.open("input.txt");

   if(readFile.is_open())
   {
      while(!readFile.eof())
      {

      }
   }


   mergeSort(1,n);
   for(int i=1;i<=n;i++)
   {
      cout<< name[i] << " " << age[i] << endl;
   }
}

int main()
{
}