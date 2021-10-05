// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblServices.
 * @see .TblServices
 * @author Hibernate Tools
 */
@Stateless
public class TblServicesHome {

	private static final Log log = LogFactory.getLog(TblServicesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblServices transientInstance) {
		log.debug("persisting TblServices instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblServices persistentInstance) {
		log.debug("removing TblServices instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblServices merge(TblServices detachedInstance) {
		log.debug("merging TblServices instance");
		try {
			TblServices result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblServices findById(int id) {
		log.debug("getting TblServices instance with id: " + id);
		try {
			TblServices instance = entityManager.find(TblServices.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
