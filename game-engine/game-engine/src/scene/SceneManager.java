package scene;

public class SceneManager {
    private static int componentID = -1;

    public static int getComponentID() {
	return componentID++;
    }
}