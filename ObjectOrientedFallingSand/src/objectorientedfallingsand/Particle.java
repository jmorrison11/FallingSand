package objectorientedfallingsand;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Particle
{
    // Fields go here.
    private String name;
    private Color color;
    private ArrayList<Movement> movements;
    private ArrayList<Relationship> relationships;
    
    // Constructor(s) go here.
    public Particle() {
        name = " ";
        color = Color.RED;   
        movements = new ArrayList();
        relationships = new ArrayList();
    }   
    
    // Methods go here.
    public void setColor(Color inColor) {
        color = inColor;
    }
    public void setName(String inName) {
        name = inName;
    } 
    
    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    
    public Particle cloneParticle() {
        Particle clone = new Particle();
        clone.setName(name);
        clone.setColor(color);
        clone.movements = movements;
        clone.relationships = relationships;
        return clone;
    }
    
    public void addMovement(Movement movement) {
        movements.add(movement);
    }
    
    public boolean isMoveable() {
        if(!movements.isEmpty()) {
        return true;        
        }
        else {
            return false;
        }
    }
    
    public Movement getRandomMovement() {
        int randomMovement = (int)(Math.random() * movements.size());
        Movement ranMovement = movements.get(randomMovement);
        return ranMovement;    
    }
    
    public void addRelationship(Relationship relationship) {
        relationships.add(relationship);
    }
    
    @Override
    public boolean equals(Object other) {
        Particle particle = (Particle) other;
        String otherName = particle.getName();
        if (otherName.equals(name)) {
            return true;
        }
        else {return false;}
    }
    public boolean hasRelationshipWith(Particle otherParticle) {
        for(int x = 0; x < relationships.size(); x++) {
            Relationship current = relationships.get(x);
            if(current.getOtherParticle().equals(otherParticle)) {
                return true;
            }
        }
        return false;
    }
    
    public Relationship getRelationshipWith(Particle otherParticle) {
        for(int x = 0; x < relationships.size(); x++) {
            Relationship current = relationships.get(x);
            if(current.getOtherParticle().equals(otherParticle)) {
                return current;
            }
        }
        return null;
    }
    
}
