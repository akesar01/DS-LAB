import java.awt.dnd.DropTarget;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AvlTree {
    private static FastScanner fs = new FastScanner();

    public static void main(String[] args) {
        Node root = null;

        root = insert(root, 9);
        root = insert(root, 5);
        root = insert(root, 10);
        root = insert(root, 0);
        root = insert(root, 6);
        root = insert(root, 11);
        root = insert(root, -1);
        root = insert(root, 1);
        root = insert(root, 2);

        /* The constructed AVL Tree would be
        9
        / \
        1 10
        / \ \
        0 5 11
        / / \
        -1 2 6
        */
        System.out.println("Preorder traversal of " +
                "constructed tree is : ");
        preOrder(root);
        System.out.println("This is root after preorder " + root.data);

        root = delete(10,root);
        System.out.println("Preorder traversal of " +
                "constructed tree is : ");
        preOrder(root);
        System.out.println("This is root after preorder " + root.data);

    }

    static class Node {
        int data;
        Node left, right;
        int height;

        public Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    public static Node insert(Node head, int data) {
        if (head == null) return new Node(data);
        if (data < head.data) {
            head.left = insert(head.left, data);
        } else if (data > head.data) {
            head.right = insert(head.right, data);
        } else {
            return head;
        }
        head.height = 1 + Math.max(height(head.left), height(head.right));
        int res = getBalance(head);
        /*

        res >1
         1
        /  \
           2
          /  \
         4    5

        */

        if (res > 1 && head.left.data > data) {
            return rotateRight(head);
        }
        if (res > 1 && head.left.data < data) {

            head.left = rotateLeft(head.left);
            return rotateRight(head);

        }
        if (res < -1 && head.right.data < data) {
            return rotateLeft(head);
        }
        if (res < -1 && head.right.data > data) {
            head.right = rotateRight(head.right);
            return rotateLeft(head);
        }
        return head;

    }

    private static Node rotateLeft(Node n) {
        Node y = n.right;
        Node sub = y.left;

        y.left = n;
        n.right = sub;

        n.height = Math.max(height(n.left), height(n.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;

    }

    private static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;

    }

    private static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    private static int height(Node n) {
        if (n == null) return 0;
        return n.height;
    }

    private static int getBalance(Node n) {
        if (n == null) return 0;
        return (height(n.left) - height(n.right));
    }

    private static Node delete(int key,Node head) {

        if(head == null) return null;
        if(key > head.data ) head.right =delete(key,head.right);
        else if( (key < head.data)) head.left =delete(key,head.left);
        else {
            if (head.left == null && head.right == null) {
                head = null;
            } else if (head.left == null || head.right == null) {
                Node temp = null;
                if (head.left == null) temp = head.right;
                else temp = head.left;
                head = temp;

            } else {
                Node temp = maxLeft(head.left);
                head.data = temp.data;
                head.left = delete(temp.data, head.left);

            }
        }
        if(head == null) return head;
        head.height = Math.max(height(head.left),height(head.right)) +1;
        int balance = getBalance(head);
        if(balance >1 && getBalance(head.left) >=0)
        {
            return rotateRight(head);
        }
        if(balance >1 && getBalance(head.left)<0)
        {
            head.left = rotateLeft(head.left);
            return rotateRight(head);
        }


        if(balance < -1 && getBalance(head.left) <=0)
        {
            return rotateLeft(head);
        }
        if(balance < -1 && getBalance(head.left)>0)
        {
            head.right = rotateRight(head.left);
            return rotateLeft(head);
        }
        return head;




    }
    private static Node maxLeft(Node node)
    {
        Node n = node;
        if(n == null) return n;

        while(n.right != null)
        {
            n = n.right;
        }
        return n;

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