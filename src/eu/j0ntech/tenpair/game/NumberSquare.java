package eu.j0ntech.tenpair.game;

public class NumberSquare {
	
	private byte value;
	
	private float startX;
	
	private float endX;
	
	private float startY;
	
	private float endY;
	
	private float centerX;
	
	private float centerY;
	
	private int row;
	
	private int column;
	
	private boolean selected = false;
	
	private boolean highlighted = false;
	
	private boolean scratched = false;
	
	public NumberSquare(byte value) {
		this.value = value;
	}
	
	public NumberSquare(byte value, int row, int column) {
		this.value = value;
		this.row = row;
		this.column = column;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}
	
	public void setCoordinates(float left, float top, float right, float bottom) {
		startX = left;
		startY = top;
		endX = right;
		endY = bottom;
		calculateCenter();
	}

	public float getStartX() {
		return startX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
		calculateCenter();
	}

	public float getEndX() {
		return endX;
	}

	public void setEndX(float endX) {
		this.endX = endX;
		calculateCenter();
	}

	public float getStartY() {
		return startY;
	}

	public void setStartY(float startY) {
		this.startY = startY;
		calculateCenter();
	}

	public float getEndY() {
		return endY;
	}

	public void setEndY(float endY) {
		this.endY = endY;
		calculateCenter();
	}

	public float getCenterX() {
		return centerX;
	}

	public void setCenterX(float centerX) {
		this.centerX = centerX;
		calculateCenter();
	}

	public float getCenterY() {
		return centerY;
	}

	public void setCenterY(float centerY) {
		this.centerY = centerY;
		calculateCenter();
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public boolean isScratched() {
		return scratched;
	}

	public void setScratched(boolean scratched) {
		this.scratched = scratched;
	}

	private void calculateCenter() {
		centerX = startX + (endX - startX) / 2;
		centerY = startY + (endY - startY) / 2;
	}

}
