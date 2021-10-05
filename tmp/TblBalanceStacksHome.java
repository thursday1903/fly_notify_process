// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblBalanceStacks.
 * @see .TblBalanceStacks
 * @author Hibernate Tools
 */
@Stateless
public class TblBalanceStacksHome {

	private static final Log log = LogFactory.getLog(TblBalanceStacksHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblBalanceStacks transientInstance) {
		log.debug("persisting TblBalanceStacks instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblBalanceStacks persistentInstance) {
		log.debug("removing TblBalanceStacks instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblBalanceStacks merge(TblBalanceStacks detachedInstance) {
		log.debug("merging TblBalanceStacks instance");
		try {
			TblBalanceStacks result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblBalanceStacks findById(BigDecimal id) {
		log.debug("getting TblBalanceStacks instance with id: " + id);
		try {
			TblBalanceStacks instance = entityManager.find(TblBalanceStacks.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
