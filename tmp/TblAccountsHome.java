// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblAccounts.
 * @see .TblAccounts
 * @author Hibernate Tools
 */
@Stateless
public class TblAccountsHome {

	private static final Log log = LogFactory.getLog(TblAccountsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblAccounts transientInstance) {
		log.debug("persisting TblAccounts instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblAccounts persistentInstance) {
		log.debug("removing TblAccounts instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblAccounts merge(TblAccounts detachedInstance) {
		log.debug("merging TblAccounts instance");
		try {
			TblAccounts result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblAccounts findById(BigDecimal id) {
		log.debug("getting TblAccounts instance with id: " + id);
		try {
			TblAccounts instance = entityManager.find(TblAccounts.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
