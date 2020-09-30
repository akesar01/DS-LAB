#include<stdio.h>
#include<stdlib.h>
int front =-1;
int rear =-1;
void enqueue();
void dequeue();
void display();
int cirqu[10];
void main()
{
int val;
printf("enter the following details");
printf("\n1.enqueue\n2.dequeue\n3.display\n4.enter 4 to stop\n");
scanf("%d",&val);
while(1){
switch(val)
{
case 1 :{
enqueue();
break;
}
case 2:{
dequeue();
break;
}
case 3:{
display();
}
case 4:{
exit(0);
}
}
printf("\nenter the option\n");
scanf("%d",&val);
}
}
void enqueue()
{
int a;
printf("\nenter the element to be added\n");
scanf("%d",&a);
if (rear==-1)
{
front=rear=0;
cirqu[rear]=a;
}
else if (front!=0 && rear==9)
{
rear=0;
cirqu[rear]=a;
}
else if((front==0 && rear==9) || (rear+1==front) )
{
printf("circular queue is full");
}
else
{
rear++;
cirqu[rear]=a;
}
printf("the element %d is added",a);
}
void dequeue()
{
int v;
v=cirqu[front];
if (rear==front)
{
front=rear=-1;
}
else if(front==9)
{
front=0;
}
else{
front++;
}
printf("the element deleted is %d",v);
}
void display()
{
if(front==-1)
{
printf("circular queue is empty");
}
else if (front<rear)
{
for(int i =front;i<=rear;i++)
{
printf("%d\n",cirqu[i]);
}
}
else{
for(int i=0;i<=rear;i++)
{
printf("%d\n",cirqu[i]);
}
for(int i=front;i<=9;i++)
{
printf("%d",cirqu[i]);
}
}
}

