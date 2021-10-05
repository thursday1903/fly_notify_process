// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblBills.
 * @see .TblBills
 * @author Hibernate Tools
 */
@Stateless
public class TblBillsHome {

	private static final Log log = LogFactory.getLog(TblBillsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblBills transientInstance) {
		log.debug("persisting TblBills instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblBills persistentInstance) {
		log.debug("removing TblBills instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblBills merge(TblBills detachedInstance) {
		log.debug("merging TblBills instance");
		try {
			TblBills result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblBills findById(BigDecimal id) {
		log.debug("getting TblBills instance with id: " + id);
		try {
			TblBills instance = entityManager.find(TblBills.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
