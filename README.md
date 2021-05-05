## SortAlgorithm

1. **내부정렬(Internal sort)**
   - 내부정렬은 입력의 크기가 주기억 장치의 공간보다 크지 않은 경우에 수행되는 정렬
   - **버블정렬, 선택정렬, 삽입정렬**
   - 합병정렬,  퀵정렬, 힙 정렬, **쉘 정렬**
   - 기수정렬
     - 입력이 제한된 크기 이내인 숫자로 구성되어 있을 때
2. **외부정렬(External sort)**
   - 입력의 크기가 주기억 장치의 공간보다 큰 경우
   - 보조기억 장치에 있는 입력을 여러번 나누어 주기억 장치에 읽어 들인 후, 정렬하여 보조 기억장치에 다시 저장하는 과정을 반복

---

### Internal sort - bubble, selection, insertion, shell

1. **BubbleSort**

   - 이웃하는 숫자를 비교하여 작은 수를 앞쪽으로 이동시키는 과정을 반복하여 정렬하는 알고리즘
   - 입력: 크기가 n인 배열 a,  출력: 정렬된 배열 a
   - 코드

   ```java
   public static void BubbleSort(int[] a, int n){
           int temp =0;
           if(n>0){
               for(int i =1;i<=n;i++){
                   if(a[i-1]>a[i]){ //위의 원소가 아래의 원소보다 크면
                       temp = a[i-1];
                       a[i-1] = a[i];
                       a[i] = temp;//서로 자리를 바꾼다.
                   }
               }
               BubbleSort(a,n-1);//재귀함수로 반복해준다.
           }
       }
   ```

2. **SelectionSort**

   - 입력 배열 전체에서 **최소값**을  **선택**하여 배열의 0번 원소와 자리를 바꾸고, 다음에 0번 원소를 제외한 나머지 원소에서 **최솟값**을 **선택**하여, 배열의 1번 원소와 자리를 바꾼다.
   - 위와 같은 방식을 배열의 마지막 원소까지 수행함으로써 오름차순의 정렬을 마친다.
   - 입력: 크기가 n인 배열 A, 출력: 정렬된 배열 a
   - 코드

   ```java
   public static void SelectSort(int[] a, int s){
           int temp=0;
           if(s < a.length-1){ // n = a.length, s = 시작index
               int min_index = s;// min_index -> 최솟값이 있는 원소의 index
               for(int i =s;i<a.length;i++){
                   if(a[i]<a[min_index]) 
                       min_index=i; // 최솟값을 찾은 후 그 index값을 저장
               }
               temp = a[s];
               a[s] = a[min_index];
               a[min_index] = temp;//서로 자리를 바꾼다.
               SelectSort(a,s+1);
              //재귀함수로 반복해준다. 수행한 시작index를 제외하고 다음index부터 시작.
           }
       }
   ```

3. **InsertionSort**

   - 배열을 정렬된 부분과 정렬 안된 부분으로 나누고, 정렬 안된 부분의 가장 왼쪽 원소를 정렬된 부분의 **적절한 위치**에 **삽입**하여 정렬한다. 그러한 과정을 반복한다.<img src="C:\Users\HelloGeun\AppData\Roaming\Typora\typora-user-images\image-20210505194324746.png" alt="image-20210505194324746" style="zoom:50%;" />
   - 단, 정렬은 배열의 첫 번째 원소만이 정렬된 부분에 있는 상태에서 정렬을 시작한다.
   - 입력: 크기가 n인 배열 a, 출력: 정렬된 배열 a
   - 코드

   ```java
   public static void InsertionSort(int[] a){
           for(int i =1;i<a.length;i++){ //n = a.length
   
               int key = a[i];// key -> 정렬 안된 부분의 가장 왼쪽 원소값을 저장
               int j;
   
               for(j=i-1;j>=0 && a[j]>key;j--){ 
                  //정렬된 부분의 가장 오른쪽 원소로부터 왼쪽 방향으로 삽입할 곳을 탐색
                   a[j+1] = a[j]; // 자리 이동을 해준다.
               }
               a[j+1] = key; //적절한 위치에 삽입한다.
           }
       }
   ```

   <img src="C:\Users\HelloGeun\AppData\Roaming\Typora\typora-user-images\image-20210505195608975.png" alt="image-20210505195608975" style="zoom:60%;" />

4. **ShellSort**

   - 앞에 소개한 정렬들의 단점을 보완하기 위함이다.
   - 배열 뒷부분의 작은 숫자를 앞부분으로 **빠르게** 이동시키고, 동시에 앞부분의 큰 숫자는 뒷부분으로 이동시고, 가장 마지막에는 삽입정렬을 수행하는 알고리즘이다.
   - 입력: 크기가 n인 배열 a, 출력: 정렬된 배열 a
   - 코드

   ```java
   public static void ShellSort(int[] a, int num){//num = a.length
           for(int gap = num/2; gap>0;gap= gap/2){ //간격을 반으로 계속 줄인다.
               for(int i = gap ; i<num;i++){
                   int value = a[i]; // 현재값을 value에 저장한다.
                   int j;
                   for(j=i-gap;j>=0 && value < a[j];j=j-gap){
                       a[j+gap] = a[j];
                 // gap만큼 떨어진 index값들과 비교 후 value보다 작으면 서로 바꾼다.
                   }
                   a[j+gap] = value;
               }
           }
       }
   ```

#### 전체코드

```java
public interface Input {
     int[] Descending();//내림차순
     int[] random();//랜덤
     int[] Almost();//거의 정렬된
}
```

- 공통된 부분을 interface로 만들어 사용해준다.

```java
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
```

- interface를 class에 부여해준다.

1. **BubbleSort Class**

```java
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
```

2. **SelectionSort Class**

```java
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
```

3. **InsertionSort Class**

```java
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

```

4. **ShellSort Class**

```java
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
```

### 성능 분석

- n의 값을 계속 늘려면 측정된 시간을 그래프를 나타낸것이다.
- ms1은 내림차순, ms2는 랜덤, ms3은 거의 정렬된 배열이다.

![image-20210505203450184](C:\Users\HelloGeun\AppData\Roaming\Typora\typora-user-images\image-20210505203450184.png)

- 램덤으로 한 경우가 나머지 경우보다 더 시간이 오래걸린다.

![image-20210505203504714](C:\Users\HelloGeun\AppData\Roaming\Typora\typora-user-images\image-20210505203504714.png)

- 3가지 모두 비슷하게 나온다.

![image-20210505203514583](C:\Users\HelloGeun\AppData\Roaming\Typora\typora-user-images\image-20210505203514583.png)

- 버블정렬과 선택정렬과 달리 n값을 더 크게 하였다. n의 값이 작으면 시간이 0ms로 나오기 때문에 시간 측정을 할 수 없다.
- 거의 정렬된 배열에 경우 버블정렬과 선택정렬보다 엄청 빠르다는 것을 알 수 있다.

![image-20210505203524429](C:\Users\HelloGeun\AppData\Roaming\Typora\typora-user-images\image-20210505203524429.png)

- 삽입정렬과 같은 n값들을 사용하여 측정하였다.  n의 값이 작으면 시간이 0ms로 나오기 때문에 시간 측정을 할 수 없다.

- 삽입정렬의 램덤과 내림차순일 경우 n의 값이 커질수록 시간측정이 굉장히 오래 걸렸지만 shell정렬은 그보다 훨씬 빠르게 측정되었다. (세로축)

  