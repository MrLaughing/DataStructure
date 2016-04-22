package ren.laughing.datastructure.algorithm;

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
		quickSort(arr, 0, 9);
		printResult("快速排序：", arr);
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
