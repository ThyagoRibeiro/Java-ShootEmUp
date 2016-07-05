package game.entities.enemies.boss2;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

public class ActiveBoss2State implements EntityState {

	private Boss2 context;
	private boolean goingRight;
	private LocalTime shootTime;

	private double waitTime;

	public ActiveBoss2State(Boss2 context) {
		waitTime = 100;
		this.context = context;
		goingRight = true;
		shootTime = new LocalTime(Math.random() * waitTime);
		
	}

	@Override
	public void render() {

		GameLib.setColor(context.getColor());
		GameLib.drawDiamond(context.getPosition().getX(), context.getPosition()
				.getY(), context.getRadius());
		GameLib.drawCircle(context.getPosition().getX(), context.getPosition()
				.getY(), context.getRadius());
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
			curX += context.getVelocity().getX()*9;
		}
		else{
			curX -= context.getVelocity().getX()*9;
		}		
		
		context.setPosition(new Vector2D(curX, curY));			
		
		if (shootTime.hasEnded()) {
			context.shoot();
			shootTime.start(Math.random() * waitTime);
		}
	}
}
