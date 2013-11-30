package pe.joedayz.javaee7.cdi.ejemplo23;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.logging.Logger;


@Transactional
public class CustomerService23 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private EntityManager em;
  @Inject
  private Logger logger;

  // ======================================
  // =           Public Methods           =
  // ======================================

  public void createCustomer(Customer23 customer) {
    em.persist(customer);
  }

  public Customer23 findCustomerById(Long id) {
    return em.find(Customer23.class, id);
  }

  @AroundInvoke
  private Object logMethod(InvocationContext ic) throws Exception {
    logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
    logger.severe(">>>" + ic.getTarget().toString() + " - " + ic.getMethod().getName());
    try {
      return ic.proceed();
    } finally {
      logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
      logger.severe("<<<" + ic.getTarget().toString() + " - " + ic.getMethod().getName());
    }
  }
}