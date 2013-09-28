package seventeh.science.solarsystem;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Planet implements Entity{
	
	private Image im; 		//image of planet
	Vector2f pos;			//position of planet
	Vector2f labelPos;		//position of label
	Vector2f labelTransform;//transform vector of label
	Vector2f transformationVector; //makes it as though any change to pos is at the center of the planet, not the upper-left
	float orbitingRadius;	//radius of the planets orbit around the sun; would be 0 if it is the sun
	double angle;
	String label;
	boolean showLabel;
	float orbitRate;

	static Random ran = new Random();
	public static boolean insanity = false;

	
	
	public Planet(Image im, Vector2f startingPos,String label,float orbitingRadius,int angle, GameContainer container,
			boolean show, float orbitRate) {
		
		this.im = im;
		this.angle = angle;
		this.label = label;
		this.orbitingRadius = orbitingRadius;
		showLabel = show;
		this.orbitRate = orbitRate;
		
		pos = startingPos;
		transformationVector = new Vector2f(  -((float)im.getWidth()/2),  -((float)im.getHeight()/2));
		
		if(orbitingRadius != 0){
			labelTransform = new Vector2f(im.getWidth()/2 - container.getGraphics().getFont().getWidth(label)/2,
					im.getHeight());
			labelPos = pos.copy().add(labelTransform);
		}else{
			labelPos = new Vector2f(pos.x + im.getWidth()/2, pos.y + im.getHeight()/2);
		}
	}
	
	public Planet(Image im, Vector2f startingPos,String label,float orbitingRadius,int angle, GameContainer container,
			float orbitRate) {
		this( im,  startingPos, label, orbitingRadius, angle,  container,true,orbitRate);
	}

	
	
	@Override
	public void update(int delta) {
		if(orbitingRadius == 0) return; //if this is the sun, return
		
		
		if(!insanity){
			angle += ((delta * 0.005 )  / this.orbitRate) *  SolarSystem.speedMultiplier;
			angle %= 361;
		}else
			angle = (ran.nextDouble() * 360);//  insanity mode
		
		pos.x = (float)(Math.cos(Math.toRadians(angle))*orbitingRadius + SolarSystem.sunX);
		pos.y = (float)(Math.sin(Math.toRadians(angle))*orbitingRadius + SolarSystem.sunY);
		
		pos.add(transformationVector);
		labelPos = pos.copy().add(labelTransform);
	}

	
	
	@Override
	public void render(GameContainer container, Graphics g) {

		g.setColor(new Color(0x3200ffff));
		if(orbitingRadius != 0)
			g.drawOval(SolarSystem.sunX - orbitingRadius, SolarSystem.sunY - orbitingRadius,
				(orbitingRadius*2), (orbitingRadius*2));
		
		g.drawImage(im, pos.x, pos.y);
		
		if(!showLabel) return;
		
		if(orbitingRadius != 0){
			g.setColor(Color.white);
			g.drawString(label, labelPos.x, labelPos.y);
		}else{
			g.setColor(Color.black);
			g.drawString(label, labelPos.x, labelPos.y);
		}

	}

}
