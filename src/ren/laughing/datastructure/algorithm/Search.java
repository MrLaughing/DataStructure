package ren.laughing.datastructure.algorithm;

/**
 * 
 * @author Laughing_Lz
 * @time 2016年4月19日
 */
public class Search {
	/**
	 * 二分查找/折半查找（此处使用增序数组）
	 * 
	 * @param s
	 *            增序的数组
	 * @param low
	 *            最小元素下标
	 * @param high
	 *            最大元素下标
	 * @param key
	 *            要查找元素
	 */
	public int binSearch(int[] s, int low, int high, int key) {
		// 递归查询
		if (low > high) {
			return -1;
		}
		int middle = (low + high) / 2;
		if (key > s[middle]) {
			middle++;
			return binSearch(s, middle, high, key);
		} else if (key < s[middle]) {
			middle--;
			return binSearch(s, low, middle, key);
		} else {
			return middle;
		}
	}

	public static void main(String[] args) {
		Search search = new Search();
		int[] s = new int[] { 2, 11, 22, 33, 44, 55, 66 };
		int result = search.binSearch(s, 0, 6, 42);
		System.out.println(result);
	}
}
