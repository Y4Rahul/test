package project;
import java.awt.*;
import java.awt.event.*;
import acm.program.*;
import acm.graphics.*;

public class Breakingball extends GraphicsProgram{
	
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;


/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** creating a global ball*/
	private GOval ball;
	
/** crating the paddle*/
	private GRect paddle;
	
/** testin for collision*/
	private GObject[] obstacle = new GObject[4];
	
/** velocities of the ball*/
	private double vx=5.0;
	private double vy=5.0;
	
/** checkin no.of turns*/	
	private int i=0;
	
/** constructing bricks*/	
	private GRect brick;
	
	public void init(){
		addMouseListeners();
	}

/** Runs the Breakout program. */

	public void run(){
	resize(WIDTH,HEIGHT);
	setup();
	while(i<NTURNS){
	gameon();
	}
	remove(ball);
	GLabel title = new GLabel("GAME OVER");
	add(title,(WIDTH-title.getWidth())/2,(HEIGHT-title.getAscent())/2);
}
	public void mouseMoved(MouseEvent e){
			if(e.getX()>paddle.getX() && paddle.getX()<WIDTH){
				paddle.move(8,0);
			}
			else{
				paddle.move(-8,0);
			}
	}
	
	private void setup(){
		for(int i=0;i<NBRICKS_PER_ROW;i++){
			for(int j=0;j<NBRICKS_PER_ROW;j++){
			 brick =new GRect(BRICK_WIDTH,BRICK_HEIGHT);
			 switch(i){
			 case 0:
				 brick.setFilled(true);
				 brick.setFillColor(Color.red);
				 break;
			 case 1:
				 brick.setFilled(true);
				 brick.setFillColor(Color.red);
				 break;
			 case 2:
				 brick.setFilled(true);
				 brick.setFillColor(Color.orange);
				 break;
			 case 3:
				 brick.setFilled(true);
				 brick.setFillColor(Color.orange);
				 break;
			 case 4:
				 brick.setFilled(true);
				 brick.setFillColor(Color.yellow);
				 break;
			 case 5:
				 brick.setFilled(true);
				 brick.setFillColor(Color.yellow);
				 break;
			 case 6:
				 brick.setFilled(true);
				 brick.setFillColor(Color.green);
				 break;
			 case 7:
				 brick.setFilled(true);
				 brick.setFillColor(Color.green);
				 break;
			 case 8:
				 brick.setFilled(true);
				 brick.setFillColor(Color.cyan);
				 break;
			 case 9:
				 brick.setFilled(true);
				 brick.setFillColor(Color.cyan);
				 break;
			 }
			 add(brick,(BRICK_WIDTH+BRICK_SEP)*j,BRICK_Y_OFFSET+(BRICK_SEP+BRICK_HEIGHT)*i);

			 }
			}
		//now for paddle
		
			paddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
			add(paddle,(WIDTH-PADDLE_WIDTH)/2,HEIGHT-PADDLE_Y_OFFSET);
			paddle.setFilled(true);
			paddle.setFillColor(Color.black);
			
			//now for ball
			
			ball = new GOval(BALL_RADIUS,BALL_RADIUS);
			ball.setFilled(true);
			ball.setFillColor(Color.black);
			add(ball,(WIDTH-BALL_RADIUS)/2,(HEIGHT-BALL_RADIUS)/2);
	}
	private void gameon(){
		while(ball.getY()<HEIGHT){
					if(ball.getY()>HEIGHT-PADDLE_Y_OFFSET){
					i++;
					remove(ball);
					add(ball,(WIDTH-BALL_RADIUS)/2,(HEIGHT-BALL_RADIUS)/2);
					break;
				}
		moveball();
		checkforcollison();
		getCollidingObject();
		pause(100);
		}
	}
	
	private void moveball(){
		ball.move(vx,vy);
	}
	private void checkforcollison(){
		//checking for right collison
		
		if(ball.getX()>WIDTH-BALL_RADIUS){
			vx=-vx;
			double diff = ball.getX() - (WIDTH -BALL_RADIUS); 
			ball.move(-2*diff,0);
		}
		//checkin for left collision
		
		if(ball.getX()<BALL_RADIUS){
			vx=-vx;
		}
		//checking for bottom collison
		
		if(ball.getY()>HEIGHT-BALL_RADIUS){
			vy=-vy;
			double diff = ball.getY()-(HEIGHT -BALL_RADIUS); 
			ball.move(0,-2*diff);
		}
		
		//checking for top collision
		
		if(ball.getY()<0){
			vy=-vy;
		}
	}
	
	private void getCollidingObject(){
		//GObject[] obstacle = new GObject[4];
		
			obstacle[0] = getElementAt(ball.getX(),ball.getY());
			obstacle[1] = getElementAt(ball.getX()+BALL_RADIUS,ball.getY());
			obstacle[2] = getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+BALL_RADIUS);
			obstacle[3] = getElementAt(ball.getX(),ball.getY()+BALL_RADIUS);
			//checking for paddle
			
			if(obstacle[3]==paddle){
				vy=-vy;
			}
			
			for(int i=0;i<4;i++){
					 if(obstacle[i]!=null){
						 vy=-vy;
						 if(obstacle[i]!=paddle){
						 remove(obstacle[i]);
						 break;
						 }
					 }
			}
	
	}
}
