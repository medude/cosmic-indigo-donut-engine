package services.fileLoader;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

import dataTypes.TextFile;
import dataTypes.Texture;
import services.Services;

public class JavaFileLoader extends CoreFileLoader {
	public List<Integer> images=new ArrayList<Integer>();
	
	@Override
	public TextFile loadFile(String path){
		File file;
		DataInputStream stream;
		BufferedReader read;
		
		try{
			file=new File("res/"+path);
			stream=new DataInputStream(new FileInputStream(file));
			read=new BufferedReader(new InputStreamReader(stream));
			
			List<String> lines=new ArrayList<String>();
			String line;
			
			while((line=read.readLine())!=null){
				lines.add(line);
			}
			
			read.close();
			
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

	
	@Override
	public Texture loadImage(String filename){
		final int BYTES_PER_PIXEL=4;
		BufferedImage image=null;
		
		try{
			image=ImageIO.read(new File("res/images/"+filename+".png"));
		}catch(IOException e){
			Services.getErrorHandler().handle(e);
		}
		
		int[] pixels=new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		
		ByteBuffer buffer=BufferUtils.createByteBuffer(image.getWidth()*image.getHeight()*BYTES_PER_PIXEL);
		
		for(int y=0; y<image.getHeight(); y++){
			for(int x=0; x<image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); //R
				buffer.put((byte) ((pixel >> 8) & 0xFF));  //G
				buffer.put((byte) (pixel & 0xFF));         //B
				buffer.put((byte) ((pixel >> 24) & 0xFF)); //A
			}
		}
		
		buffer.flip();
		
		// You now have a ByteBuffer filled with the color data of each pixel.
		// Now just create a texture ID and bind it. Then you can load it using 
		// whatever OpenGL method you want, for example:
		
		int textureID=GL11.glGenTextures(); //Generate texture ID
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); //Bind texture ID#
		
		//Setup wrap mode
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		//Setup texture scaling filtering
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
		images.add(textureID);
		
		return new Texture(textureID);
	}


	
	@Override
	public void cleanup() {
		for(int image:images){
			GL11.glDeleteTextures(image);
		}
	}
}