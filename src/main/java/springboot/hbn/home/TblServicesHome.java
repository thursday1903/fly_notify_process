package springboot.hbn.home;
// default package

// Generated Oct 9, 2019 3:01:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import springboot.hbn.entities.TblServices;

/**
 * Home object for domain model class TblServices.
 * 
 * @see .TblServices
 * @author Hibernate Tools
 */

public class TblServicesHome extends BaseHibernateHome {

	public List<Object> findByServiceCode(TblServices tblServices) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblServices.class);
			Criterion name = Restrictions.eq("serviceCode", tblServices.getServiceCode());

			listTrans.add(name);
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
