package apis.loader;

import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;

public interface LoaderType {
	public TextFile loadFile(String filename) throws MalformedFileException;

	public Texture loadImage(String filename) throws MalformedFileException;

	public void cleanup();

	public ModelData loadOBJ(String filename) throws MalformedFileException;

	public void processJSON(String filename) throws MalformedFileException;
}
