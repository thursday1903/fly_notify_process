// default package
// Generated May 14, 2020 4:02:14 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblDownloadPins.
 * @see .TblDownloadPins
 * @author Hibernate Tools
 */
@Stateless
public class TblDownloadPinsHome {

	private static final Log log = LogFactory.getLog(TblDownloadPinsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblDownloadPins transientInstance) {
		log.debug("persisting TblDownloadPins instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblDownloadPins persistentInstance) {
		log.debug("removing TblDownloadPins instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblDownloadPins merge(TblDownloadPins detachedInstance) {
		log.debug("merging TblDownloadPins instance");
		try {
			TblDownloadPins result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblDownloadPins findById(BigDecimal id) {
		log.debug("getting TblDownloadPins instance with id: " + id);
		try {
			TblDownloadPins instance = entityManager.find(TblDownloadPins.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
