package mx.com.develop.store.listener;

import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Venta;

@WebListener()
public class StoreHttpSessionListener implements HttpSessionListener {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction ut;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Venta venta = (Venta) session.getAttribute("venta");
        if (venta != null) {
            Map<Producto, Integer> mapProductos = venta.getProductos();
            Set<Producto> productos = mapProductos.keySet();
            for (Producto p : productos) {
                Query q = em.createQuery("select p from Producto p where p=:producto");
                q.setParameter("producto", p);
                Producto prodPersist = (Producto) q.getSingleResult();
                prodPersist.setDisponibles(prodPersist.getDisponibles() + mapProductos.get(p));
                try {
                    ut.begin();
                    em.merge(prodPersist);
                    ut.commit();
                } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException ex) {
                }
            }
        }
    }
}
