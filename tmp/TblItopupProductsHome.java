// default package
// Generated May 14, 2020 4:02:14 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblItopupProducts.
 * @see .TblItopupProducts
 * @author Hibernate Tools
 */
@Stateless
public class TblItopupProductsHome {

	private static final Log log = LogFactory.getLog(TblItopupProductsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblItopupProducts transientInstance) {
		log.debug("persisting TblItopupProducts instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblItopupProducts persistentInstance) {
		log.debug("removing TblItopupProducts instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblItopupProducts merge(TblItopupProducts detachedInstance) {
		log.debug("merging TblItopupProducts instance");
		try {
			TblItopupProducts result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblItopupProducts findById(int id) {
		log.debug("getting TblItopupProducts instance with id: " + id);
		try {
			TblItopupProducts instance = entityManager.find(TblItopupProducts.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
