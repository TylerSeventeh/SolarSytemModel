package seventeh.science.solarsystem;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class SolarSystem extends BasicGame {
	
	/*
	 * TODO add planet rotation (as opposed to revolution) and cap the fps
	 * also TODO add planet orbit eclipse and fix the string to be adjacent to it instead of following the planet
	 * 
	 */
	List<Entity> entities;
	public static float sunX = 500;
	public static float sunY = 450;
	public static final float DEFAULT_SPEEED = 4;
	public static float speedMultiplier = DEFAULT_SPEEED;//TODO impliment the keyboard listener to deal with this
	private int keyPressing = 0;
	Image key;

	public SolarSystem(String title) {
		super(title);
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		g.drawImage(key, 4, 4);
		
		for(Entity var1 : entities){
			var1.render(container, g);
		}
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		container.setShowFPS(false);
		
		entities = new ArrayList<Entity>();
		entities.add(new Planet(new Image("res/sun.bmp"),new Vector2f(sunX - (float)72,sunY - (float)72),"sun",0,0,container,1F));
		
		entities.add(new Planet(new Image("res/mercury-mars.bmp"),new Vector2f(2,2),"Mercury",76.6F,0,container,false,0.2408213552361396F));// 0.39 - 4.6
		entities.add(new Planet(new Image("res/venus.bmp"),new Vector2f(2,2),"Venus",81.158F,0,container,false,0.61514031F));
		entities.add(new Planet(new Image("res/earth.bmp"),new Vector2f(2,2),"Earth",84.666667F,0,container,false,1));
		entities.add(new Planet(new Image("res/mercury-mars.bmp"),new Vector2f(2,2),"Mars",91.304F,0,container,false,1.88084873F));
		//asteroid field 
		entities.add(new Planet(new Image("res/jupiter.bmp"),new Vector2f(2,2),"Jupiter",137.9046666666667F,0,container,11.862F));
		entities.add(new Planet(new Image("res/saturn.bmp"),new Vector2f(2,2),"Saturn",192.8273333333333F,0,container,29.456F));
		entities.add(new Planet(new Image("res/uranus.bmp"),new Vector2f(2,2),"Uranus",314.9466666666667F,0,container,84.07F));
		entities.add(new Planet(new Image("res/neptune.bmp"),new Vector2f(2,2),"Neptune",430,0,container,164.81F));//30
		
		key = new Image("res/infos.bmp");
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		if(this.keyPressing > 0)
			SolarSystem.speedMultiplier += delta/10F;
		else if(this.keyPressing < 0 && SolarSystem.speedMultiplier != 0)
			SolarSystem.speedMultiplier -= delta/10F;
		
		if(SolarSystem.speedMultiplier < 0) SolarSystem.speedMultiplier = 0;
		
		for(Entity var1 : entities){
			var1.update(delta);
		}
	}
	
	@Override
	public void keyPressed(int key, char c){
		if(c == 'a') this.keyPressing = 1;
		else if(c == 'd') this.keyPressing = -1;
		switch(c){
		case 'a': 
			this.keyPressing = 1;
		break;
		case 'd': 
			this.keyPressing = -1;
		break;
		case 's':
			SolarSystem.speedMultiplier = DEFAULT_SPEEED;
			break;
		case 'i':
			Planet.insanity = !Planet.insanity;
		}
	}
	
	@Override
	public void keyReleased(int key, char c){
		if(c == 'a' || c == 'd') this.keyPressing = 0;
	}
	
	public static void main(String[] args) {
		System.out.println("starting");
		try {
			AppGameContainer app = new AppGameContainer(new SolarSystem("Solar System"), 1000, 900, false);
			app.start();
		} catch (SlickException e) {
			System.out.println(e.getMessage());
		}

	}

}
