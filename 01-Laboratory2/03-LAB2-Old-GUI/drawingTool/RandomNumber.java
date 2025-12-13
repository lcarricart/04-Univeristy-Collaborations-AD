/*******************************************************************************************************************
 * Objective of the class: Simple utility for generating random integers within a specified range.
 *******************************************************************************************************************
 * Context: This is part of a major programming project, where live telemetry is transmitted to a PC, to be later
   manipulated and displayed with a Java GUI application.
 *******************************************************************************************************************
 * Authors: 
 * 	- Luciano Carricart, https://github.com/lcarricart/
 * 	- Georgii Molyboga, https://github.com/Georgemolyboga/
 * Status: Information Engineering students, HAW Hamburg, Germany.
 * Date: November 2024
 *******************************************************************************************************************
 * Public methods:
 * 	- between() - Returns a random integer between min and max (inclusive)
 *******************************************************************************************************************/

package drawingTool;

public class RandomNumber {
    public static int between(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}