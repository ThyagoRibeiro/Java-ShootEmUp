package game.entities.enemies.boss1;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

public class ActiveBoss1State implements EntityState {

	private Boss1 context;
	private boolean goingRight;
	private LocalTime shootTime;

	private double waitTime;

	public ActiveBoss1State(Boss1 context) {
		waitTime = 1000;
		this.context = context;
		shootTime = new LocalTime(Math.random() * waitTime);
		goingRight = true;
	}

	@Override
	public void render() {
		GameLib.setColor(context.getColor());
		GameLib.drawHourglass(context.getPosition().getX(), context
				.getPosition().getY(), context.getRadius());
	}

	@Override
	public void update() {
		float curX = context.getPosition().getX();
		float curY = context.getPosition().getY();
		
		//Se chegar no limite direito da tela
		if( (curX + context.getRadius()) >= GameLib.WIDTH){
			goingRight = false;
		}
		//Se chegar no limite esquerdo da tela
		if((curX - context.getRadius()) <= 0){
			goingRight = true;
		}
		
		if(goingRight){
			curX += context.getVelocity().getX()*6;
		}
		else{
			curX -= context.getVelocity().getX()*6;
		}
		context.setPosition(new Vector2D(curX, curY));	
		if (shootTime.hasEnded()) {
			context.shoot();
			shootTime.start(Math.random() * waitTime);
		}
	}
}
