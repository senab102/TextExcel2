//Sena Hernandez
//3/12/18
//1st Period

package textExcel;

public class RealCell implements Cell {
	private String text;

	public RealCell(String text) {
		this.setText(text);
	}

	// text for individual cell inspection, not truncated or padded
	public String fullCellText() {
		return getText();
	}
	
	//returns the calculated value of the cell as a double (not a String).
	public double getDoubleValue() {
		return Double.parseDouble(getText());
	}
	
	// text for spreadsheet cell display, must be exactly length 10
	public String abbreviatedCellText() {
		String spaces = "          ";
		String out = "" + getDoubleValue();
		if (out.length() < 10) {
			return out + spaces.substring(0, 10 - out.length());
		}
		return out.substring(0, 10);
	}
	


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}