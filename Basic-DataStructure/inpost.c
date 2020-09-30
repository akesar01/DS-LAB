#include<stdio.h>
#include<string.h>
#include<ctype.h>
#include<stdlib.h>
int stack[100];
int top=-1;
void in2post(char[]);
int pop();
int prec(char);
void push(char);
void main()
{
char in[100],post[100];
printf("Enter the expresion\n");
scanf("%s",in);
in2post(in);
}
void in2post (char in_exp[])
{
int x=0;
int y=0;
char t;
char c;
char post_fix[100];
t =in_exp[x];
push('\0');
while(t!='\0'){
if (isalnum(t))
{
post_fix[y]=t;
y++;
}
else if (t=='(')
{
push(t);
}
else if (t==')')
{
while(stack[top]!='(')
{
c=pop();
post_fix[y]=c;
y++;
}
c=pop();
}

else {
while(prec(stack[top])>=prec(t))
{
c=pop();
post_fix[y]=c;
y++;
}
push(t);
}
x++;
t=in_exp[x];
}
while(top!=-1)
{
c=pop();
post_fix[y]=c;
y++;
}
printf("the post fix expression is ");
puts(post_fix);
}
int pop()
{
return(stack[top--]);
}
void push(char a )
{
stack[++top]=a;
}
int prec(char t)
{
switch(t)
{
case '+':{
return (7);
break;
}
case '-':{
return (7);
break;}
case '*':{
return (8);
break;
}
case '/':{
return (8);
break;
}
case '\0':{
return(0);
break;
}
return 0;
}
}
