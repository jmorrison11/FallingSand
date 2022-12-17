/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectorientedfallingsand;

/**
 * facillitates movement
 * @author Jade
 */
public class Movement {
    private int rowChange;
    private int columnChange;
    
    //constructor
    public Movement(int inRowChange, int inColumnChange) {
        rowChange = inRowChange;
        columnChange = inColumnChange;
    }
    //gets
    public int getRowChange() {
        return rowChange;
    }
    public int getColumnChange() {
        return columnChange;
    }
}
