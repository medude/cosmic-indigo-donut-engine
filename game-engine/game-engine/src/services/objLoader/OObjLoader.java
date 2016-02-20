package services.objLoader;

import java.io.IOException;
import java.util.List;

import externalLibraries.oobjloader.builder.Build;
import externalLibraries.oobjloader.builder.Face;
import externalLibraries.oobjloader.parser.Parse;
import services.Services;
import thing.ModelData;

public class OObjLoader extends CoreOBJLoader {
	private Build build=new Build();
	@SuppressWarnings("unused")
	private Parse parser;
	
	@Override
	public ModelData parse(String filename){
		try{
			parser=new Parse(build, "res/"+filename);
		}catch(IOException e){
			Services.getErrorHandler().handle(e);
		}
		
		List<Face> faceList=build.faces;
		
		float[] verticies=new float[faceList.size()*9];
		
		for(int i=0; i<faceList.size(); i++){
			verticies[i*9]=faceList.get(i).vertices.get(0).v.x;
			verticies[i*9+1]=faceList.get(i).vertices.get(0).v.y;
			verticies[i*9+2]=faceList.get(i).vertices.get(0).v.z;
			verticies[i*9+3]=faceList.get(i).vertices.get(1).v.x;
			verticies[i*9+4]=faceList.get(i).vertices.get(1).v.y;
			verticies[i*9+5]=faceList.get(i).vertices.get(1).v.z;
			verticies[i*9+6]=faceList.get(i).vertices.get(2).v.x;
			verticies[i*9+7]=faceList.get(i).vertices.get(2).v.y;
			verticies[i*9+8]=faceList.get(i).vertices.get(2).v.z;
		}
		
		return new ModelData(verticies);
	}
}
