package services.fileLoader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dataTypes.TextFile;
import services.Services;

public class JavaFileLoader extends CoreFileLoader {
	File file;
	DataInputStream stream;
	BufferedReader read;

	@Override
	public TextFile loadFile(String path){
		try{
			file=new File("res/"+path);
			stream=new DataInputStream(new FileInputStream(file));
			read=new BufferedReader(new InputStreamReader(stream));
			
			List<String> lines=new ArrayList<String>();
			String line;
			
			while((line=read.readLine())!=null){
				lines.add(line);
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