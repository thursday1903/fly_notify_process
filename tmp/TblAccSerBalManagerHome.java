// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblAccSerBalManager.
 * @see .TblAccSerBalManager
 * @author Hibernate Tools
 */
@Stateless
public class TblAccSerBalManagerHome {

	private static final Log log = LogFactory.getLog(TblAccSerBalManagerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblAccSerBalManager transientInstance) {
		log.debug("persisting TblAccSerBalManager instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblAccSerBalManager persistentInstance) {
		log.debug("removing TblAccSerBalManager instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblAccSerBalManager merge(TblAccSerBalManager detachedInstance) {
		log.debug("merging TblAccSerBalManager instance");
		try {
			TblAccSerBalManager result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblAccSerBalManager findById(BigDecimal id) {
		log.debug("getting TblAccSerBalManager instance with id: " + id);
		try {
			TblAccSerBalManager instance = entityManager.find(TblAccSerBalManager.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
