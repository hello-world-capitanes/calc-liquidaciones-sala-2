package com.babelgroup;

import com.babelgroup.model.Client;
import com.babelgroup.model.Policy;
import com.babelgroup.model.Sinister;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RegisterSinister {

    public void registerSinister(Client client){
        Sinister sinister = new Sinister();
        Scanner scanner = new Scanner(System.in);
        System.out.println("A continuación va a registrar un siniestro: ");

        //Fecha
        System.out.print("Introduzca la fecha del siniestro: ");
        sinister.setDate(new Date(scanner.next()));

        //Causa
        System.out.print("Introduzca la causa del siniestro: ");
        sinister.setCause(scanner.next());
        //Capital real
        System.out.print("Introduzca el capital real: ");
        sinister.setRealCapital(scanner.nextDouble());
        //Direccion
        System.out.print("Introduzca la dirección del siniestro: ");
        sinister.setAddress(scanner.next());

        //Poliza

        System.out.println("Seleccione una de las siguientes polizas");
        List<Policy> policyList = getPoliciesForClient();

        //Daños -> Garantía afectada / Valor nuevo / Valor inicial / Antigüedad
    }

    private List<Policy> getPoliciesForClient() {
        return null;
    }
}
