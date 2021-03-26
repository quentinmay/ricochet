package ricochet;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JPanel;


public class RicochetField extends JPanel {
//        private Point[] points = new Point[5];

        private Point2D.Double ballPosition = new Point2D.Double(0, 1);
        private int ballRadius = 5;
        private double deltaX;
        private double deltaY;
        private double distance_traveled_per_tic;
//        private Point nextPoint;
        private double pixPerSec;
        private boolean moving;
        
    public RicochetField() {
        //Constants. Just whatever value we want to give.
//        points[0] = new Point(960, 900);
//        points[1] = new Point(1660, 400);
//        points[2] = new Point(900, 100);
//        points[3] = new Point(560, 500);
//        ballPosition.setLocation(points[0].x, points[0].y);
//        nextPoint = new Point(points[1]);
    }
    
    
    public void paintComponent(Graphics g) {
        
        Computations comp = new Computations();
        super.paintComponent(g);
        //This for loop is only used to draw the Base ball diamond lines.
        g.setColor(Color.ORANGE);
        /*
        if (Math.sqrt(Math.pow((double)ballPosition.getX() - nextPoint.x, 2) + Math.pow((double)ballPosition.getY() - nextPoint.y, 2)) <= distance_traveled_per_tic) {
            //At this point, we are too close to the nextPoint, so we just goto the base and change our nextPoint to the next base.
            //System.out.println("too close to point. Just teleport to the spot then recalculate.");
            ballPosition.setLocation(nextPoint.x, nextPoint.y);
            for (int i = 0; i < points.length; i++) {
                    if (!nextPoint.equals(points[1])) {
                        if (ballPosition.getX() == points[0].x && ballPosition.getY() == points[0].y) {
                            //This only gets called once weve returned home.
//                            System.out.println("Returned home");
                            running = false;
                            g.fillOval((int)ballPosition.getX() - ballRadius, (int)ballPosition.getY() - ballRadius, ballRadius*2, ballRadius*2);
                            return;
                        }
                    }
                        if (running == true){
                            if(nextPoint.equals(points[i])) {
                                if (i == 3) nextPoint = new Point(points[0]);
                                else
                                    nextPoint = new Point(points[i+1]);
                                break;
                            }
                        }
                
            }
            
            g.fillOval((int)ballPosition.getX() - ballRadius, (int)ballPosition.getY() - ballRadius, ballRadius*2, ballRadius*2);
            double[] delta = comp.computeDelta(new Point((int)ballPosition.getX(), (int)ballPosition.getY()), nextPoint, pixPerSec);
            deltaX = delta[0];
            deltaY = delta[1];
        } else { //If the distance to next base ISNT lower than distance traveled per tic, then we just move normally.
            */
            ballPosition.setLocation(ballPosition.getX() + deltaX, ballPosition.getY() + deltaY);
            g.fillOval((int)ballPosition.getX() - ballRadius, (int)ballPosition.getY() - ballRadius, ballRadius*2, ballRadius*2);
//        }
            
    }
    
    //Utility used by our AppUI to check if the runner is still running.
    public boolean isMoving() {
        return moving;
    }
    
    //Call this function from the main class before running
    public void initializeBall(double pixPerSec, double locationX, double locationY, double refreshRate) {
            Computations comp = new Computations();
            this.moving = true;
            this.pixPerSec = pixPerSec;
            distance_traveled_per_tic = comp.distancePerTic(refreshRate, pixPerSec);
            this.ballPosition.setLocation(locationX, locationY);
            
            
//            double[] delta = comp.computeDelta(new Point((int)ballPosition.getX(), (int)ballPosition.getY()), nextPoint, pixPerSec);
//            System.out.println("rise:" + delta[1] + " / run:" + delta[0]);
//            deltaX = delta[0];
//            deltaY = delta[1];
            

    }
}
