import java.awt.*;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Graphics2D graphic;
    private Color backgroundColor = Color.WHITE;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Simulates multiple balls bouncing inside a defined rectangular box on the canvas.
     * The amount of balls is dependent on user input. Ball placement is randomly generated inside the box.
     * Balls bounce off the wall when making contact. The box is drawn once with the balls being redrawn each iteration.
     */
    public void boxBounce(int numBalls) {
        
        int boxLeft = 50;
        int boxRight = 550;
        int boxTop = 50;
        int boxBottom = 450;
        
        // Draw the box only once before simulation starts
        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.BLACK);
        drawBox(boxLeft, boxRight, boxTop, boxBottom);
        
        
        // Create balls
        BoxBall[] balls = new BoxBall[numBalls];
        for (int i = 0; i < numBalls; i++) {
            balls[i] = new BoxBall(boxLeft, boxRight, boxTop, boxBottom, myCanvas);
            balls[i].draw(); // Draw each ball individually
        }
        
        // Move balls
        boolean finished = false;
        while (!finished) {
            myCanvas.wait(50);
            
            // Clears canvas to avoid overlapping balls disappearing when
            // ontop of eachother.
            myCanvas.erase();
            
            // Redraw the box to ensure it remains intact
            drawBox(boxLeft, boxRight, boxTop, boxBottom);
            
            // Move all balls
            for (BoxBall ball : balls) {
                ball.move(); // Ball moves, but box stays intact
            }
        }
    }
    
    /**
     * drawBox method is used to redraw the box for each iteration to ensure chipping does not occur.
     */
    private void drawBox(int boxLeft, int boxRight, int boxTop, int boxBottom) {
        // Draw the box only once before the simulation starts
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(boxLeft, boxTop, boxRight, boxTop); // top
        myCanvas.drawLine(boxLeft, boxBottom, boxRight, boxBottom); // bottom
        myCanvas.drawLine(boxLeft, boxTop, boxLeft, boxBottom); // left
        myCanvas.drawLine(boxRight, boxTop, boxRight, boxBottom); // right
    }
}
