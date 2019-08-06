package test;

import paixu.XuanZe;

public class Main {

	public static void main(String[] args) {
		int[] a = {99,66,35,86,23,41,10,8,55,70};
		//a = MaoPao.paixu(a);
		a = XuanZe.paixu(a);
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+",");
		}
	}
}
