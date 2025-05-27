public class QS {
    
    int partition(int arr[],int low,int high){
      int pivot =arr[low];
      int left = low+1;
      int right= high;

      while (left<= right) {

        while (left<=right && arr[left]<=pivot ) {
            left++;
        }

        while (left<=right && arr[right] >pivot) {
            right--;
        }
        if(left < right){
            swap(arr,left,right);
        }
        
      }

      swap(arr, low, right);
      return right;
    }

    void swap(int arr[],int i,int j){
        int temp= arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


    void QuickSort(int arr[],int low,int high){
        if(low<high){
            int pivotIndex= partition( arr, low, high);
            QuickSort(arr,low,pivotIndex-1);
            QuickSort(arr,pivotIndex+1,high);
            

        }
    }

    static void print(int arr[]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            System.out.print(arr[i]  + " ");


        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[]={55,69,25,15,5,47};
        int n=arr.length -1;

        QS qs= new QS();
        qs.QuickSort(arr,0,n);

        print(arr);

    }
}
