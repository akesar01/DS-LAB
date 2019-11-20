#include<stdio.h>
void enqueue(int );
void dequeue();
void display();
int queue[100];
int front=-1;
int rear=-1;
void main()
{
int val;
int a;
printf("\nselect from the following\nenter 5 to exit\n");
printf("1.enqueue\n2.dequeue\n3.display\n");
scanf("%d",&val);
while(val !=5)
{
switch(val)
{
case 1:{ printf("\nenter the element to be added\n");
scanf("%d",&a);
enqueue(a);
break;}
case 2:{
dequeue();
break;
}
case 3:
{
display();
break;
}
}
printf("\nenter option\n");
scanf("%d",&val);
}
}
void enqueue(int value)
{
if(rear==-1)
{
front=rear=0;
queue[rear]=value;
}
else if(rear==99)
{
printf("\nstack overflow\n");
printf("wrong");
}
else{
rear++;
queue[rear]=value;
}
}
void dequeue()
{
if(front==-1)
{
printf("stack empty");
}
else if (front!=-1 && front==rear)
{
front=rear=-1;
}
else
{
front++;
}
}
void display()
{
for(int i=front;i<=rear;i++)
{
printf("%d\n",queue[i]);
}
}

 
