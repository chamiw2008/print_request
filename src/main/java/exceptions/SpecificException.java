/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Data
 */
public class SpecificException extends Exception {

    //Parameterless
    public SpecificException() {
    }

    //Constructor that accepts a message
    public SpecificException(String message) {
        super(message);
    }
}
