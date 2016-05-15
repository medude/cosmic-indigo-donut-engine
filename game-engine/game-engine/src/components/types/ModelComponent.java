package components.types;

import components.Component;
import dataTypes.ModelData;

public class ModelComponent extends Component {
	private ModelData model;
	
	public ModelComponent(ModelData model){
		super("ModelComponent");
		this.model=model;
	}
	
	public ModelData getModel(){
		return model;
	}
}
