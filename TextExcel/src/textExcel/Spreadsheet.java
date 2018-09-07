//Sena Hernandez
//3/12/18
//1st Period

package textExcel;

public class Spreadsheet implements Grid {
	private Cell[][] cells;

	public Spreadsheet() {
		cells = new Cell[getRows()][getCols()];
		clearGrid();
	}

	public String processCommand(String command) {
		if (command.contains("=")) {
			// assignment
			String[] parts = command.split(" = ", 2);
			assign(parts);
			return getGridText();
		} else if (command.toUpperCase().contains("CLEAR ")) {
			// clear one cell
			String[] parts = command.split(" ");
			SpreadsheetLocation loc = new SpreadsheetLocation(parts[1]);
			this.cells[loc.getRow()][loc.getCol()] = new EmptyCell();
			return getGridText();
		} else if (command.toUpperCase().contains("CLEAR")) {
			// clear whole cell
			clearGrid();
			return getGridText();
		} else {
			// inspect the cell
			SpreadsheetLocation loc = new SpreadsheetLocation(command);
			return getCell(loc).fullCellText();
		}

	}

	private void clearGrid() {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getCols(); j++) {
				this.cells[i][j] = new EmptyCell();
			}
		}
	}

	public int getRows() {
		return 20;
	}

	public int getCols() {
		return 12;
	}

	// call on certain cell
	public Cell getCell(Location loc) {
		return this.cells[loc.getRow()][loc.getCol()];
	}

	public Cell getCell(int i, int j) {
		return this.cells[i][j];
	}

	// creates the grid of the spreadsheet
	public String getGridText() {
		String output = "   |";
		for (int i = 0; i < getCols(); i++) {
			output = output + (char) ((int) 'A' + i);
			output = output + "         |";
		}
		output = output + "\n";
		for (int i = 1; i <= getRows(); i++) {
			if (i <= 9) {
				output = output + i + "  |";
			} else {
				output = output + i + " |";
			}
			for (int j = 0; j < getCols(); j++) {
				output = output + this.cells[i - 1][j].abbreviatedCellText() + "|";
			}
			output = output + "\n";
		}
		return output;
	}

	// different assignments depending on whether what's being passed in is text,
	// percent, formula, or value
	public void assign(String[] parts) {
		SpreadsheetLocation loc = new SpreadsheetLocation(parts[0]);
		if (parts[1].contains("\"")) {
			this.cells[loc.getRow()][loc.getCol()] = new TextCell(parts[1].substring(1, parts[1].length() - 1));
		} else if (parts[1].contains("%")) {
			this.cells[loc.getRow()][loc.getCol()] = new PercentCell(parts[1]);
		} else if (parts[1].contains("(")) {
			this.cells[loc.getRow()][loc.getCol()] = new FormulaCell(parts[1], this);
		} else {
			this.cells[loc.getRow()][loc.getCol()] = new ValueCell(parts[1]);
		}
	}

}