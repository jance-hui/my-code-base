
public class Array {
	//��������������
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int[] array = {58,47,36,10,45,29,5,1,9};
		int i = 47;
		System.out.println("ԭ���飺");
		printArray(array); //��ӡ����
		findArray(array);  //��ֵ
		int[] array1 = {58,47,36,10,45,29,5,1,9};
		orderArray(array1); //ð������
		System.out.println("ð��������������飺");
		printArray(array1);
		reverseArray(array1);//���鵹��
		System.out.println("���ú����飺");
		printArray(array1);
		int index = findIndex(array1,i);
		System.out.println(i+"��������"+index);
	}
	//��ֵ
	public static void findArray(int[] array) {
		int min = array[0],max = array[0];
		for(int i = 1;i<array.length;i++) {
			//�Ƚ�����Сֵ
			if(array[i]<min) {
				int temp = min;
				min = array[i];
				array[i] = temp;
			}
			//�Ƚ������ֵ
			if(array[i]>max) {
				int temp = min;
				min = array[i];
				array[i] = temp;
			}
		}
		System.out.print("���ֵΪ��"+max+"\t");
		System.out.println("��СֵΪ��"+min);
	}
	//ð�ݷ��������飨��С��
	public static void orderArray(int[] array) {
		//��Ҫ�Ƚ����� array.length-1 ��
		for(int i = 0;i<array.length-1;i++) {
			//ÿ����Ҫ�Ƚ� array.length-i-1  ��
			for(int j = 0;j<array.length-i-1;j++) {
				//����һ����ǰһ���󣬽���λ��
				if(array[j]<array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}	
	//���鵹��
	public static void reverseArray(int[] array) {
		for(int i= 0;i<array.length/2;i++) {
			int temp = array[i];
			array[i] = array[array.length-i-1];
			array[array.length-i-1] = temp;
		}
	}
	//���ַ�����(��Ҫ��˳�����顾��С����)
	public static int findIndex(int[] array, int x) {
		//�����ʼ����ֵ
		int index = -1;
		//�����ʼ������Сֵ
		int minIndex = 0;
		//�����ʼ�������ֵ
		int maxIndex = array.length;
		//�����ʼ�����м�ֵ
		int midIndex = array.length/2;
		while(x != array[midIndex]){
			//��û��ֵ������-1
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
	//��ӡ���� /��������
	public static void printArray(int[] array) {
		for(int i = 0;i<array.length;i++) {
			System.out.print(array[i]+"\t");
		}
		System.out.println();
	}
}
