/*
    Program name: "Ricochet". This program uses a simple UI to
    simulate a ball ricocheting of walls.
    Copyright (C) 2021  Quentin May

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

/*
Author information:
    Author: Quentin May
    Email: quentinemay@gmail.com, quentinemay@csu.fullerton.edu
*/

/*
Program information:
    Program name: Ricochet
    Programming language: Java
    Files: main.java, AppUI.java, Computations.java, RicochetField.java, run.sh
    Date project began: 2021-March-24
    Date of last update: 2021-March-28
    Status: Finished
    Purpose: This program simulates a ball rolling at constant speed ricocheting around table.
    Base test system: Linux system with Bash shell and openjdk-14-jdk
*/

/*
This Module:
    File name: Computations.java
    Compile: javac Computations.java
    Purpose: This is the class file that holds our algorithm to calculate the deltaX
    and deltaY necessary for the ricochet ball to continue at a constant speed down its path.
*/
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
