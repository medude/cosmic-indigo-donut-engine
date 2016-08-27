package dataTypes;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ModelData {
    private static List<Integer> vaos = new ArrayList<Integer>();
    private static List<Integer> vbos = new ArrayList<Integer>();

    private int vaoID = 0;
    private int indiciesID = 0;

    // private float[] positions = {};
    // private int[] indicies = {};
    // private float[] normals = {};
    // private float[] uv = {};

    private int vertexCount;

    public ModelData(double[] positions, int[] indicies) {
	// create(positions, indicies, normals, uv);
	create(positions, indicies, new double[0], new double[0]);
    }

    public ModelData(double[] positions, int[] indicies, double[] normals) {
	// create(positions, indicies, normals, uv);
	create(positions, indicies, normals, new double[0]);
    }

    public ModelData(double[] positions, int[] indicies, double[] normals, double[] uv) {
	create(positions, indicies, normals, uv);
    }

    private void create(double[] positions, int[] indicies, double[] normals, double[] uv) {
	// this.positions = positions;
	// this.indicies = indicies;
	// this.normals = normals;
	// this.uv = uv;
	this.vertexCount = indicies.length;

	FloatBuffer positionsBuffer = storeInBuffer(positions);
	IntBuffer indiciesBuffer = storeInBuffer(indicies);
	FloatBuffer normalsBuffer = storeInBuffer(normals);
	FloatBuffer uvBuffer = storeInBuffer(uv);

	// Create VAO
	createVAO();

	// Make a VBO, load data, and store it into the VAO
	createVBO();
	loadBuffer(positionsBuffer);
	vboToVAO(0);

	// Make a VBO, load data, and store it into the VAO
	createVBO();
	loadBuffer(normalsBuffer);
	vboToVAO(1);

	// Make a VBO, load data, and store it into the VAO
	createVBO();
	loadBuffer(uvBuffer);
	vboToVAO(2, 2);

	// Unbind all
	unbindVBO();
	unbindVAO();

	// Make a VBO, load data
	createIndiciesVBO();
	loadBuffer(indiciesBuffer);
	unbindIndiciesVBO();
    }

    // public float[] getPositions() {
    // return positions;
    // }
    //
    // public float[] getNormals() {
    // return normals;
    // }
    //
    // public float[] getUv() {
    // return uv;
    // }
    //
    // public int[] getIndicies() {
    // return indicies;
    // }

    public int getVertexCount() {
	return vertexCount;
    }

    public void cleanup() {
	unbindVAO();
	unbindVBO();

	for (int VBO : vbos) {
	    deleteVBO(VBO);
	}

	for (int VAO : vaos) {
	    deleteVAO(VAO);
	}
    }

    public int getIndiciesID() {
	return indiciesID;
    }

    public int getVAOID() {
	return vaoID;
    }

    private FloatBuffer storeInBuffer(float[] array) {
	FloatBuffer buffer = BufferUtils.createFloatBuffer(array.length);
	buffer.put(array);
	buffer.flip();
	return buffer;
    }

    private FloatBuffer storeInBuffer(double[] array) {
	float[] array2 = new float[array.length];

	for (int i = 0; i < array.length; i++) {
	    array2[i] = (float) array[i];
	}

	return storeInBuffer(array2);
    }

    private IntBuffer storeInBuffer(int[] array) {
	IntBuffer buffer = BufferUtils.createIntBuffer(array.length);
	buffer.put(array);
	buffer.flip();
	return buffer;
    }

    private void createVAO() {
	vaoID = GL30.glGenVertexArrays();
	GL30.glBindVertexArray(vaoID);
	vaos.add(vaoID);

    }

    private void unbindVAO() {
	GL30.glBindVertexArray(0);
    }

    private void createVBO() {
	int vboID = GL15.glGenBuffers();
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
	vbos.add(vboID);
    }

    private void createIndiciesVBO() {
	int vboID = GL15.glGenBuffers();
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
	vbos.add(vboID);
	this.indiciesID = vboID;
    }

    private void unbindVBO() {
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindIndiciesVBO() {
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private void loadBuffer(FloatBuffer indiciesBuffer) {
	GL15.glBufferData(GL15.GL_ARRAY_BUFFER, indiciesBuffer, GL15.GL_STATIC_DRAW);
    }

    private void loadBuffer(IntBuffer buffer) {
	GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private void vboToVAO(int index) {
	GL20.glVertexAttribPointer(index, 3, GL11.GL_FLOAT, false, 0, 0);
    }

    private void vboToVAO(int index, int size) {
	GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
    }

    private void deleteVBO(int id) {
	GL15.glDeleteBuffers(id);
    }

    private void deleteVAO(int id) {
	GL30.glDeleteVertexArrays(id);
    }
}
