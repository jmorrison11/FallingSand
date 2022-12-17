package objectorientedfallingsand;

import java.awt.*;
import java.util.*;

public class SandLab
{
    //do not add any more fields
    private Particle[][] grid;
    private SandDisplay display;
    public static final int ROWS = 120;
    public static final int COLUMNS = 80;

    public static void main(String[] args)
    {
        SandLab lab = new SandLab(ROWS, COLUMNS);
        lab.run();
        
    }

    public SandLab(int numRows, int numCols)
    {
        //Make a particle Array
        Particle[] particles = new Particle[8];
        //Arrays.sort(particles);
        
        //making my particles
        Particle empty = new Particle();
        Particle sand = new Particle();
        Particle metal = new Particle();
        Particle water = new Particle();
        Particle cloud = new Particle();
        Particle gas = new Particle();
        Particle bug = new Particle();
        Particle frog = new Particle();
       
       //adding movement for particles
        Movement up = new Movement(-1, 0);
        Movement left = new Movement(0, -1);
        Movement down = new Movement(1, 0);
        Movement right = new Movement(0, 1);
       
        //making the grid
        grid = new Particle[numRows][numCols];
       
        //using my setters
        empty.setName("Empty");
        empty.setColor(Color.BLACK);       
        //naming them on the arraylist
        particles[0] = empty;
        sand.setName("Sand");
        sand.setColor(Color.YELLOW);
        //adding movement to some
        sand.addMovement(down);
        particles[1] = sand;
        metal.setName("Metal");
        metal.setColor(Color.GRAY);
        particles[2] = metal;
        water.setName("Water");
        water.setColor(Color.BLUE);
        particles[3] = water;
        water.addMovement(down);
        water.addMovement(left);
        water.addMovement(right);
        cloud.setName("Cloud");
        cloud.setColor(Color.WHITE);
        particles[4] = cloud;
        cloud.addMovement(left);
        cloud.addMovement(right);
        gas.setName("Gas");
        gas.setColor(Color.LIGHT_GRAY);
        particles[5] = gas;
        gas.addMovement(up);
        gas.addMovement(left);
        gas.addMovement(right);
        bug.setName("Bug");
        bug.setColor(Color.ORANGE);
        particles[6] = bug;
        bug.addMovement(up);
        bug.addMovement(left);
        bug.addMovement(right);
        bug.addMovement(down);
        frog.setName("Frog");
        frog.setColor(Color.GREEN);
        particles[7] = frog;
        frog.addMovement(up);
        frog.addMovement(down);
        
        //go through the grid and find all the empties
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++)
            {
                //label what are the empties
                Particle empties = new Particle();
                empties.setName("Empty");
                empties.setColor(Color.BLACK);
                grid[row][column] = empties;
            }
        }      
       
        //add relationships from the relationship class
        sand.addRelationship(new Relationship(empty, empty, sand));
        sand.addRelationship(new Relationship(water, water, sand));
        water.addRelationship(new Relationship(empty, empty, water));
        cloud.addRelationship(new Relationship(empty, empty, cloud));
        gas.addRelationship(new Relationship(empty, empty, gas));
        bug.addRelationship(new Relationship(empty, empty, bug));
        frog.addRelationship(new Relationship(empty, empty, frog));
        frog.addRelationship(new Relationship(frog, frog, empty));
        frog.addRelationship(new Relationship(bug, frog, frog));
        
       
        //particle that is set frog bug empty
        display = new SandDisplay("Falling Sand", numRows, numCols, particles);
    }

    //called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, Particle tool)
    {
        grid[row][col] = tool.cloneParticle();
    }

    //copies each element of grid into the display
    public void updateDisplay()
    {
        Particle particle;
        
        for (int row = 0; row < ROWS; row++)
        {
            for (int column = 0; column < COLUMNS; column++)
            {
                particle = grid[row][column];
                display.setColor(row, column, particle.getColor());
            }
        }
    }

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step()
    {
        int numRows = (int) (ROWS * Math.random());
        int numCols = (int) (COLUMNS * Math.random());
        Particle gridSpots = grid[numRows][numCols];
        //where can these particles move?
        if (gridSpots.isMoveable()) {
            Movement ranMove = gridSpots.getRandomMovement(); 
            int newRow = numRows + ranMove.getRowChange();
            int newColumn = numCols + ranMove.getColumnChange();
                   
            if (0 <= newRow && newRow < ROWS && 0 <= newColumn && newColumn < COLUMNS){
                Particle swapSpot = grid[newRow][newColumn];
                //grid[numRows][numCols] = swapSpot;
                //grid[newRow][newColumn] = gridSpots;
                
                //how should they move if there is a particle in the way?
                if(gridSpots.hasRelationshipWith(swapSpot) == true) {
                    Relationship relationship = gridSpots.getRelationshipWith(swapSpot);
                    grid[numRows][numCols] = relationship.getNewFirstParticle();
                    grid[newRow][newColumn] = relationship.getNewSecondParticle();
                }
            }
        }        
    }

    //do not modify
    public void run()
    {
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
            {
                step();
            }

            updateDisplay();
            display.repaint();
            display.pause(1);  //wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();

            if (mouseLoc != null)  //test if mouse clicked
            {
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
            }
        }
    }
}