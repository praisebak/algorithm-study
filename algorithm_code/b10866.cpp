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


class Deque
{
private:
   Node * first;
   Node * last;
   int size;
public:
   Deque()
   {
      this->size = 0;
      this->first = NULL;
      this->last = NULL;
   }

   void push_back(int val);
   void push_front(int val);
   int pop_front();
   int pop_back();
   int front();
   int back();
   int getSize();
   int isEmpty();
};


void Deque::push_back(int val)
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


void Deque::push_front(int val)
{
   Node* newNode = new Node(val);
   if(this->first == NULL)
   {
       this->first = newNode;
       this->last = newNode;
   }
   else
   {
      newNode->next = this->first;
      this->first = newNode;

   }
   this->size++;

}


int Deque::pop_front()
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

int Deque::pop_back()
{
   if(this->first == NULL)
   {
      return -1;
   }
   else
   {
      int tmp = 0;
      tmp = this->last->val;
      if(this->first == this->last)
      {
         this->first = NULL;
         this->last = NULL;
      }
      else
      {
         Node * prevLast = this->first;
         while(prevLast -> next != this->last)
         {
            prevLast = prevLast -> next;
         }
         prevLast -> next = NULL;
         this->last = prevLast;
      }
      this->size--;
      return tmp;
   }

}



int Deque::front()
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

int Deque::back()
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

int Deque::getSize()
{
   return this->size;
}

int Deque::isEmpty()
{
   if(this->first == NULL)
   {
      return 1;
   }
   return 0;
}

void solve()
{
   Deque deque = Deque();
   int n = 0;
   int val = 0;
   string command = "";

   cin >> n;
   for(int i=1;i<=n;i++)
   {
      cin >> command;
      if(command == "push_front")
      {
         cin >> val;
         deque.push_front(val);
         
      }
      if(command == "push_back")
      {
         cin >> val;
         deque.push_back(val);
         
      }

      if(command == "pop_front")
      {
         cout << deque.pop_front() << "\n";
      }

      if(command == "pop_back")
      {
         cout << deque.pop_back() << "\n";
      }

      if(command == "front")
      {
      	cout << deque.front() << "\n";	
      }
      if(command == "back")
      {
         cout << deque.back() << "\n";
      }
      if(command == "size")
      {
      	cout << deque.getSize() << "\n";
      }
      if(command == "empty")
      {
      	cout << deque.isEmpty() << "\n";
      }
      


   }


}

int main()
{
   solve();

   
}







