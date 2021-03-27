package ricochet;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JPanel;


public class RicochetField extends JPanel {
        private Point2D.Double ballPosition = new Point2D.Double(0, 1);
        private int ballRadius = 5;
        private double deltaX;
        private double deltaY;
        private double distance_traveled_per_tic;
        private double pixPerSec;
        private boolean moving;
        
    public RicochetField() {

    }
    
    
    public void paintComponent(Graphics g) {
        
        Computations comp = new Computations();
        super.paintComponent(g);
        g.setColor(Color.RED);
        
        int wall = 0;
        double changeX = 0;
        double changeY = 0;

        if ((ballPosition.x + deltaX + ballRadius) >= this.getSize().width) { //Ran into right wall.
            wall = 1;
            changeX = this.getSize().width - (ballPosition.x + ballRadius);
            changeY = (deltaY * changeX) / deltaX;
        }else if ((ballPosition.y + deltaY + ballRadius) >= this.getSize().height) { //Ran into bottom wall
            wall = 4;
            changeY = this.getSize().height - (ballPosition.y + ballRadius);
            changeX = (deltaX * changeY) / deltaY;
            
        } else if ((ballPosition.x + deltaX - ballRadius) <= 0) { //Ran into left wall
            wall = 3;
            changeX = -1 * (ballPosition.x - ballRadius);
            changeY = (deltaY * changeX) / deltaX;
        } else if ((ballPosition.y + deltaY - ballRadius) <= 0) { //Ran into top wall
            wall = 2;
            changeY =  -1 * (ballPosition.y - ballRadius);
            changeX = (deltaX * changeY) / deltaY;
        } else { //Didnt run into any walls
            ballPosition.setLocation(ballPosition.getX() + deltaX, ballPosition.getY() + deltaY);
            g.fillOval((int)(ballPosition.getX() - ballRadius), (int)(ballPosition.getY() - ballRadius), ballRadius*2, ballRadius*2); 
            return;
        }
            //get the point where it WOULD contact the wall and set the ball to there. So were technically teleporting to the wall.
            ballPosition.setLocation(ballPosition.getX() + changeX, ballPosition.getY() + changeY);
            g.fillOval((int)(ballPosition.getX() - ballRadius), (int)(ballPosition.getY() - ballRadius), ballRadius*2, ballRadius*2); 
            
            //At this point, we are too close to the wall, so we just change our deltas for the change in direction
            double[] delta = comp.computeDeltaAfterBounce(deltaX, deltaY, wall, this.distance_traveled_per_tic);
            deltaX = delta[0];
            deltaY = delta[1];
    }
    
    //Utility used by our AppUI to check if the ball is still moving.
    public boolean isMoving() {
        return moving;
    }
    
    //Call this function from the main class before running
    public void initializeBall(double pixPerSec, double locationX, double locationY, double refreshRate, double directionAngle) {
            Computations comp = new Computations();
            this.moving = true;
            this.pixPerSec = pixPerSec;
            distance_traveled_per_tic = comp.distancePerTic(refreshRate, pixPerSec);
            this.ballPosition.setLocation(locationX, locationY);
            double[] delta = comp.computeInitialDeltaPerTic(directionAngle, this.distance_traveled_per_tic);
            deltaX = delta[0];
            deltaY = delta[1];
            


            

    }
}
