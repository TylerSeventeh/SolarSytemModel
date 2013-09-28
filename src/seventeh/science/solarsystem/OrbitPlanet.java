package seventeh.science.solarsystem;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class OrbitPlanet implements Entity{
	
	Image im; 				//image of planet
	Vector2f pos;			//position of planet
	Vector2f labelPos;		//position of label
	String label;

	public OrbitPlanet(Image im,String label,GameContainer container) {
		
		this.im = im;
		this.label = label;
		
		pos = new Vector2f();
		pos.x = SolarSystem.sunX - im.getWidth()/2;
		pos.y = SolarSystem.sunY - im.getHeight()/2;
		
		labelPos.x = SolarSystem.sunX - (container.getGraphics().getFont().getWidth(label)/2);
		labelPos.y = pos.y + im.getHeight();
		
		
	}

	@Override
	public void update(int delta) {
		
		im.rotate(delta * 0.01F * SolarSystem.speedMultiplier);
		
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		
		g.drawImage(im, pos.x, pos.y);
		
		g.setColor(Color.white);
		g.drawString(label, labelPos.x, labelPos.y);
		
	}

}
