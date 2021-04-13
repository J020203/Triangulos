/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author juanc
 */
public class Formulas {
    public void definirLados(boolean ctrl, Scanner input, int base, int y, int z) {
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
    }    
    public void clasificaTriangulos(int a, int b, int c, boolean[] tipoTriangulo) {
        String preRespuesta = "\nEl triángulo es de tipo ";        
        //Determina cual de los 3 tipos de triángulo es y lo guarda
        if (a == b && b == c) {
            System.out.println(preRespuesta + "equilátero.");
            tipoTriangulo[0] = true;
        }
        else if (a == b || b == c || c == a) {
            System.out.println(preRespuesta + "isósceles.");
            tipoTriangulo[1] = true;
        }
        else {
            System.out.println(preRespuesta + "escaleno.\n");
            tipoTriangulo[2] = true;
        }
    }
    public void medirAreaTriangulo(int a, int b, int c) {
        //Fórmula de Heron
        double semiPerimetro = (a + b + c)/2.0;
        double area = Math.round((Math.sqrt(semiPerimetro*(semiPerimetro - a)*(semiPerimetro - b)*(semiPerimetro - c))* 100.0))/100.0;
        System.out.println("\nEl área del triángulo es de " + area);
    }
    public void medirAreaTrianguloAltura(int base, int y, int z, double h, String preTipoTriangulo) {
        double[] alturaLados = alturasSegunBases(base, y, z);
        double area;
        if (h == alturaLados[0]) area = Math.round((base * h) * 100.0 / 2) / 100.0;
        else if (h == alturaLados[1]) area = Math.round((y * h) * 100.0 / 2) / 100.0;
        else area = Math.round((z * h) * 100.0 / 2) / 100.0;
        System.out.println("\nEl área del triángulo es de " + area);
    }
    public void medirAngulosTriangulos(int a, int b, int c, boolean[] tipoTriangulo) {
        //La suma de los ángulos de un triángulo debe ser igual a 180°
        //Teorema del coseno
        String caso; //Controlador del switch
        double[] angulos = teoremaCoseno(a, b, c); //Almacenará los 3 ángulos del triángulo                
        if (tipoTriangulo[0]) caso = "EQUILATERO";
        else if(tipoTriangulo[1]) caso = "ISOSCELES";
        else caso = "ESCALENO";
        
        switch(caso) {
            case "EQUILATERO":
                //Ya que todos sus lados son iguales sus ángulos también
                System.out.println("Todos los ángulos del triángulo equivalen a 60°");
                break;
            case "ISOSCELES":
                //Ya que dos lados son iguales, dos de sus angulos tambien lo serán            
                if (angulos[0] == angulos[1]) System.out.println("Los ángulos A y B corresponden a: " + angulos[0] + "°\nMientras que el ángulo C equivale a: " + angulos[2] + "°");
                else if (angulos[1] == angulos[2]) System.out.println("El ángulo A equivale a: " + angulos[0] + "°\nMientras que los ángulos B y C corresponden a: " + angulos[1] + "°");
                else if (angulos[0] == angulos[2]) System.out.println("Los ángulos A y C corresponden a: " + angulos[0] + "°\nMientras que el ángulo B equivale a: " + angulos[1] + "°");                               
                break;
            case "ESCALENO":
                //Ninguno de sus lados son iguales, sus angulos tampoco
                System.out.println("El ángulo A es: " + angulos[0] + "°\nEl ángulo B es: " + angulos[1] + "°\nEl ángulo C es: " + angulos[2] + "°"); 
                break;
        }
    }
    public double[] teoremaCoseno(int a, int b, int c) {
        double[] angulos = new double[3]; //Guardará los 3 ángulos del triángulo
        double cosA = Math.toDegrees(Math.acos((Math.pow(a, 2)-Math.pow(b, 2)-Math.pow(c, 2))/(-2*(b)*(c))));
        double cosB = Math.toDegrees(Math.acos((Math.pow(b, 2)-Math.pow(a, 2)-Math.pow(c, 2))/(-2*(a)*(c))));
        double cosC = Math.toDegrees(Math.acos((Math.pow(c, 2)-Math.pow(a, 2)-Math.pow(b, 2))/(-2*(a)*(b))));        
        //Redondeando con 2 decimales y se guardan las variables en angulos
        angulos[0] = Math.round(cosA * 100.0)/100.0;
        angulos[1] = Math.round(cosB * 100.0)/100.0;
        angulos[2] = Math.round(cosC * 100.0)/100.0;        
        return angulos; //Se usa esto para devolver las 3 variables y así puedan ser usadas más adelante
    }

    /**
     *
     * @param preTipoTriangulo
     * @param base
     * @param y
     * @param z
     * @return 
     */
    public void alturaEscaleno(String preTipoTriangulo, int base, int y, int z) {
        double[] alturasLados = alturasSegunBases(base, y, z);
        String preRespuesta = preTipoTriangulo.substring(0, 36) + "según ";        
        System.out.println(preRespuesta + base + " que es la base introducida es: " + alturasLados[0]);
        System.out.println(preRespuesta + y + " que es el lado introducido es: " + alturasLados[1]);
        System.out.println(preRespuesta + z + " que es el último lado introducido es: " + alturasLados[2]);
    }
    public double[] alturasSegunBases(int base, int y, int z) {
        double[] alturasLados = new double[3];
        double semiPerimetro = (base + y + z) / 2.0;
        double alturaBase = Math.round((2.0 / base) * (Math.sqrt(semiPerimetro*(semiPerimetro - base)*(semiPerimetro - y)*(semiPerimetro - z))* 100.0))/100.0;
        double alturaLadoY = Math.round((2.0 / y) * (Math.sqrt(semiPerimetro*(semiPerimetro - base)*(semiPerimetro - y)*(semiPerimetro - z))* 100.0))/100.0;
        double alturaLadoZ = Math.round((2.0 / z) * (Math.sqrt(semiPerimetro*(semiPerimetro - base)*(semiPerimetro - y)*(semiPerimetro - z))* 100.0))/100.0;
        alturasLados[0] = alturaBase;
        alturasLados[1] = alturaLadoY;
        alturasLados[2] = alturaLadoZ;
        return alturasLados;
    }
}