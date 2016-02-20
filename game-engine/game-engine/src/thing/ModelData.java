package thing;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ModelData {
	private static List<Integer> vaos=new ArrayList<Integer>();
	private static List<Integer> vbos=new ArrayList<Integer>();
	
	private int vaoID;
	private List<Integer> vboIDs=new ArrayList<Integer>();
	
	private float[] positions;
	private float[] normals={};
	private float[] uv={};
	
	private int vertexCount;
	
	public ModelData(float[] positions){
		this.positions=positions;
		this.vertexCount=positions.length;
		
		FloatBuffer positionsBuffer=storeInBuffer(positions);
		
		//Create VAO
		createVAO();
		
		//Make a VBO, load data, and store it into the VAO
		createVBO();
		loadBuffer(positionsBuffer);
		vboToVAO(0);
		
		//Unbind all
		unbindVBO();
		unbindVAO();
	}
	
	public ModelData(float[] positions, float[] normals){
		this.positions=positions;
		this.vertexCount=positions.length;
		this.normals=normals;
		
		FloatBuffer positionsBuffer=storeInBuffer(positions);
		FloatBuffer normalsBuffer=storeInBuffer(normals);
		
		//Create VAO
		createVAO();
		
		//Make a VBO, load data, and store it into the VAO
		createVBO();
		loadBuffer(positionsBuffer);
		vboToVAO(0);
		
		//Make a VBO, load data, and store it into the VAO
		createVBO();
		loadBuffer(normalsBuffer);
		vboToVAO(1);
		
		//Unbind all
		unbindVBO();
		unbindVAO();
	}
	
	public ModelData(float[] positions, float[] normals, float[] uv){
		this.positions=positions;
		this.vertexCount=positions.length;
		this.normals=normals;
		this.uv=uv;
		
		FloatBuffer positionsBuffer=storeInBuffer(positions);
		FloatBuffer normalsBuffer=storeInBuffer(normals);
		FloatBuffer uvBuffer=storeInBuffer(uv);
		
		//Create VAO
		createVAO();
		
		//Make a VBO, load data, and store it into the VAO
		createVBO();
		loadBuffer(positionsBuffer);
		vboToVAO(0);
		
		//Make a VBO, load data, and store it into the VAO
		createVBO();
		loadBuffer(normalsBuffer);
		vboToVAO(1);
		
		//Make a VBO, load data, and store it into the VAO
		createVBO();
		loadBuffer(uvBuffer);
		vboToVAO(2);
		
		//Unbind all
		unbindVBO();
		unbindVAO();
	}
	
	public float[] getPositions(){
		return positions;
	}
	
	public float[] getNormals(){
		return normals;
	}
	
	public float[] getUv(){
		return uv;
	}
	
	public int getID(){
		return vaoID;
	}
	
	public int getVertexCount(){
		return vertexCount;
	}
	
	public void cleanup(){
		unbindVAO();
		unbindVBO();
		
		for(int VBO:vbos){
			deleteVBO(VBO);
		}
		
		for(int VAO:vaos){
			deleteVAO(VAO);
		}
	}
	
	private FloatBuffer storeInBuffer(float[] array){
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(array.length);
		verticesBuffer.put(array);
		verticesBuffer.flip();
		return verticesBuffer;
	}
	
	private void createVAO(){
		vaoID=vaos.size();
		vaos.add(GL30.glGenVertexArrays());
		bindVAO();
	}
	
	private void bindVAO(){
		GL30.glBindVertexArray(vaos.get(vaoID));
	}
	
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private void createVBO(){
		int index=vboIDs.size();
		vboIDs.add(vbos.size());
		vbos.add(GL15.glGenBuffers());
		bindVBO(index);
	}
	
	private void bindVBO(int index){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboIDs.get(index));
	}
	
	private void unbindVBO(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void loadBuffer(FloatBuffer buffer){
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private void vboToVAO(int index){
		GL20.glVertexAttribPointer(index, 3, GL11.GL_FLOAT, false, 0, 0);
	}
	
	private void deleteVBO(int id){
		GL15.glDeleteBuffers(id);
	}
	
	private void deleteVAO(int id){
		GL30.glDeleteVertexArrays(id);
	}
}
