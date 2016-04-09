package components.types;

import components.Component;
import dataTypes.Texture;

public class TextureComponent extends Component {
	private Texture texture;
	
	public TextureComponent(int id, Texture texture){
		super(id, "TextureComponent");
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
}