package springboot.hbn.home;
// default package

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import springboot.hbn.entities.TblServiceKeys;
import springboot.hbn.entities.TblServices;

/**
 * Home object for domain model class TblServiceKeys.
 * 
 * @see .TblServiceKeys
 * @author Hibernate Tools
 */

public class TblServiceKeysHome extends BaseHibernateHome {

	public List<Object> findByServiceIdAndType(TblServiceKeys tblServiceKeys) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblServiceKeys.class);
			Criterion serviceId = Restrictions.eq("serviceId", tblServiceKeys.getServiceId());
			Criterion serviceType = Restrictions.eq("serviceType", tblServiceKeys.getServiceType());

			listTrans.add(serviceId);
			listTrans.add(serviceType);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by findByServiceCode failed", re);

			throw re;
		} finally {
			releaseSession(session);
		}

	}
}
