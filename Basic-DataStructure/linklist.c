#include<stdio.h>
#include<stdlib.h>
struct node
{
struct node*next;
int data;
};
struct node *head =NULL;
struct node*create_node(struct node*);
struct node*insert_at_beg(struct node*);
struct node*insert_at_end(struct node*);
struct node*insert_before(struct node*);
struct node*insert_after(struct node*);
struct node*display(struct node*);
void main()
{
int val;
while(1){
printf("enter any option\n");
printf("\n1.create\n 2.insert at beg\n3.insert at end\n4.insert before\n5.insert after\n6.exit\n7.dispay\n");
scanf("%d",&val);


switch(val){
case 1: { 
head=create_node(head);
              printf("\nlink list created\n");
               break;
}
case 2:{

head=insert_at_beg(head);
break;
}
case 3:{
head=insert_at_end(head);
break;
}
case 4:
{
head=insert_before(head);
break;
}
case 5:
{
head=insert_after(head);
break;
}
case 6:
{
exit(0);
}
case 7:
{
head=display(head);
break;
}
}
}
}
struct node*create_node(struct node*head)
{
struct node*ptr;
struct node*new_node;
int val;
printf("enter -1 to exit\n");
printf("enter the data\n");
scanf("%d",&val);
while(val !=-1)
{
new_node=(struct node*)malloc(sizeof(struct node));
new_node->data=val;
if (head==NULL)
{
new_node->next=NULL;
head=new_node;
}
else
{
ptr=head;
while(ptr->next!=NULL)
{
ptr=ptr->next;
}
ptr->next=new_node;
new_node->next=NULL;
}
printf("\nenter the data\n");
scanf("%d",&val);
}
return head;
}
struct node*insert_at_beg(struct node*head)
{
int val;
struct node*ptr;
ptr=(struct node*)malloc(sizeof(struct node));
printf("\nenter the data");
scanf("%d",&val);
ptr->data=val;
ptr->next=head;
head=ptr;
return head;
}
struct node*insert_at_end(struct node*node)
{int val;
struct node*ptr;
struct node*temp;
ptr=(struct node*)malloc(sizeof(struct node));
printf("\nenter the data\n");
scanf("%d",&val);
ptr->data=val;
temp=head;
while(temp->next!=NULL)
{
temp=temp->next;
}
temp->next=ptr;
ptr->next=NULL;
return head;
}
struct node*insert_before(struct node*head)
{int val;
struct node*ptr;
struct node*pre;
struct node*current;
int num;
ptr=(struct node*)malloc(sizeof(struct node));
printf("\nenter the data\n");
scanf("%d",&val);
ptr->data=val;
printf("\nenter the node before which you want to insert\n");
scanf("%d",&num);
current=head;
while(current->data !=num)
{
pre=current;
current=current->next;
}
pre->next=ptr;
ptr->next=current;
return head;
}
struct node*insert_after(struct node*head)
{
int val;
int num;
struct node*ptr;
struct node*pre;
struct node*current;
ptr=(struct node*)malloc(sizeof(struct node));
printf("\nenter the data\n");
scanf("%d",&val);
printf("\nenter the node after which you want to insert\n");
scanf("%d",&num);
ptr->data=val;
pre=head;
while(pre->data!=num)
{
pre=pre->next;
}
current=pre->next;
pre->next=ptr;
ptr->next=current;
return head;
}
struct node*display(struct node* head)
{
struct node*ptr;
ptr=head;
printf("\nthe details of link list is:\n");
while(ptr!=NULL)
{
printf("%d\n",ptr->data);
ptr=ptr->next;
}
return head;
}




