package ricochet;

import java.awt.Point;



public class Computations {

    /*
    My algorithm is slightly different because I wasn't a fan of the given ones. Instead uses basic rise-run terminology.
    And once I calculate the rise:run, I get the rise:run where distance travelled per tic (C) = 1. Then I can multiply rise:run by given constant speed
    to get a distance travelled per tic that equals the given speed.
    */
    public double[] computeInitialDeltaPerTic(double directionAngle, double pixPerTic) {
        //polar coordinates to cartesian equation
        //x = r * cos( θ )
        //y = r * sin( θ )
        //Where r is distance traveled per tic, and theta is direction angle but needs to be in rads.
        
        double directionRads = (Math.PI / 180) * directionAngle;
        
        
        double deltaX = pixPerTic * Math.cos(directionRads);
        
        //Fix direction since our coordinate system starts at top left of grid. Only y should be reversed.
        double deltaY = -1 * pixPerTic * Math.sin(directionRads);
     
        return new double[]{deltaX, deltaY};
    }
    
    
    public double distancePerTic(double refreshRate, double pixPerSec) {
        return pixPerSec / refreshRate;
    } 
    
    public double[] computeDeltaAfterBounce(double deltaX, double deltaY, int wall, double pixPerSec) {
        //int wall (right = 1, top = 2, left = 3, bottom = 4)
        if (wall == 1 || wall == 3) { //wall is left or right
            return new double[] {(deltaX * -1), deltaY};
        } else  { //wall is top or bottom
            return new double[] {deltaX , (deltaY * -1)};
        }
        //hitting right wall should REVERSE deltaX.
        //hitting left wall should reverse deltaX.
        //Hitting top wall should reverse deltaY
        //Hitting bottom wall should reverse deltaY
    }
        
        
        
    
}
