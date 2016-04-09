package services.objLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import dataTypes.ModelData;
import math.Vector2;
import math.Vector3;

public class HomemadeObjLoader extends CoreOBJLoader {
	public ModelData parse(String filename){
		FileReader fr=null;
		try {
			fr=new FileReader(new File("res/"+filename+".obj"));
		} catch (FileNotFoundException e) {
			System.err.println("Model data "+filename+".obj not found in res/ folder!");
			e.printStackTrace();
		}
		
		BufferedReader reader=new BufferedReader(fr);
		
		String line;
		
		List<Vector3> verticies=new ArrayList<Vector3>();
		List<Vector2> UV=new ArrayList<Vector2>();
		List<Vector3> normals=new ArrayList<Vector3>();
		List<Short> indicies=new ArrayList<Short>();
		
		float[] vertArray=null;
		float[] UVArray=null;
		float[] normalsArray=null;
		short[] indiciesArray=null;
		
		try{
			while(true){
				line=reader.readLine();
				String[] currentLine=line.split(" ");
				
				//Check if it tells of vertex positions
				if(line.startsWith("v ")){
					Vector3 vertex=new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					verticies.add(vertex);
				
				//UV coords
				}else if(line.startsWith("vt ")){
					Vector2 UVCoord=new Vector2(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
					UV.add(UVCoord);
				
				//Normals
				}else if(line.startsWith("vn ")){
					Vector3 normal=new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					normals.add(normal);
				
				//Faces- these are parsed separately, in the next section
				}else if(line.startsWith("f ")){
					UVArray=new float[verticies.size()*2];
					normalsArray=new float[verticies.size()*3];
					break;
				}
			}
			
			while(line!=null){
				//Fast-forward to faces
				if(!line.startsWith("f ")){
					line=reader.readLine();
					continue;
				}
				
				//Get the three verticies in each face
				String[] currentLine=line.split(" ");
				
				//Get the vertex position, UV, and normal id's
				String[] vertex1=currentLine[1].split("/"); 
				String[] vertex2=currentLine[2].split("/"); 
				String[] vertex3=currentLine[3].split("/"); 
				
				//Process to link id's
				processVertex(vertex1, indicies, UV, normals, UVArray, normalsArray);
				processVertex(vertex2, indicies, UV, normals, UVArray, normalsArray);
				processVertex(vertex3, indicies, UV, normals, UVArray, normalsArray);
				line=reader.readLine();
			}
			
			//End
			reader.close();
			
		}catch(Exception e){
			System.err.println("An error has occured while parsing the OBJ file "+filename+".obj.");
			e.printStackTrace();
		}
		
		vertArray=new float[verticies.size()*3];
		indiciesArray=new short[indicies.size()];
		
		int vertexPointer=0;
		for(Vector3 vertex:verticies){
			vertArray[vertexPointer++]=vertex.x;
			vertArray[vertexPointer++]=vertex.y;
			vertArray[vertexPointer++]=vertex.z;
		}
		
		for(int i=0; i<indicies.size(); i++){
			indiciesArray[i]=indicies.get(i);
		}
		
		return new ModelData(vertArray, indiciesArray, normalsArray, UVArray);
		//return new ModelData(vertArray, indiciesArray);
	}
	
	private static void processVertex(String[] vertexData, List<Short> indicies, List<Vector2> UV, List<Vector3> normals, float[] textureArray, float[] normalArray){
		short currentVertexPointer=(short) (Short.parseShort(vertexData[0])-1);
		indicies.add(currentVertexPointer);
		
		Vector2 currentTex=UV.get(Integer.parseInt(vertexData[1])-1);
		textureArray[currentVertexPointer*2]=currentTex.x;
		textureArray[currentVertexPointer*2+1]=1-currentTex.y;
		
		Vector3 currentNorm=normals.get(Integer.parseInt(vertexData[2])-1);
		normalArray[currentVertexPointer*3]=currentNorm.x;
		normalArray[currentVertexPointer*3+1]=currentNorm.y;
		normalArray[currentVertexPointer*3+2]=currentNorm.z;
	}
}