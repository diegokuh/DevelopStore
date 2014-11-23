package mx.com.develop.store.controller;

import java.io.PrintWriter;

/**
 *
 * @author Curso
 */
public class AsyncTask implements Runnable{
    
    private PrintWriter writer;
    
    public AsyncTask(PrintWriter writer){
        this.writer = writer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Tarea asincrona ejecutandose");
                writer.print("Ciclo "+i);
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException ex) {
               
            }
    }
    }
}
