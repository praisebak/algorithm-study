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


class Stack
{
private:
   Node * top;
   int size;
public:
   Stack()
   {
      this->size = 0;
      this->top = NULL;
   }

   void push(int val);
   int pop();
   int peek();
   int getSize();
   int isEmpty();
};


void Stack::push(int val)
{
   Node* newNode = new Node(val);
   if(this->top == NULL)
   {
       this->top = newNode;
       this->size++;
   }
   else
   {
      newNode -> next = this->top;
      this->top = newNode;
      this->size++;
   }

}

int Stack::pop()
{
   if(this->top == NULL)
   {
      return -1;
   }
   else
   {
      int tmp = 0;
      tmp = this->top->val;
      this->top = this->top->next;
      this->size--;
      return tmp;
   }

}

int Stack::peek()
{
   if(this->top == NULL)
   {
      return -1;
   }
   else
   {
      return this->top->val;
   }
}

int Stack::getSize()
{
   return this->size;
}

int Stack::isEmpty()
{
   if(this->top == NULL)
   {
      return 1;
   }
   return 0;
}

void solve()
{
   Stack stack = Stack();
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
         stack.push(val);
         
      }
      if(command == "top")
      {
      	cout << stack.peek() << "\n";	
      }
      if(command == "size")
      {
      	cout << stack.getSize() << "\n";
      }
      if(command == "empty")
      {
      	cout << stack.isEmpty() << "\n";
      }
      if(command == "pop")
      {
      	cout << stack.pop() << "\n";
      }


   }


}

int main()
{
   solve();

   
}







