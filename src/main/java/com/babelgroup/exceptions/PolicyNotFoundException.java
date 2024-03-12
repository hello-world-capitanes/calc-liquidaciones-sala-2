package com.babelgroup.exceptions;

public class PolicyNotFoundException extends Exception {
    public PolicyNotFoundException() {
        super("Poliza no encontrada para cubrir el daño.");
    }
}
