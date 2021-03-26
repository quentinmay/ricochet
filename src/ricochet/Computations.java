package ricochet;

import java.awt.Point;



public class Computations {

    /*
    My algorithm is slightly different because I wasn't a fan of the given ones. Instead uses basic rise-run terminology.
    And once I calculate the rise:run, I get the rise:run where distance travelled per tic (C) = 1. Then I can multiply rise:run by given constant speed
    to get a distance travelled per tic that equals the given speed.
    */
    public double[] computeInitialDeltaPerTic(double directionAngle, double pixPerTIc) {
        //polar coordinates to cartesian equation
        //x = r * cos( θ )
        //y = r * sin( θ )
        //Where r is distance traveled per tic, and theta is direction angle but needs to be in rads.
        
        double directionRads = (Math.PI / 180) * directionAngle;
        
        Math.cos(directionRads)
        double deltaX = pix
     
        if (directionAngle > 90 || directionAngle < 270) {
            run = run * -1;
        }
        if (directionAngle > 0 || directionAngle < 180) {
            rise = 
        }
                

        double ratio = Math.sqrt(Math.pow(rise, 2) + Math.pow(run, 2)); //Ratio is the hypotenuse of the theoretical right triangle.
                                                                        //If we divide deltaX and deltaY by this, we get a hypotenuse
                                                                        //that = 1. then we just multiply our wanted speed to both.
                                                                        
        double deltaX = (run / ratio) * pixPerSec / 100; //the "/ 100" comes from the timer tickrate. We tick 100 times a second, so we divide pixPerSec by the tickrate to get the movement per tic
        double deltaY = (rise / ratio) * pixPerSec / 100;
        //deltaX and deltaY are now our rise and run assuming that we are traveling at our constant speed
        
        if (first.y > last.y) deltaY = deltaY * -1; //Shifts deltaY to be correct direction if were supposed to be going negative
        if (first.x > last.x) deltaX = deltaX * -1; //Shifts deltaX to be correct direction
        return new double[]{deltaX, deltaY};
    }
    
    
    public double distancePerTic(double refreshRate, double pixPerSec) {
        return pixPerSec / refreshRate;
    } 
    
    public double[] computeDeltaAfterBounce(double deltaX, double deltaY, int wall, double pixPerSec) {
        //int wall (right = 1, top = 2, left = 3, bottom = 4)
        if (wall == 1 || wall == 3) { //wall is left or right
            deltaX = deltaX * -1;
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
