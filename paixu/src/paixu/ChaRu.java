package paixu;
/**
 * 插入排序
 * 	工作原理是通过构建有序序列，对于未排序数据，
 * 	在已排序序列中从后向前扫描，找到相应位置并插入。
 * 
 *	从第一个元素开始，该元素可以认为已经被排序； 
 *	取出下一个元素，在已经排序的元素序列中从后向前扫描；
 *	如果该元素（已排序）大于新元素，将该元素移到下一位置；
 *	重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 *	将新元素插入到该位置后；
 *	重复步骤2~5。
 *  
 *  时间复杂度O(n²)平均	O(n)~O(n²) 
 *  空间复杂度O(1)
 *  稳定
 */
public class ChaRu {
	public static int[] paixu(int[] a) {
		//第一层循环遍历a.length-1次，（循环次数）
		for(int i=1;i<a.length;i++) {
			//第二层循环，比较a.length-i-1次，大的往后，小的往前
			int minIndex = i;
			for(int j=0;j<i;j++) {
				if(a[i] > a[j]) {
					minIndex = j;
				}
			}
			int temp = a[minIndex];
			a[minIndex] = a[i];
			a[i] = temp;
		}
		return a;
	}
}
