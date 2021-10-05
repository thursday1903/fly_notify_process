// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblTransactions.
 * @see .TblTransactions
 * @author Hibernate Tools
 */
@Stateless
public class TblTransactionsHome {

	private static final Log log = LogFactory.getLog(TblTransactionsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblTransactions transientInstance) {
		log.debug("persisting TblTransactions instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblTransactions persistentInstance) {
		log.debug("removing TblTransactions instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblTransactions merge(TblTransactions detachedInstance) {
		log.debug("merging TblTransactions instance");
		try {
			TblTransactions result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblTransactions findById(String id) {
		log.debug("getting TblTransactions instance with id: " + id);
		try {
			TblTransactions instance = entityManager.find(TblTransactions.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
