
public class QuickSort {
    public static void main(String[] args) {

        int[] a1 = { 1, 23, 45, 2, 8, 134, 9, 4, 2000 };
        int[] a2 = {23,44,1,2009,2,88,123,7,999,1040,88};
        quickSort(a1, 0, a1.length - 1);
        for( int i = 0; i < a1.length; i++ )
        	System.out.print(a1[i] + " ");
        
        System.out.println();
        
        quickSort(a2, 0, a2.length - 1);
        for( int i = 0; i < a2.length; i++ )
        	System.out.print(a2[i] + " ");
    }

    public static void quickSort(int[] a, int l, int r) {
        if( l < r ) {
            int i = partition(a,l,r);
            quickSort(a, l, i);
            quickSort(a, i + 1, r);
        }
    }

    private static int partition(int[] a, int p, int r) {

        int x = a[p];
        int i = p - 1;
        int j = r + 1;

        while( true ) {
            i++;
            while( i< r && a[i] < x)
                i++;
            j--;
            while( j > p && a[j] > x)
                j--;

            if (i < j)
                swap(a, i, j);
            else
                return j;
        }
    }

    private static void swap(int[] a, int i, int j) {
        // TODO Auto-generated method stub
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
