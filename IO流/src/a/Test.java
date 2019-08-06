package a;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream("d:/a.txt");
			FileOutputStream fos = new FileOutputStream("d:/ab.txt");
			while(true) {
				int i = fis.read();
				if(i ==-1)break;
				System.out.println(i);
				fos.write(i);
			}
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
