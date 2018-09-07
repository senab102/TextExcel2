//Sena Hernandez
//3/12/18
//1st Period

package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;
//using scanner to get user input
public class TextExcel {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Spreadsheet spreadsheet = new Spreadsheet();
		boolean done = false;
		while (!done) {
			String nextCommand = input.nextLine();
			if (nextCommand.equals("quit")) {
				done = true;
			}else {
			System.out.println(spreadsheet.processCommand(nextCommand));
	
				
			}
		}
	}
}