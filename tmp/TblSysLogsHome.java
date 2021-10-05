// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblSysLogs.
 * @see .TblSysLogs
 * @author Hibernate Tools
 */
@Stateless
public class TblSysLogsHome {

	private static final Log log = LogFactory.getLog(TblSysLogsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblSysLogs transientInstance) {
		log.debug("persisting TblSysLogs instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblSysLogs persistentInstance) {
		log.debug("removing TblSysLogs instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblSysLogs merge(TblSysLogs detachedInstance) {
		log.debug("merging TblSysLogs instance");
		try {
			TblSysLogs result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblSysLogs findById(BigDecimal id) {
		log.debug("getting TblSysLogs instance with id: " + id);
		try {
			TblSysLogs instance = entityManager.find(TblSysLogs.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
