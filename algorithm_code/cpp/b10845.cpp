#include <iostream>
using namespace std;

class Node
{
public:
   int val;
   Node * next;
   Node()
   {
      this->val = 0;
      this->next = NULL;
   }
   Node(int val)
   {
      this->val = val;
      this->next = NULL;
   }

};


class Queue
{
private:
   Node * first;
   Node * last;
   int size;
public:
   Queue()
   {
      this->size = 0;
      this->first = NULL;
      this->last = NULL;
   }

   void push(int val);
   int pop();
   int front();
   int back();
   int getSize();
   int isEmpty();
};


void Queue::push(int val)
{
   Node* newNode = new Node(val);
   if(this->first == NULL)
   {
       this->first = newNode;
       this->last = newNode;
   }
   else
   {
      this->last->next = newNode;
      this->last = newNode; 

   }
   this->size++;

}

int Queue::pop()
{
   if(this->first == NULL)
   {
      return -1;
   }
   else
   {
      int tmp = 0;
      tmp = this->first->val;
      if(this->first == this->last)
      { 
         this->last = NULL;
      }
      this->first = this->first->next;
      this->size--;

      return tmp;
   }

}

int Queue::front()
{
   if(this->first == NULL)
   {
      return -1;
   }
   else
   {
      return this->first->val;
   }
}

int Queue::back()
{
   if(this->last == NULL)
   {
      return -1;
   }
   else
   {
      return this->last->val;
   }


}

int Queue::getSize()
{
   return this->size;
}

int Queue::isEmpty()
{
   if(this->first == NULL)
   {
      return 1;
   }
   return 0;
}

void solve()
{
   Queue queue = Queue();
   int n = 0;
   int val = 0;
   string command = "";

   cin >> n;
   for(int i=1;i<=n;i++)
   {
      cin >> command;
      if(command == "push")
      {
         cin >> val;
         queue.push(val);
         
      }
      if(command == "front")
      {
      	cout << queue.front() << "\n";	
      }
      if(command == "back")
      {
         cout << queue.back() << "\n";
      }
      if(command == "size")
      {
      	cout << queue.getSize() << "\n";
      }
      if(command == "empty")
      {
      	cout << queue.isEmpty() << "\n";
      }
      if(command == "pop")
      {
      	cout << queue.pop() << "\n";
      }


   }


}

int main()
{
   solve();

   
}







