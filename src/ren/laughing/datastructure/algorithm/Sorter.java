package ren.laughing.datastructure.algorithm;

import ren.laughing.datastructure.baseImpl.BinTreeNode;

/**
 * 排序
 * 
 * @author Laughing_Lz
 * @time 2016年4月22日
 */
public class Sorter {
	/**
	 * 直接插入排序(从小到大) 时间复杂度为O(n^2)
	 * 
	 * @param arr
	 *            要排序的数组
	 * @param low
	 *            要排序的最低下标
	 * @param high
	 *            要排序的最高下标
	 * @return 排序后的数组
	 */
	public static void insertSort(int[] arr, int low, int high) {
		for (int i = low + 1; i <= high; i++) {
			int temp = arr[i];// 待插入元素
			int j = i;// 记录待插入位置
			for (; j > low && temp < arr[j - 1]; j--) {//
				arr[j] = arr[j - 1];// 后移
			}
			arr[j] = temp;// 插入
		}
		printResult("直接插入排序：", arr);
	}

	/**
	 * 折半插入排序 时间复杂度仍为O(n^2)
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public static void binInsertSort(int[] arr, int low, int high) {
		for (int i = low + 1; i <= high; i++) {
			int temp = arr[i];
			int lo = low;
			int hi = i - 1;
			while (lo <= hi) {// 注意'=' ★
				int mid = (lo + hi) / 2;
				if (temp < arr[mid]) {
					hi = mid - 1;// 由此看出最终temp的位置在hi+1
				} else {
					lo = mid + 1;// hi+1等同于lo (此处应该没错吧？)
				}
			}
			for (int j = i - 1; j > hi; j--) {// 后移
				arr[j + 1] = arr[j];
			}
			arr[hi + 1] = temp;// 插入
		}
		printResult("折半插入排序：", arr);
	}

	/**
	 * 希尔排序（缩小增量排序） 当n在某个范围内，时间复杂度可达到O(n^1.3)
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @param delta
	 *            步长序列
	 */
	public static void shellSort(int[] arr, int low, int high, int[] delta) {
		for (int i = 0; i < delta.length; i++) {// 第一步：遍历步长序列
			for (int m = low; m < low + delta[i]; m++) {// 第二步：循环起始点，保证每个被拆分的子序列都被直接排序
				for (int j = m + delta[i]; j <= high; j += delta[i]) {// 对每个子序列直接排序
					int temp = arr[j];
					int k = j;
					for (; k > m && temp < arr[(k - delta[i])]; k -= delta[i]) {
						arr[k] = arr[(k - delta[i])];// 后移
					}
					arr[k] = temp;// 插入
				}
			}
		}
		printResult("希尔排序：", arr);
	}

