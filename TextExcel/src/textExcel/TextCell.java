//Sena Hernandez
//3/12/18
//1st Period


package textExcel;

public class TextCell implements Cell{
	
	private String text;
	
	public TextCell(String text) {
		this.text = text;
	}
	
	// text for individual cell inspection, not truncated or padded
	
	public String fullCellText(){
		return "\"" + text + "\"";
	}

	// text for spreadsheet cell display, must be exactly length 10
	public String abbreviatedCellText() {
		String spaces = "          ";
		if(text.length() < 10) {
			return text + spaces.substring(0, 10 - text.length());
		}
		return text.substring(0, 10);
	}
}