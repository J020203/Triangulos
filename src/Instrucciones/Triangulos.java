/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author juanc
 */
public class Triangulos {
    //Comprobar el tipo de triangulo a través de la entrada de la longitud de sus lados
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Formulas trf = new Formulas();
        boolean[] tipoTriangulo = new boolean[3];
        Arrays.fill(tipoTriangulo, false); //Coloca los controles del tipo triángulo como false; los cuales son dados en el siguiente orden: Equilatero, Isósceles y Escaleno
        
        double h = 0; //Altura
        int base = 0, y = 0, z = 0;
        boolean ctrl = true;
        
        System.out.println("Digite las 3 medidas de los lados del triángulo una por una:\n");
        while(ctrl){
            try {
                System.out.print("\tIntroduce la base: ");
                base = input.nextInt();
            } catch(InputMismatchException e){
                input.nextLine();
                System.out.println("Valor invalido.\n");
                continue;
            }
            while (ctrl){
                try {
                    System.out.print("\tIntroduce un lado: ");
                    y = input.nextInt();
                } catch(InputMismatchException e){
                    input.nextLine();
                    System.out.println("Valor invalido.\n");
                    continue;
                }
                while (ctrl){
                    try {
                        System.out.print("\tIntroduce el último lado: ");
                        z = input.nextInt();
                        ctrl = false;
                    } catch(InputMismatchException e){
                        input.nextLine();
                        System.out.println("Valor invalido.\n");
                    }
                }
            }            
        }
        String respuesta;
        while(true) {
            System.out.print("\nSi tiene la altura del triángulo digite (Y), sino es el caso digite (N): ");
            respuesta = input.next().toUpperCase();
            if ("Y".equals(respuesta) || "N".equals(respuesta)) break;
            else System.out.println("Digite una opción valida");
        }
        trf.clasificaTriangulos(base, y, z, tipoTriangulo);
        String preTipoTriangulo = "La altura recomendada del triángulo es de: ";
        if (!"Y".equals(respuesta)) trf.medirAreaTriangulo(base, y, z);
        else {
            while(true) {
                if (tipoTriangulo[0]) {
                    double altura = Math.round((Math.sqrt(3) * 100.0 * base)/2) / 100.0;
                    System.out.println(preTipoTriangulo + altura + "\n");
                } else if (tipoTriangulo[1]) {
                    double altura = Math.round(Math.sqrt(Math.pow(y, 2) - (Math.pow(base, 2)) / 4) * 100.0) / 100.0;
                    System.out.println(preTipoTriangulo + altura + "\n");
                } else {
                    //Al ser escaleno posee tres alturasSegunBases diferentes correspondientes al lado que se tome como base
                    trf.alturaEscaleno(preTipoTriangulo, base, y, z);
                }
                System.out.print("\nIngrese la altura del triángulo: "); //La altura del triangulo depende de su tipo
                try {
                    h = input.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println("Ingrese un valor valido.\n");
                }
            }
            trf.medirAreaTrianguloAltura(base, y, z, h, preTipoTriangulo);
        }
        trf.medirAngulosTriangulos(base, y, z, tipoTriangulo);
    }
}