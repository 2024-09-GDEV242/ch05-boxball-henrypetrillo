
/**
 * BoxBall will create a canvas that draws a box with a specified number
 * of balls inside bouncing around.
 *
 * @author Henry Petrillo
 * @version 10/21/2024
 */

import java.awt.*;
import java.util.Random;

public class BoxBall
{
    // instance variables
    private int xPosition;
    private int yPosition;
    private int diameter;
    private Color color;
    private int xSpeed;
    private int ySpeed;
    private Canvas canvas;
    private int leftWall;
    private int rightWall;
    private int topWall;
    private int bottomWall;
    
    // New random
    private Random random = new Random();

    /**
     * Constructor for objects of class BoxBall.
     * 
     * @param boxLeft   The left boundary of the box.
     * @param boxRight  The right boundary of the box.
     * @param boxTop    The top boundary of the box.
     * @param boxBottom The bottom boundary of the box.
     * @param canvas    The canvas on which the ball will be drawn.
     */
    public BoxBall(int boxLeft, int boxRight, int boxTop, int boxBottom, Canvas canvas) {
        // Initializing walls and canvas.
        this.leftWall = boxLeft;
        this.rightWall = boxRight;
        this.topWall = boxTop;
        this.bottomWall = boxBottom;
        this.canvas = canvas;
        
        // Ensure the ball spawns fully inside the box, leaving enough margin for the diameter
        this.xPosition = random.nextInt((rightWall - leftWall - diameter)) + leftWall + (diameter / 2);
        this.yPosition = random.nextInt((bottomWall - topWall - diameter)) + topWall + (diameter / 2);
        
        final int MIN_SPEED = 3;
        do {
            this.xSpeed = random.nextInt(15) - 7;  // Random value between -7 and 7, excluding 0
            this.ySpeed = random.nextInt(15) - 7;  // Random value between -7 and 7, excluding 0
        } while (Math.abs(xSpeed) < MIN_SPEED || Math.abs(ySpeed) < MIN_SPEED);  // Ensure speeds are within threshold to not get stuck on wall

        // Sets the diameter between 10 - 30
        this.diameter = random.nextInt(20) + 10;
        
        // nextInt(201) ensures we aren't getting close to a potential white ball
        this.color = new Color(random.nextInt(201), random.nextInt(201), random.nextInt(201));  // RGB values between 0 and 200

    }
    
    /**
     * Sets the canvas foreground and draws the balls with given inputs.
     */
    public void draw() {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erases the ball from its current position. This is to create the
     * moving effect of the ball when it is repositioned when used before the
     * ball is redrawn.
     */
    public void erase() {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Moves the ball by updating its position based on the current speed (xSpeed and ySpeed).
     * The ball will "bounce" when it reaches the edges of the box, by reversing its direction
     * (inverting the speed). The ball is erased from its current position before moving,
     * and then redrawn at the new position.
     */
    public void move() {
        // Erase the ball's current position
        erase();
        
        // Update the ball's position by adding the current speed to x and y coordinates
        xPosition += xSpeed;
        yPosition += ySpeed;
        
        // Check if ball hits left or right wall and reverse xSpeed
        if (xPosition <= leftWall || xPosition >= rightWall - diameter) {
            xSpeed = -xSpeed;
        }
        
        // Check if ball hits top or bottom wall and reverse ySpeed
        if (yPosition <= topWall || yPosition >= bottomWall - diameter) {
            ySpeed = -ySpeed;
        }
        
        // Redraw the ball at its new position
        draw();
    }
    
    /**
     * Returns the current x-posititon of the ball.
     * @return the x-coordinate of the ball's current position
     */
    public int getXPosition() {
        return xPosition;
    }
    
    /**
     * Returns the current y-posititon of the ball.
     * @return the y-coordinate of the ball's current position
     */
    public int getYPosition() {
        return yPosition;
    }

}
