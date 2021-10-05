// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblBalChangeLog.
 * @see .TblBalChangeLog
 * @author Hibernate Tools
 */
@Stateless
public class TblBalChangeLogHome {

	private static final Log log = LogFactory.getLog(TblBalChangeLogHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblBalChangeLog transientInstance) {
		log.debug("persisting TblBalChangeLog instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblBalChangeLog persistentInstance) {
		log.debug("removing TblBalChangeLog instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblBalChangeLog merge(TblBalChangeLog detachedInstance) {
		log.debug("merging TblBalChangeLog instance");
		try {
			TblBalChangeLog result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblBalChangeLog findById(BigDecimal id) {
		log.debug("getting TblBalChangeLog instance with id: " + id);
		try {
			TblBalChangeLog instance = entityManager.find(TblBalChangeLog.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
