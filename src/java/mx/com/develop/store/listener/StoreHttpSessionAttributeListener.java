package mx.com.develop.store.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class StoreHttpSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        event.getName();//String EL nombre del atibuto que se agregó e la sesion
        event.getValue();// Object El valor del atributo que agregaron
        event.getSession();// HttpSession la sesion
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}