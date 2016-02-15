package dataTypes;

public class TextFile {
	private String[] lines;
	private int currentLine=0;
	
	public TextFile(String[] lines){
		this.lines=lines;
	}
	
	public String[] getLines(){
		return lines;
	}
	
	public String getNextLine(){
		String currentString=lines[currentLine];
		currentLine++;
		return currentString;
	}
	
	public void skipLine(){
		currentLine++;
	}
	
	public void skipLines(int lines){
		currentLine+=lines;
	}
	
	public void backLine(){
		currentLine--;
	}
	
	public void backLines(int lines){
		currentLine-=lines;
	}
}
