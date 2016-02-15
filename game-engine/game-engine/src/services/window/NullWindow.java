package services.window;

public class NullWindow extends CoreWindow {
	@Override
	public void init(){}

	@Override
	public void refresh(long id){}

	@Override
	public boolean isOpen(long id){return false;}

	@Override
	public long create(String windowName){return 1;}

	@Override
	public void close(long id){}

	@Override
	public float getScreenWidth(){return 0;}

	@Override
	public float getScreenHeight(){return 0;}
}