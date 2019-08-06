package paixu;
/**
 * 冒泡排序
 * 	比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 *  对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 *  针对所有的元素重复以上的步骤，除了最后一个；
 *  重复步骤1~3，直到排序完成。
 *  
 *  时间复杂度O(n²)平均	O(n)~O(n²)
 *  空间复杂度O(1)
 *  稳定
 */
public class MaoPao {
	public static int[] paixu(int[] a) {
		//第一层循环遍历a.length-1次，（循环次数）
		for(int i=0;i<a.length-1;i++) {
			//第二层循环，比较a.length-i-1次，大的往后，小的往前
			for(int j=0;j<a.length-i-1;j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
				}
			}
			//第一圈，比较a.length-1次，查出最大的值
			//第二圈，比较a.length-2次，查出第二大的值。。。。。。循环到最后
		}
		return a;
	}
}
