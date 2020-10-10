package javabase.排序;

public class 排序 {

    public static void main(String[] args) {
        int[] a = {9, 8, 7, 3, 4, 5, 6, 2, 1, 0};
        // 数组排序 从小到大
        int[] temp = new int[20];
//        bubble_sort(a, 10);
//        selection_sort(a, 10);
//        insertion_sort(a, 10);
//        shell_sort(a,10);
//        merge_sort(a,0,9,temp);
        bubble_sort2(a, 10);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }

    /**
     * 1. 冒泡排序
     * @param a 数组
     * @param n 整型
     */
    static void bubble_sort(int[] a, int n) {
        int i, j, lastSwap, tmp;
        for (j = n - 1; j > 0; j = lastSwap) {
            //每一轮要初始化为0，防止某一轮未发生交换，lastSwap保留上一轮的值进入死循环
            lastSwap = 0;
            for (i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    //最后一次交换位置的坐标
                    lastSwap = i;
                }
            }
        }
    }

    static void bubble_sort2(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    a[j] = a[j] + a[j+1];
                    a[j+1] = a[j] - a[j+1];
                    a[j] = a[j] - a[j+1];
                }
            }
        }
    }

    /**
     * 2. 选择排序
     * @param a 数组
     * @param n 整型
     */
    static void selection_sort(int[] a, int n) {
        int i, j, pos, tmp;
        for (i = 0; i < n - 1; i++) {
            //寻找最小值的下标
            for (pos = i, j = i + 1; j < n; j++) {
                if (a[pos] > a[j]) {
                    pos = j;
                }
            }
            if (pos != i) {
                tmp = a[i];
                a[i] = a[pos];
                a[pos] = tmp;
            }
        }
    }

    /**
     * 3. 插入排序
     * @param a 数组
     * @param n 整型
     */
    static void insertion_sort(int[] a, int n) {
        int i, j, v;
        for (i = 1; i < n; i++) {
            //如果第i个元素小于第j个，则第j个向后移动
            for (v = a[i], j = i - 1; j >= 0 && v < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = v;
        }
    }

    /**
     * 4. 希尔排序
     * @param a 数组
     * @param n 整型
     */
    static void shell_sort(int a[], int n) {
        int d, i, j, temp; //d为增量
        //增量递减到1使完成排序
        for (d = n / 2; d >= 1; d = d / 2)
        {
            //插入排序的一轮
            for (i = d; i < n; i++)
            {
                temp = a[i];
                for (j = i - d; (j >= 0) && (a[j] > temp); j = j - d) {
                    a[j + d] = a[j];
                }
                a[j + d] = temp;
            }
        }
    }

    static void mergearray(int a[], int first, int mid, int last, int temp[]) {
        int i = first, j = mid + 1;
        int m = mid, n = last;
        int k = 0;

        while (i <= m && j <= n) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= m) {
            temp[k++] = a[i++];
        }

        while (j <= n) {
            temp[k++] = a[j++];
        }

        for (i = 0; i < k; i++) {
            a[first + i] = temp[i];
        }
    }

    /**
     * 5. 归并排序
     * @param a
     * @param first
     * @param last
     * @param temp
     */
    static void merge_sort(int a[], int first, int last, int temp[]) {
        if (first < last) {
            int mid = (first + last) / 2;
            //左边有序
            merge_sort(a, first, mid, temp);
            //右边有序
            merge_sort(a, mid + 1, last, temp);
            //再将二个有序数列合并
            mergearray(a, first, mid, last, temp);
        }

    }
}
