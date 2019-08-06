
public class Array {
	//主函数定义数组
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] array = {58,47,36,10,45,29,5,1,9};
		int i = 47;
		System.out.println("原数组：");
		printArray(array); //打印数组
		findArray(array);  //求极值
		int[] array1 = {58,47,36,10,45,29,5,1,9};
		orderArray(array1); //冒泡排序
		System.out.println("冒泡排序后数组数组：");
		printArray(array1);
		reverseArray(array1);//数组倒置
		System.out.println("倒置后数组：");
		printArray(array1);
		int index = findIndex(array1,i);
		System.out.println(i+"的索引是"+index);
	}
	//求极值
	public static void findArray(int[] array) {
		int min = array[0],max = array[0];
		for(int i = 1;i<array.length;i++) {
			//比较求最小值
			if(array[i]<min) {
				int temp = min;
				min = array[i];
				array[i] = temp;
			}
			//比较求最大值
			if(array[i]>max) {
				int temp = min;
				min = array[i];
				array[i] = temp;
			}
		}
		System.out.print("最大值为："+max+"\t");
		System.out.println("最小值为："+min);
	}
	//冒泡法排序数组（大到小）
	public static void orderArray(int[] array) {
		//需要比较轮数 array.length-1 轮
		for(int i = 0;i<array.length-1;i++) {
			//每轮需要比较 array.length-i-1  次
			for(int j = 0;j<array.length-i-1;j++) {
				//若后一个比前一个大，交换位置
				if(array[j]<array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}	
	//数组倒置
	public static void reverseArray(int[] array) {
		for(int i= 0;i<array.length/2;i++) {
			int temp = array[i];
			array[i] = array[array.length-i-1];
			array[array.length-i-1] = temp;
		}
	}
	//二分法查找(需要是顺序数组【由小到大】)
	public static int findIndex(int[] array, int x) {
		//定义初始索引值
		int index = -1;
		//定义初始二分最小值
		int minIndex = 0;
		//定义初始二分最大值
		int maxIndex = array.length;
		//定义初始二分中间值
		int midIndex = array.length/2;
		while(x != array[midIndex]){
			//若没有值，返回-1
			if(minIndex>= maxIndex ){
				return -1;
			}
			if(x > array[midIndex]) {
				minIndex = midIndex+1;
				midIndex = (minIndex+maxIndex)/2;
			}else {
				maxIndex = midIndex-1;
				midIndex = (minIndex+maxIndex)/2;
			}
		}
		index = midIndex;
		return index;
	}
	//打印数组 /遍历数组
	public static void printArray(int[] array) {
		for(int i = 0;i<array.length;i++) {
			System.out.print(array[i]+"\t");
		}
		System.out.println();
	}
}
