import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C{
    private static FastScanner fs = new FastScanner();
    int MaxLvL;
    double p;
    Node header;
    int level;
    public C(int maxLvL, double p) {
        this.MaxLvL = maxLvL;
        this.p = p;
        this.header = createNode(maxLvL,-1);
        this.level =0;
    }
    public Node createNode(int lvl,int key)
    {
        Node n =new  Node(key,lvl);
        return n;
    }
    private int randomLevel()
    {
        int lvl =0;
        while(Math.random()<p && lvl<this.MaxLvL)
        {
            lvl++;
        }
        return lvl;
    }
    private  void insertElement(int key)
    {
        Node update []= new Node[MaxLvL+1];

        Node current = this.header;
        for(int i=level;i>=0;i--)
        {
            while(current.forward[i] != null && current.forward[i].key <key)
            {
                current = current.forward[i];
            }
            update[i] = current;

        }
        current = current.forward[0];
        if(current==null || current.key != key)
        {
            int rLevel = randomLevel();
            if(rLevel>level)
            {
                for(int i = this.level+1;i<=rLevel;i++)
                {
                    update[i] = this.header;
                }
                this.level = rLevel;
            }
            Node n = this.createNode(rLevel,key);
            for(int i=0;i<=rLevel;i++)
            {
                n.forward[i] = update[i].forward[i];
                update[i].forward[i] = n;
            }
            System.out.println("Successfully Inserted the key "+key);
        }


    }
    private void displayList()
    {
        System.out.println("The skip List is");
        Node head = this.header;
        for(int i=0;i<=this.level;i++)
        {
            System.out.print("Level "+i +": ");
            Node node = head.forward[i];
            while(node != null)
            {
                System.out.print(node.key+" ");
                node = node.forward[i];
            }
            System.out.println();
        }
    }
    private void deleteElement(int key)
    {
       Node[] update = new Node[MaxLvL+1];
       Node current = this.header;
       for(int i=this.level;i>=0;i--)
       {
           while(current.forward[i] != null && current.forward[i].key<key)
           {
               current = current.forward[i];
           }
           update[i] = current;
       }
       current = current.forward[0];

       if(current != null && current.key == key)
       {
           for(int i=0;i<=this.level;i++)
           {
               if(update[i].forward[i] != current)
               {
                   break;
               }
               update[i].forward[i] = current.forward[i];
           }
           while(this.level>0 && this.header.forward[this.level] ==null)
           {
               this.level -=1;
           }
           System.out.println("Successfully Deleted The "+key);


       }
    }
    private void searchElement(int key)
    {
        Node current = this.header;
        for(int i=this.level;i>=0;i--)
        {
            while(current.forward[i] != null && current.forward[i].key<key)
            {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        if(current != null && current.key==key)
            System.out.println("The Key "+key+" found");
        else
        {
            System.out.println("The key "+key+" not Found");
        }

    }






    public static void main(String[] args) throws InterruptedException{
        System.out.println("Enter the level in the List");
        int lvl = fs.nextInt();
        C c = new C(lvl,0.5);
        while(true) {
            System.out.print("Enter: \n1)Add The element \n2) Stop! \n3) Display\n4)Delete Element\n5)Search Element\n");
            int n = fs.nextInt();
            if(n==1) {
                System.out.println("Enter the Element to be Inserted");
                int temp = fs.nextInt();
                c.insertElement(temp);
            }
            else if(n==2) {
                System.out.println("Exiting the Loop....");
                break;
            }
            else if(n==3)
            {
                System.out.println("Displaying...");
                c.displayList();
            }
            else if(n==4)
            {
                System.out.println("Enter the element to be deleted");
                int temp   = fs.nextInt();
                System.out.println("Deleting...");
                Thread.sleep(1000);
                c.deleteElement(temp);
            }
            else if(n==5)
            {
                System.out.println("Enter the element to be searched");

                int temp = fs.nextInt();
                System.out.println("Searching....");
                Thread.sleep(2000);
                c.searchElement(temp);

            }
            else System.out.println("Check Your Input");
        }

    }
    static class Node
    {
        int key;
        Node forward[];

        public Node(int key, int level) {
            this.key = key;
            this.forward = new Node[level+1];
        }
    }



    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
        int  gcd(int a,int b)
        {
            while (b > 0)
            {
                int  temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
        private int upper(int arr[],int key){
            int low = 0;
            int high = arr.length-1;
            while(low < high){
                int mid = low + (high - low+1)/2;
                if(arr[mid] <= key){
                    low = mid;
                }
                else{
                    high = mid-1;
                }
            }
            return low;
        }
        public int lower(int arr[],int key){
            int low = 0;
            int high = arr.length-1;
            while(low < high){
                int mid = low + (high - low)/2;
                if(arr[mid] >= key){
                    high = mid;
                }
                else{
                    low = mid+1;
                }
            }
            return low;
        }
    }
}