	/**
	 * 冒泡排序 时间复杂度为O(n^2)
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void bubbleSort(int[] arr, int low, int high) {
		int len = high - low + 1;
		for (int i = 1; i < len; i++) {
			for (int j = low; j <= high - i; j++) {// 这里j<= high-i ★
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];// 交换
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		printResult("冒泡排序：", arr);
	}

	/**
	 * 快速排序 需要递归
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pa = partition(arr, low, high);// 分治
			quickSort(arr, low, pa - 1);
			quickSort(arr, pa + 1, high);
		}
	}

	/**
	 * 将序列划分为两个子序列并返回枢轴元素的位置
	 * 
	 * @param arr
	 * @param low
	 *            划分区间
	 * @param high
	 * @return
	 */
	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[low];// 首先定义枢轴为low所指元素
		while (low < high) {// 交替扫描
			while (arr[high] > pivot && low < high) {
				high--;
			}
			arr[low] = arr[high];// 将比 pivot 小的元素移向低端
			while (arr[low] < pivot && low < high) {
				low++;
			}
			arr[high] = arr[low];// 将比 pivot 大的元素移向高端
		}
		arr[low] = pivot;// 设置枢轴
		return low;
	}

	/**
	 * 简单选择排序 时间复杂度为O(n^2)
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void selectSort(int[] arr, int low, int high) {
		for (int i = low; i < high - 1; i++) {
			int min = i;// 记录位置,不是元素是位置！★
			for (int j = i; j <= high; j++) {
				if (arr[j] < arr[min]) {
					min = j;// 更新最小元素位置
				}
			}
			if (min != i) {// 交换
				int temp = arr[min];
				arr[min] = arr[i];
				arr[i] = temp;
			}
		}
		printResult("简单选择排序：", arr);
	}

	/**
	 * 堆排序，时间复杂度为O(nlogn) 堆排序中，arr[]数组的首位arr[0]弃用。所以这里arr[1]为根结点
	 * 
	 * @param arr
	 */
	public static void heapSelectSort(int[] arr) {
		int n = arr.length - 1;
		for (int i = n / 2; i >= 1; i--) {// 遍历叶子结点的上层第一个结点及之前所有结点,i>=1是因为数组中首位元素弃用
			heapAdjust(arr, i, n);// 初始化建堆,i=n/2位置
		}
		for (int i = n; i > 1; i--) {//
			int temp = arr[1];// 堆顶元素（根节点）为最大元素，将它和堆底元素交换
			arr[1] = arr[i];
			arr[i] = temp;
			heapAdjust(arr, 1, i - 1);// 从根结点更新堆
		}
		printResult("堆排序：", arr);
	}

	/**
	 * 创建堆
	 * 
	 * @param r
	 *            数组元素根据二叉树层次遍历顺序存储
	 * @param low
	 *            遍历是否比左右孩子大的起始结点
	 * @param high
	 *            数组长度
	 */
	private static void heapAdjust(int[] r, int low, int high) {
		int temp = r[low];// r[low]为要与左右孩子比较的父结点，以保证其比左右孩子都大
		for (int j = 2 * low; j <= high; j = j * 2) {// 需要循环保证该起始结点low以下的树都符合堆定义★
			if (j < high && r[j] < r[j + 1]) {// r[j]是r[low]的左孩子，r[j+1]是右孩子
				j++;
			}
			if (temp > r[j]) {// 父结点与较大的孩子比较大小，若父结点小，交换
				break;
			}
			r[low] = r[j];// 父结点存放的是三者中最大的元素
			low = j;
		}
		r[low] = temp;// 将原父结点的元素存入孩子结点
	}

	/**
	 * 归并排序
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void mergeSort(int[] arr, int low, int high) {
		if (low < high) {// 分治
			mergeSort(arr, low, (low + high) / 2);
			mergeSort(arr, (low + high) / 2 + 1, high);
			merge(arr, low, (low + high) / 2, high);
		}
	}

	/**
	 * 将两个有序区间[low,mid]和[mid+1,high]合并成一个有序区间
	 * 
	 * @param arr
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] arr, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int a = low;
		int b = mid + 1;
		int t = 0;
		while (a <= mid && b <= high) {// 注意这个循环★
			if (arr[a] < arr[b]) {
				temp[t++] = arr[a++];
			} else {
				temp[t++] = arr[b++];
			}
		}
		while (a <= mid) {
			temp[t++] = arr[a++];
		}
		while (b <= high) {
			temp[t++] = arr[b++];
		}
		for (int i = 0; i < temp.length; i++) {
			arr[low + i] = temp[i];
		}
	}

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[] { 2, 5, 7, 3, 4, 8, 1, 9, 6, 0 };
		int[] delta = new int[] { 5, 3, 1 };
		// insertSort(arr, 0, 9);
		// binInsertSort(arr, 0, 9);
		// shellSort(arr, 0, 9, delta);
		// bubbleSort(arr, 0, 9);
		// quickSort(arr, 0, 9);
		// printResult("快速排序：", arr);
		// selectSort(arr, 0, 9);
		// heapSelectSort(arr);
		mergeSort(arr, 0, 9);
		printResult("归并排序：", arr);
	}

	/**
	 * 打印
	 * 
	 * @param str
	 * @param arr
	 */
	public static void printResult(String str, int[] arr) {
		System.out.print(str);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
