package seventeh.science.solarsystem;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Entity {
	
	abstract void update(int delta);
	
	abstract void render(GameContainer arg0, Graphics arg1);

}
