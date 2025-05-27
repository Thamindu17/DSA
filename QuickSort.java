import java.util.Arrays;


public class QuickSort {



 // Print the array
static void printArray(int arr[]) {
    int n = arr.length;
    for (int i = 0; i < n; ++i)
           System.out.print(arr[i] + " ");
         System.out.println();
       }
    

private int partition(int[] arr, int low, int high) {
 // Choose the first element as the pivot
 int pivot = arr[low];
 int left = low + 1;
 int right = high;
 while (left <= right) {
            // Move left pointer to the right until it finds an
            // element greater than pivot
            while (left <= right && arr[left] <= pivot) {
                            left++;
                        }
            // Move right pointer to the left until it finds an
            // element smaller than pivot
            while (left <= right && arr[right] > pivot) {
                            right--;
                        }
            // Swap elements at left and right pointers if they
            // haven't crossed each other
            if (left < right) {
                            swap(arr, left, right);
                        }
        }


        
    // Swap pivot with the element at the right pointer
    swap(arr, low, right);
    return right;
    }


private static void swap(int[] arr, int i, int j) {
 int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


 private void quickSort(int[] arr, int low, int high) {
 if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

 //main
    public static void main(String[] args) {
 int[] arr = {5, 2, 9, 1, 5, 6};
        printArray(arr);
 QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }
 }