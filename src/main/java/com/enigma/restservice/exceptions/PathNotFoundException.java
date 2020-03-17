package com.enigma.restservice.exceptions;

public class PathNotFoundException extends ApplicationException {

    public PathNotFoundException(){
        super("Exception.path.not.found", ErrorCodes.PATH_NOT_FOUND);
    }
}
