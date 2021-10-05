package springboot.hbn.home;
// default package

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import springboot.hbn.entities.TblBills;
import springboot.hbn.entities.TblTransactions;

/**
 * Home object for domain model class TblBills.
 * 
 * @see .TblBills
 * @author Hibernate Tools
 */

public class TblBillsHome extends BaseHibernateHome {

	public List<Object> getPendingBillToProcess(int blocData) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblBills.class);

			Criterion billStt = Restrictions.eq("billStatus", 0);
			Criterion billType = Restrictions.eq("billType", 1);

			listTrans.add(billStt);
			listTrans.add(billType);
			// listTrans.setMaxResults(1);

			listTrans.setMaxResults(blocData);
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by example failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
	}

	public List<Object> getAllBills(TblBills tblBills) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblBills.class);
			Criterion name = Restrictions.eq("targetNumber", tblBills.getTargetNumber());
			Criterion billStt = Restrictions.eq("billStatus", tblBills.getBillStatus());
			Criterion billType = Restrictions.eq("billType", tblBills.getBillType());
			Criterion andExpireDate = Restrictions.gt("expireDate", new Date());
			listTrans.add(name);
			listTrans.add(billStt);
			listTrans.add(billType);
			listTrans.add(andExpireDate);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by example failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
	}

	public List<Object> getAllBillByFileName(TblBills tblBills) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblBills.class);
			Criterion name = Restrictions.eq("billFileName", tblBills.getBillFileName().trim());
			Criterion billStt = Restrictions.eq("billStatus", tblBills.getBillStatus());
			listTrans.add(name);
			listTrans.add(billStt);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by example failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
	}

	public Boolean isExistedFileNameAndPhone(ArrayList<TblBills> arrBills) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			for (TblBills tblBills2 : arrBills) {
				Criteria listTrans = session.createCriteria(TblBills.class);
				Criterion name = Restrictions.eq("billFileName", tblBills2.getBillFileName().trim());
				Criterion billStt = Restrictions.eq("billTargetName", tblBills2.getBillTargetName().trim());
				listTrans.add(name);
				listTrans.add(billStt);
				// listTrans.setMaxResults(1);
				@SuppressWarnings("unchecked")
				List<Object> lstBill = listTrans.list();
				if(lstBill.size()>0)
					return true;
				else continue;
			}
		} catch (Exception re) {
			log.fatal("find by example failed", re);
			// composeAlertAndSend(re);
			throw re;
		} finally {
			releaseSession(session);
		}
		return false;
	}
	
	public int importBatch(List<TblBills> lstBill) {
		// log.debug("persisting TblACategories instance");
		int finish = -1;
		Session session = null;
		Transaction trs = null;
		try {
			System.currentTimeMillis();
			session = HibernateUtil.getSessionFactory().openSession();
			System.currentTimeMillis();
			// log.info(">>>>>>>>>>>>>>>>>Total get connection="
			// + (finishProcess - preProcess));
			trs = session.beginTransaction();
			for (Object object : lstBill) {
				session.persist(object);
			}
			// log.debug("persist successful");
			trs.commit();
			finish = 0;
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			finish = -2;
			// TODO: handle exception
			trs.rollback();
		} catch (Exception re) {
			re.printStackTrace();
			// String object2Persist = GsonUltilities.toJson(transientInstance);
			log.fatal("persist failed", re);
			trs.rollback();
		} finally {
			releaseSession(session);
		}
		return finish;
	}
}
