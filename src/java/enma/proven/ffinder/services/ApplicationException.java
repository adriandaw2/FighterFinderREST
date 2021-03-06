/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.services;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AMS
 */
@XmlRootElement
public class ApplicationException extends Exception {

    private String message;
    private String exceptionType;

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public ApplicationException(){
    }
    
    public ApplicationException(String message){
        this.message = message;
    }
    
    public ApplicationException(String message, Exception origin){
        this.message = message;
        this.exceptionType = origin.getClass().toString();
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
