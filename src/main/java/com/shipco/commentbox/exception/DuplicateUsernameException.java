/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.exception;

/**
 *
 * @author San
 */
public class DuplicateUsernameException extends Exception{
    public DuplicateUsernameException(){
        super("Username aready have.");
    }
}
