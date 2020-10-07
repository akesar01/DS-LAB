import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class isLand{
    private  static int rows=0;
    private  static int column =0;
    private static FastScanner fs = new FastScanner();
    public static void main(String[] args) {

        System.out.println("Enter the no of rows and Column");
         rows = fs.nextInt();column = fs.nextInt();
        System.out.println("Enter the 2D  Array");
        int arr[][] = new int[rows][column];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<column;j++)
            {
                arr[i][j] = fs.nextInt();
            }
        }
        DisjointSet ds = new DisjointSet();
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<column;j++)
            {
                ds.makeSet(i*column+j);
            }
        }
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<column;j++)
            {

                if(arr[i][j] ==1)
                {
//                    System.out.println("Hello");
                   if(check(i+1,j) && arr[i+1][j]==1) ds.union((i)*column+j,(i+1)*column+j);
                    if(check(i-1,j) && arr[i-1][j]==1) ds.union((i)*column+j,(i-1)*column+j);
                    if(check(i,j+1) && arr[i][j+1]==1) ds.union((i)*column+j,(i)*column+j+1);
                    if(check(i,j-1) && arr[i][j-1]==1) ds.union((i)*column+j,(i)*column+j-1);
                    if(check(i+1,j+1) && arr[i+1][j+1]==1) ds.union((i)*column+j,(i+1)*column+j+1);
                    if(check(i+1,j-1) && arr[i+1][j-1]==1) ds.union((i)*column+j,(i+1)*column+j-1);
                    if(check(i-1,j+1) && arr[i-1][j+1]==1) ds.union((i)*column+j,(i-1)*column+j+1);
                    if(check(i-1,j-1) && arr[i-1][j-1]==1) ds.union((i)*column+j,(i-1)*column+j-1);

                }
            }
        }
        boolean isAgain[] = new boolean[rows*column+column];
        Arrays.fill(isAgain,false);

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<column;j++) {
                if (arr[i][j] == 1) {
                     int a =(int) ds.findSet(i*column+j);
                    if(isAgain[a]==false)
                    {
                        res++;
                        isAgain[a] = true;
                    }
                }
            }
        }
        System.out.println(res);


    }
    private static boolean check(int i,int j)
    {
        return (i<rows && i>=0) && (j<column && j>=0);

    }

    private static long res =0;



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
/**
 * @author Ankit
 * Date 08/20/2020
 *
 *
 *
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 
 */
class DisjointSet {

    private Map<Long, Node> map = new HashMap<>();

    class Node {
        long data;
        Node parent;
        int rank;
    }

    /**
     * Create a set with only one element.
     */
    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(long data1, long data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     *
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }
}
