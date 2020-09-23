#include <bits/stdc++.h>

using namespace std;

class Node{
    public:
    int data;
    Node* xorValue;
};
Node* head=NULL;
Node* XOR(Node* a,Node* b)
{
    return (Node*) ((uintptr_t) (a) ^ (uintptr_t) (b));  
}
void insertBeg(int data)
{
    Node* temp = new Node();
    temp->data = data;

    temp->xorValue = XOR(NULL,head);

    if(head==NULL) head=temp;
    else
    {
        head->xorValue = XOR(temp,head->xorValue);
        head=temp;
    }
    
}

void insertEnd(int data)
{
    Node* temp = new Node();
    temp->data = data;

    Node* curr = head;
    Node* prev = NULL;
    Node* next;

    if(head!=NULL)
    {
        while(curr!=NULL)
        {
            next = XOR(prev,curr->xorValue);
            prev=curr;
            curr=next;
        }

        prev->xorValue = XOR(prev->xorValue,temp);
        temp->xorValue = XOR(prev,NULL);
        
    }
    else{
       head=temp;
        temp->xorValue = XOR(NULL,NULL);
    }
    
}

void print()
{
    Node* NOW=head;
    Node* next;
    Node* prev=NULL;
    while(NOW!=NULL)
    {
        cout<<NOW->data<<" ";
        next = XOR(prev,NOW->xorValue);
        prev = NOW;
        NOW = next;
    }
    cout<<endl;
}

int main()
{
    int n;
    while(true)
    {
    cout<<"1)Insert BEG\n2) InsertEnd \n3) Print Values\n4)Stop"<<endl;
    cin>>n;
    int value;
    if(n != 4 && n != 3)
    {
    cout<<"Enter the data "<<endl;
    cin>>value;
    }
    if(n==1) insertBeg(value);
    else if(n==2) insertEnd(value);
    else if(n==3)
    {
        print();
    }
    else 
    {
        /* code */
        break;
    }
}    

 return 0;
}