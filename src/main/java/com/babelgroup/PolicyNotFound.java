package com.babelgroup;

public class PolicyNotFound extends Exception {
    public PolicyNotFound() {
        super("Poliza no encontrada para cubrir el daño.");
    }
}
