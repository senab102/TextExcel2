//Sena Hernandez
//3/12/18
//1st Period

package textExcel;

public class FormulaCell extends RealCell {
	private Spreadsheet sheet;

	public FormulaCell(String text, Spreadsheet sheet) {
		super(text);
		this.sheet = sheet; // formulacell can now access spreadsheet
	}

	// Gets the assignment (like (A1 + 1) or (A2 + B3) and splits it and gets the
	// value in the cell it calls
	// Also turns the vaalue into a double if it's a numeric value
	private double parse(String value) {
		if (value.charAt(0) >= 'A' && value.charAt(0) <= 'Z') {
			SpreadsheetLocation loc1 = new SpreadsheetLocation(value);
			RealCell cell = (RealCell) this.sheet.getCell(loc1);
			return cell.getDoubleValue();
		} else {
			double temp = Double.parseDouble(value);
			return temp;
		}
	}

	// deals with assignments that has "SUM" or "AVG" in the formula
	// loops through the cells that wants to take the sum or avg of (ex: (Sum a1-c3)
	public double getDoubleValue() {

		String text = getText().toUpperCase();
		String[] parts = text.substring(2, text.length() - 2).split(" ");

		if (parts[0].equals("SUM") || parts[0].equals("AVG")) {
			String[] parts1 = parts[1].split("-");
			SpreadsheetLocation loc1 = new SpreadsheetLocation(parts1[0]);
			SpreadsheetLocation loc2 = new SpreadsheetLocation(parts1[1]);
			int counter = 0;
			double sum = 0;
			for (int i = loc1.getRow(); i <= loc2.getRow(); i++) {
				for (int j = loc1.getCol(); j <= loc2.getCol(); j++) {
					RealCell cell = (RealCell) this.sheet.getCell(i, j);
					sum += cell.getDoubleValue();
					counter++;
				}
			}
			if (parts[0].equals("AVG")) {
				return sum / counter;
			} else {
				return sum;
			}
		}
		// do the (+,-,*,/) operations when it is just a numeric value
		double temp = parse(parts[0]);
		String operand = "";
		for (int i = 1; i < parts.length; i++) {
			if (i % 2 == 0) {
				double num = parse(parts[i]);
				if (operand.equals("+")) {
					temp += num;
				} else if (operand.equals("-")) {
					temp -= num;
				} else if (operand.equals("*")) {
					temp *= num;
				} else if (operand.equals("/")) {
					temp /= num;
				}

			} else {
				operand = parts[i];
			}
		}
		return temp;

	}

	// text for individual cell inspection, not truncated or padded
	public String fullCellText() {
		return getText();
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
}