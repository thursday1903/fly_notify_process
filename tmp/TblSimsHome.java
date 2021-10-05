// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblSims.
 * @see .TblSims
 * @author Hibernate Tools
 */
@Stateless
public class TblSimsHome {

	private static final Log log = LogFactory.getLog(TblSimsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblSims transientInstance) {
		log.debug("persisting TblSims instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblSims persistentInstance) {
		log.debug("removing TblSims instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblSims merge(TblSims detachedInstance) {
		log.debug("merging TblSims instance");
		try {
			TblSims result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblSims findById(String id) {
		log.debug("getting TblSims instance with id: " + id);
		try {
			TblSims instance = entityManager.find(TblSims.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
