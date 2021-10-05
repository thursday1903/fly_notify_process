// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblAlertQueue.
 * @see .TblAlertQueue
 * @author Hibernate Tools
 */
@Stateless
public class TblAlertQueueHome {

	private static final Log log = LogFactory.getLog(TblAlertQueueHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblAlertQueue transientInstance) {
		log.debug("persisting TblAlertQueue instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblAlertQueue persistentInstance) {
		log.debug("removing TblAlertQueue instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblAlertQueue merge(TblAlertQueue detachedInstance) {
		log.debug("merging TblAlertQueue instance");
		try {
			TblAlertQueue result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblAlertQueue findById(BigDecimal id) {
		log.debug("getting TblAlertQueue instance with id: " + id);
		try {
			TblAlertQueue instance = entityManager.find(TblAlertQueue.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
