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
    File name: main.java
    Compile: javac main.java
    Purpose: This is the main file that just creates our AppUI.
*/
package ricochet;


public class main {


    public static void main(String[] args) {
        AppUI app = new AppUI();
    }
    
}
