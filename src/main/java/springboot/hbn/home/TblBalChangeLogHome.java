package springboot.hbn.home;
// default package

// Generated Nov 4, 2019 5:30:30 PM by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import springboot.hbn.entities.TblBalChangeLog;
import springboot.utils.GsonUltilities;

/**
 * Home object for domain model class TblBalChangeLog.
 * 
 * @see .TblBalChangeLog
 * @author Hibernate Tools
 */

public class TblBalChangeLogHome extends BaseHibernateHome {

	public static void main(String[] args) {
		TblBalChangeLogHome tblBalChangeLogHome = new TblBalChangeLogHome();
		try {
			tblBalChangeLogHome.findObjecHaveFilter(20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TblBalChangeLog findObjecHaveFilter(Object objId) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Criteria crit = session.createCriteria(TblBalChangeLog.class);
			Criterion balanceLogId = Restrictions.eq("balanceLogId", Long.parseLong(objId + ""));

			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("balanceLogId"));
			projList.add(Projections.property("balChangeType"));
			projList.add(Projections.property("balChangeStatus"));
			projList.add(Projections.property("balChangeAmount"));
			projList.add(Projections.property("balBef"));
			projList.add(Projections.property("balAf"));
			projList.add(Projections.property("changeDate"));
			projList.add(Projections.property("acceptBy"));
			projList.add(Projections.property("createdBy"));
			projList.add(Projections.property("remark"));
			projList.add(Projections.property("authAt"));

			crit.setProjection(projList);
			crit.add(balanceLogId);

			@SuppressWarnings("unchecked")
			List<Object> results = crit.list();
			if (results.size() > 0) {
				Object[] objTmp = (Object[]) results.get(0);
				TblBalChangeLog tblBalChangeLog = new TblBalChangeLog();
				tblBalChangeLog.setBalanceLogId(Long.parseLong(objTmp[0] + ""));
				tblBalChangeLog.setBalChangeType((int) objTmp[1]);
				tblBalChangeLog.setBalChangeStatus((int) objTmp[2]);
				tblBalChangeLog.setCreatedBy(objTmp[8].toString());
				tblBalChangeLog.setBalChangeAmount((int) objTmp[3]);
				return tblBalChangeLog;
			}
		} catch (Exception re) {
			log.error("get failed", re);

			throw re;
		} finally {
			try {
				BaseHibernateHome.releaseSession(session);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	public int update(int status, long balanceLogId, String remark, String acceptBy, long balBf, long balAf) throws Exception {
		// log.debug("merging TblACategories instance");
		Session session = null;
		int finish = -1;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			// session = sessionFactory.getCurrentSession();
			// session
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("update TblBalChangeLog set balChangeStatus = " + status + ",remark = '"
					+ remark + "',authAt = sysdate,latestChange=sysdate,acceptBy='"+acceptBy+"',balBef="+balBf+",balAf="+balAf+"" + " where balanceLogId = " + balanceLogId + "");
			int result = query.executeUpdate();

			log.debug("merge successful");
			tx.commit();
			finish = 0;
		} catch (ConstraintViolationException e) {
			finish = -2;
			// TODO: handle exception
		} catch (Exception re) {
			log.fatal("merge failed[" + balanceLogId + "]", re);

			throw re;
		} finally {
			releaseSession(session);
		}
		return finish;
	}
}
