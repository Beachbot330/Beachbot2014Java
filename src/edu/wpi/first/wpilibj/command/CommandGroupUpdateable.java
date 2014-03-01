/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.command;

/**
 * Removes check when adding adding a Command to a CommandGroup. This allows
 * Commands to be added in initialize.
 * @author Joe-XPS13-W7
 */
public class CommandGroupUpdateable extends CommandGroup {

    synchronized void validate(String message) {
        //do nothing. 
    }

    protected void initialize() {
        this.removeAllCommands();
        super.initialize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
