/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectorientedfallingsand;

/**
 * organizes the particles in a relationship
 * @author Jade
 */
public class Relationship {
    
    Particle otherParticle;
    Particle newFirstParticle; 
    Particle newSecondParticle; 
   
    public Relationship(Particle inOtherParticle, Particle inNewFirstParticle, Particle inNewSecondParticle) {
        otherParticle = inOtherParticle;
        newFirstParticle = inNewFirstParticle;
        newSecondParticle = inNewSecondParticle;
    }
    
    //getters
    public Particle getOtherParticle() {
        return otherParticle;
    }
    
    //getters but they return clones
    public Particle getNewFirstParticle() {
        return newFirstParticle.cloneParticle();
    }
    public Particle getNewSecondParticle() {
        return newSecondParticle.cloneParticle();
    }
    
    
}
