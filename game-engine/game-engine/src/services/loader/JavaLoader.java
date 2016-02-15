package services.loader;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataTypes.TextFile;
import services.Services;

public class JavaLoader extends CoreLoader {
	File file;
    DataInputStream read;
    BufferedInputStream stream;

	@Override
	public TextFile loadFile(String path){
		try{
			file=new File("res/"+path);
			stream=new BufferedInputStream(new FileInputStream(file));
			read=new DataInputStream(stream);
			
			List<String> lines=new ArrayList<String>();
			
			while(read.available()>0){
				
			}
			
			String[] lineList=new String[lines.size()];
			
			for(int i=0; i<lines.size(); i++){
				lineList[i]=lines.get(i);
			}
			
			return new TextFile(lineList);
		} catch(IOException e){
			Services.getErrorHandler().handle(e);
		}
		
		return null;
	}
}