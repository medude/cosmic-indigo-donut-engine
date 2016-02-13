package messageBus;

public class Message {
	private int id=0;
	private float[] fData=null;
	private String[] sData=null;
	
	public Message(int id){
		this.id=id;
	}
	
	public Message(int id, float[] fData){
		this.fData=fData;
		this.id=id;
	}
	
	public Message(int id, float[] fData, String[] sData){
		this.fData=fData;
		this.sData=sData;
		this.id=id;
	}
	
	public Message(int id, String[] sData){
		this.sData=sData;
		this.id=id;
	}

	public float[] getfData() {
		return fData;
	}

	public void setfData(float[] fData) {
		this.fData = fData;
	}

	public String[] getsData() {
		return sData;
	}

	public void setsData(String[] sData) {
		this.sData = sData;
	}

	public int getId() {
		return id;
	}
}