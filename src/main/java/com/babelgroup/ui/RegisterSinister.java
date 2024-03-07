package com.babelgroup.ui;

import com.babelgroup.PolicyNotFound;
import com.babelgroup.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RegisterSinister {



    public void registerSinister(Policy policy){
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

        //Daños -> Garantía afectada / Valor nuevo / Valor inicial / Antigüedad
        List<Damage> damageList = getDamages(policy);
    }

    private List<Damage> getDamages(Policy policy) {
        Scanner scanner = new Scanner(System.in);
        List<Damage> damageList = new ArrayList<>();
        boolean option = true;
        while(option){
            try {
                System.out.println("¿Quiere introducir un nuevo daño?");
                option = scanner.nextInt() != 1;
                if(!option){
                    break;
                }
                Damage damage = new Damage();
                System.out.print("Introduzca la valoración del daño: ");
                damage.setDamageCost(scanner.nextDouble());

                System.out.print("Introduzca el coste del bien dañado en el momento de su compra: ");
                damage.setInitialValue(scanner.nextDouble());

                System.out.print("Introduzca el coste de reponer el bien dañado: ");
                damage.setNewValue(scanner.nextDouble());

                System.out.print("Introduzca la antigüedad del bien dañado: ");
                damage.setAntiquity(scanner.nextInt());

                damage.setWarranty(getWarrantyFromPolicy(policy));
            } catch (PolicyNotFound e) {
                System.out.println(e.getMessage());
            }
        }
        return damageList;
    }

    private ProductWarranty getWarrantyFromPolicy(Policy policy) throws PolicyNotFound {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la garantía afectada por el daño");
        System.out.println("En caso de que no haya una garantía que cubra el daño, escriba 0");
        List<ProductWarranty> warranties = policy.getProduct().getProductWarranties();
        for(int i = 0; i < warranties.size(); i++){
            System.out.println(i + 1 + " - " + warranties.get(i).getWarranty().getName());
        }

        int option = scanner.nextInt();
        if (option == 0 || option > warranties.size()){
            throw new PolicyNotFound();
        }

        return warranties.get(option-1);
    }
}
