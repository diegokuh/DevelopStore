/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.function;

/**
 *
 * @author Cursos Montoya
 */
public class CalcularFactura {

    public static double subtotal(double total){
        return total /1.16;
    }
    public static double iva(double total){
        return total - subtotal(total);
    }

}
