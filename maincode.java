public interface Input {
     int[] Descending();//내림차순
     int[] random();//랜덤
     int[] Almost();//거의 정렬된
}
import java.util.Arrays;
import java.util.Random;

public class InputDummy implements Input{
    @Override
    public int[] Descending() {
        int[] elements = random();
        int temp;
        Arrays.sort(elements);
        for(int i =0;i< (elements.length)/2;i++){
            temp = elements[i];
            elements[i] = elements[(elements.length-1)-i];
            elements[(elements.length-1)-i] = temp;
        }
        return elements;
    }

    @Override
    public  int[] random() {
        Random random = new Random();
        int n = 1000000;
        int[] elements = new int[n];
        for(int i=0; i<n;i++){
            elements[i] = random.nextInt(10000);
        }
        return elements;
    }

    @Override
    public int[] Almost() {
        int[] elements = random();
        int temp;
        Arrays.sort(elements);
        for(int i =10; i< elements.length;i=i+10){
            temp = elements[0];
            elements[0] = elements[i];
            elements[i]= temp;
        }
        return elements;
    }
}
public class BubbleSortAlgorithm{
    public static void BubbleSort(int[] a, int n){
        int temp =0;
        if(n>0){
            for(int i =1;i<=n;i++){
                if(a[i-1]>a[i]){
                    temp = a[i-1];
                    a[i-1] = a[i];
                    a[i] = temp;
                }
            }
            BubbleSort(a,n-1);
        }
    }
    public static void ArrayPrint(int[] a){
        for(int i =0; i<a.length;i++){
            if(i%10==0){
                System.out.println();
            }
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] arg){
        Input elements = new InputDummy();
        
        int[] result = elements.Descending();     
        BubbleSort(result,result.length-1);
        ArrayPrint(result);

        int[] result2 = elements.random();       
        BubbleSort(result2, result2.length-1);      
        ArrayPrint(result2);
        

        int[] result3 = elements.Almost();
        BubbleSort(result3, result3.length-1);       
        ArrayPrint(result3);        
    }
}
public class SelectSortAlgorithm {
    public static void SelectSort(int[] a, int s){
        int temp=0;
        if(s < a.length-1){
            int min_index = s;
            for(int i =s;i<a.length;i++){
                if(a[i]<a[min_index]) min_index=i;
            }
            temp = a[s];
            a[s] = a[min_index];
            a[min_index] = temp;
            SelectSort(a,s+1);
        }
    }
    public static void ArrayPrint(int[] a){
        for(int i =0; i<a.length;i++){
            if(i%10==0){
                System.out.println();
            }
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] arg){
        Input elements = new InputDummy();
        int[] result = elements.Descending();
        SelectSort(result,0);
        ArrayPrint(result);

        int[] result2 = elements.random();
        SelectSort(result2,0);
        ArrayPrint(result2)

        int[] result3 = elements.Almost();
        SelectSort(result3,0);
        ArrayPrint(result3);
    }
}
public class InsertionSortAlgorithm {
    public static void InsertionSort(int[] a){
        for(int i =1;i<a.length;i++){

            int key = a[i];
            int j;

            for(j=i-1;j>=0 && a[j]>key;j--){
                a[j+1] = a[j];
            }
            a[j+1] = key;
        }
    }
    public static void ArrayPrint(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] arg){
        Input elements = new InputDummy();
        int[] result1 = elements.Descending();
        InsertionSort(result1);
        ArrayPrint(result1);

        int[] result2 = elements.random();
        InsertionSort(result2);
        ArrayPrint(result2);

        int[] result3 = elements.Almost();
        InsertionSort(result3);
        ArrayPrint(result3);
    }
}
public class ShellSortAlgorithm {
    public static void ShellSort(int[] a, int num){
        for(int gap = num/2; gap>0;gap= gap/2){
            for(int i = gap ; i<num;i++){
                int value = a[i];
                int j;
                for(j=i-gap;j>=0 && value < a[j];j=j-gap){
                    a[j+gap] = a[j];
                }
                a[j+gap] = value;
            }
        }
    }
    public static void ArrayPrint(int[] a){
        for(int i =0; i<a.length;i++){
            if(i%10==0){
                System.out.println();
            }
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] arg){
        Input elements = new InputDummy();
        
        int[] result = elements.Descending();
        ShellSort(result, result.length);
        ArrayPrint(result);

        int[] result2 = elements.random();
        ShellSort(result2, result2.length);
        ArrayPrint(result2);

        int[] result3 = elements.Almost();
        ShellSort(result3, result3.length);
        ArrayPrint(result3);
    }
}