package mx.com.develop.store.listener;

import java.io.IOException;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class StoreAsyncListener implements AsyncListener{

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("Proceso asincrono terminado, enviando correo al usuario para notificar");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
    }
    
    
}
