// default package
// Generated Sep 8, 2020 6:03:31 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TblServiceBalMapping.
 * @see .TblServiceBalMapping
 * @author Hibernate Tools
 */
@Stateless
public class TblServiceBalMappingHome {

	private static final Log log = LogFactory.getLog(TblServiceBalMappingHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TblServiceBalMapping transientInstance) {
		log.debug("persisting TblServiceBalMapping instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TblServiceBalMapping persistentInstance) {
		log.debug("removing TblServiceBalMapping instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TblServiceBalMapping merge(TblServiceBalMapping detachedInstance) {
		log.debug("merging TblServiceBalMapping instance");
		try {
			TblServiceBalMapping result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TblServiceBalMapping findById(BigDecimal id) {
		log.debug("getting TblServiceBalMapping instance with id: " + id);
		try {
			TblServiceBalMapping instance = entityManager.find(TblServiceBalMapping.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
