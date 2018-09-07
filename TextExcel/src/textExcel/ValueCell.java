//Sena Hernandez
//3/12/18
//1st Period

package textExcel;
//changing the string values to a double
public class ValueCell extends RealCell {

	public ValueCell(String text) {
		super(text);
	}
	public double getDoubleValue() {
		return Double.parseDouble(getText());
	}
	public String fullTextCell() {
		return "" + getDoubleValue();
	}
	public String printCell() {
		String out = "" +  fullTextCell();
		return out;
	}
	

	

	
}