package springboot.hbn.home;
// default package

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import springboot.config.ResponseCode;
import springboot.hbn.entities.TblTransactions;
import springboot.utils.DateConvert;

/**
 * Home object for domain model class TblTransactions.
 * 
 * @see .TblTransactions
 * @author Hibernate Tools
 */

public class TblTransactionsHome extends BaseHibernateHome {

	public List<Object> findByTransId(TblTransactions tblMoneyTransferTransaction) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblTransactions.class);
			Criterion name = Restrictions.eq("requestId", tblMoneyTransferTransaction.getRequestId());
			listTrans.add(name);
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

	public List<Object> getTransactionDetailByDate(String fromdate, String todate, TblTransactions tblTransactions)
			throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblTransactions.class);
			Date fromDate = DateConvert.stringToDate(fromdate + " 00:00:00", "yyyy/MM/dd HH:mm:ss");
			Date toDate = DateConvert.stringToDate(todate + " 23:59:59", "yyyy/MM/dd HH:mm:ss");
			Criterion greaterthan = Restrictions.ge("createdDate", fromDate);
			Criterion lessthan = Restrictions.le("createdDate", toDate);
			Criterion transactionType = Restrictions.eq("transactionType", tblTransactions.getTransactionType());
			Criterion creAcc = Restrictions.eq("accId", tblTransactions.getAccId());
			Criterion creIsNotEmpt = Restrictions.isNotNull("targetNumber");
//			Criterion creTransStatus = null;
//			if (tblTransactions.getFinalStatus() != ResponseCode.RpCode.TRANSACTION_SUCCESS.getValue()
//					&& tblTransactions.getFinalStatus() != ResponseCode.RpCode.TRANSACTION_IS_PROCESSING.getValue()) {
//				creTransStatus = Restrictions.not(Restrictions.in("finalStatus", new Object[] { 200, 99 }));
//			} else {
//				creTransStatus = Restrictions.eq("finalStatus", tblTransactions.getFinalStatus());
//			}
//
//			listTrans.add(creTransStatus);
			listTrans.add(greaterthan);
			listTrans.add(lessthan);
			listTrans.add(transactionType);
			listTrans.add(creAcc);
			listTrans.add(creIsNotEmpt);
			listTrans.addOrder(Order.desc("createdDate"));
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();

			return listAcc;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			releaseSession(session);
		}
	}

	public List<Object> getAllPendingTransaction(String fromdate, String todate, TblTransactions tblTransactions)
			throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblTransactions.class);
			Date fromDate = DateConvert.stringToDate(fromdate + " 00:00:00", "yyyy/MM/dd HH:mm:ss");
			Date toDate = DateConvert.stringToDate(todate + " 23:59:59", "yyyy/MM/dd HH:mm:ss");
			Criterion greaterthan = Restrictions.ge("createdDate", fromDate);
			Criterion lessthan = Restrictions.le("createdDate", toDate);
			Criterion finalStatus = Restrictions.eq("finalStatus", tblTransactions.getFinalStatus());
			Criterion andTarget = Restrictions.isNotNull("targetNumber");

			listTrans.add(greaterthan);
			listTrans.add(lessthan);
			listTrans.add(finalStatus);
			listTrans.add(andTarget);
			listTrans.addOrder(Order.desc("createdDate"));
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();

			return listAcc;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			releaseSession(session);
		}
	}
}
