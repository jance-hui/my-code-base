package paixu;
/**
 * 选择排序
 * 
 *	工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 *	然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *	以此类推，直到所有元素均排序完毕。 
 *
 *	初始状态：无序区为R[1..n]，有序区为空；
 *	第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
 *	该趟排序从当前无序区中-选出关键字最小的记录 R[k]， 将它与无序区的第1个记录R交换，
 *	使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 *	n-1趟结束，数组有序化了。
 * 
 *  时间复杂度O(n²)平均	O(n²)~O(n²)
 *  空间复杂度O(1)
 *  不稳定
 */
public class XuanZe {
	public static int[] paixu(int[] a) {
		//第一层循环a.length-1次
		for(int i=0;i<a.length-1;i++) {
			//将第一个值假设为最小值
			int minIndex = i;
			int temp = a[i];
			for(int j=i;j<a.length;j++) {
				//判断值是否比选出的最小值小
				if(a[j] < a[minIndex]) {
					minIndex = j; //将最小的数，保存索引
				}
			}
			a[i] = a[minIndex];
			a[minIndex] = temp;
		}
		return a;
	}
}
