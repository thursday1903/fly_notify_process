// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblServiceKeys.
 * @see .TblServiceKeys
 * @author Hibernate Tools
 */
@Stateless
public class TblServiceKeysHome {

	private static final Log log = LogFactory.getLog(TblServiceKeysHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblServiceKeys transientInstance) {
		log.debug("persisting TblServiceKeys instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblServiceKeys persistentInstance) {
		log.debug("removing TblServiceKeys instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblServiceKeys merge(TblServiceKeys detachedInstance) {
		log.debug("merging TblServiceKeys instance");
		try {
			TblServiceKeys result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblServiceKeys findById(BigDecimal id) {
		log.debug("getting TblServiceKeys instance with id: " + id);
		try {
			TblServiceKeys instance = entityManager.find(TblServiceKeys.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
