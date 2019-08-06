package a;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestInput {
	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader("d:/abc.txt");
			FileWriter fw = new FileWriter("d:/a.txt");
			while (true) {
				int i = reader.read();
				if (i == -1)
					break;
				System.out.println(i);
				System.out.println(i);

				fw.write(i);
			}
			reader.close();
			fw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
