//Sena Hernandez
//3/12/18
//1st Period

package textExcel;

public class EmptyCell implements Cell{

	public String abbreviatedCellText() { // text for spreadsheet cell display, must be exactly length 10
		return "          ";
	}
	
	
	public String fullCellText() { // text for individual cell inspection, not truncated or padded
		return "";
	}
	public EmptyCell() {
		
	}
}
