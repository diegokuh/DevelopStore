/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.develop.store.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.develop.store.controller.RegistroCliente;
import mx.com.develop.store.model.Cliente;

/**
 *
 * @author Curso
 */
public class Test {
    public static void main(String[] args) {
        Class clase = Cliente.class;
        Field[] fields = clase.getDeclaredFields();
        Cliente cliente = new Cliente();
        for(Field field : fields){
            if(field.getName().equals("edad"))continue;
            try {
                String mayuscula = field.getName().substring(0, 1).toUpperCase();
                String resto = field.getName().substring(1);
                Method method = clase.getMethod("set"+mayuscula+resto, field.getType());
                method.invoke(cliente, "Prueba");
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println(cliente);
    }
}
