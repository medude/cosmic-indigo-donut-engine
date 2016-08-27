package components.types;

import components.Component;
import dataTypes.Texture;

public class TextureComponent extends Component {
    private Texture texture;

    public TextureComponent(Texture texture) {
	super("TextureComponent");
	this.texture = texture;
    }

    public Texture getTexture() {
	return texture;
    }
}