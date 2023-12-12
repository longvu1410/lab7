package lab_7;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestReadfFile {
	public static void main(String[] args) throws FileNotFoundException {
			Scanner input = new Scanner(new File("D:\\laptrinh\\lab_7\\src\\lab_7\\fit.txt"));
			
			while (input.hasNext()) {
			String word = input.next();
			System.out.println(word);
				
			}
	}
}




