package dataTypes;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
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
	
	private int vaoID=0;
	private int indiciesID=0;
	
	private float[] positions={};
	private short[] indicies={};
	private float[] normals={};
	private float[] uv={};
	
	private int vertexCount;
	
	public ModelData(float[] positions, short[] indicies){
		create(positions, indicies, normals, uv);
	}
	
	public ModelData(float[] positions, short[] indicies, float[] normals){
		create(positions, indicies, normals, uv);
	}
	
	public ModelData(float[] positions, short[] indicies, float[] normals, float[] uv){
		create(positions, indicies, normals, uv);
	}
	
	private void create(float[] positions, short[] indicies, float[] normals, float[] uv){
		this.positions=positions;
		this.indicies=indicies;
		this.vertexCount=indicies.length;
		this.normals=normals;
		this.uv=uv;
		
		FloatBuffer positionsBuffer=storeInBuffer(positions);
		ShortBuffer indiciesBuffer=storeInBuffer(indicies);
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
		vboToVAO(2, 2);
		
		//Unbind all
		unbindVBO();
		unbindVAO();
		
		//Make a VBO, load data
		createIndiciesVBO();
		loadBuffer(indiciesBuffer);
		unbindIndiciesVBO();
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
	
	public short[] getIndicies(){
		return indicies;
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
	
	public int getIndiciesID() {
		return indiciesID;
	}
	
	public int getVAOID(){
		return vaoID;
	}
	
	private FloatBuffer storeInBuffer(float[] array){
		FloatBuffer buffer=BufferUtils.createFloatBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	
	private ShortBuffer storeInBuffer(short[] array){
		ShortBuffer buffer=BufferUtils.createShortBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	
	private void createVAO(){
		vaoID=GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		vaos.add(vaoID);
		
	}
	
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private void createVBO(){
		int vboID=GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		vbos.add(vboID);
	}
	
	private void createIndiciesVBO(){
		int vboID=GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		vbos.add(vboID);
		this.indiciesID=vboID;
	}
	
	private void unbindVBO(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindIndiciesVBO(){
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private void loadBuffer(FloatBuffer indiciesBuffer){
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, indiciesBuffer, GL15.GL_STATIC_DRAW);
	}
	
	private void loadBuffer(ShortBuffer buffer){
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private void vboToVAO(int index){
		GL20.glVertexAttribPointer(index, 3, GL11.GL_FLOAT, false, 0, 0);
	}
	
	private void vboToVAO(int index, int size){
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
	}
	
	private void deleteVBO(int id){
		GL15.glDeleteBuffers(id);
	}
	
	private void deleteVAO(int id){
		GL30.glDeleteVertexArrays(id);
	}
}
