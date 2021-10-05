package springboot.hbn.home;
// default package

import java.math.BigDecimal;
import java.util.Date;

// Generated Oct 9, 2019 3:01:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import springboot.config.Constant;
import springboot.config.ResponseCode;
import springboot.hbn.entities.TblAccounts;
import springboot.hbn.entities.TblBalChangeLog;
import springboot.hbn.entities.TblTransactions;
import springboot.service.entities.BalanceChangeResult;
import springboot.service.entities.SystemPaymentData;

/**
 * Home object for domain model class TblAccounts.
 * 
 * @see .TblAccounts
 * @author Hibernate Tools
 */

public class TblAccountsHome extends BaseHibernateHome {

	public List<Object> findUserByPassword(TblAccounts tblAccounts) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblAccounts.class);
			Criterion accIndentify = Restrictions.eq("accIndentify", tblAccounts.getAccIndentify());
			Criterion accStatus = Restrictions.eq("accStatus", tblAccounts.getAccStatus());
			Criterion accPassword = Restrictions.eq("loginPassword", tblAccounts.getLoginPassword().trim());

			listTrans.add(accIndentify);
			listTrans.add(accStatus);
			listTrans.add(accPassword);
			// listTrans.add(serviceType);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by findUserByPassword failed", re);

			throw re;
		} finally {
			releaseSession(session);
		}
	}

	/**
	 * Tim thong tin user by Email
	 * 
	 * @param tblAccounts
	 * @return
	 * @throws Exception
	 */
	public List<Object> findUserByEmail(TblAccounts tblAccounts) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblAccounts.class);
			// Criterion accIndentify = Restrictions.eq("accIndentify",
			// tblAccounts.getAccIndentify());
			Criterion accEmail = Restrictions.eq("accEmail", tblAccounts.getAccEmail());
			Criterion accStatus = Restrictions.eq("accStatus", tblAccounts.getAccStatus());

			listTrans.add(accEmail);
			// listTrans.add(accIndentify);
			listTrans.add(accStatus);
			// listTrans.add(serviceType);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by findUserByPassword failed", re);

			throw re;
		} finally {
			releaseSession(session);
		}
	}

	public List<Object> findUserByUsername(TblAccounts tblAccounts) throws Exception {
		// log.debug("finding TblACategories instance by example");

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria listTrans = session.createCriteria(TblAccounts.class);
			Criterion accIndentify = Restrictions.eq("accIndentify", tblAccounts.getAccIndentify());
			Criterion accStatus = Restrictions.eq("accStatus", tblAccounts.getAccStatus());

			listTrans.add(accIndentify);
			listTrans.add(accStatus);
			// listTrans.add(serviceType);
			// listTrans.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> listAcc = listTrans.list();
			return listAcc;
		} catch (Exception re) {
			log.fatal("find by findUserByPassword failed", re);

			throw re;
		} finally {
			releaseSession(session);
		}
	}

	/**
	 * Tru tien tai khoan cua doi tac
	 * 
	 * @param tblPartners
	 * @param requestAmount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized BalanceChangeResult debitPartnerBalance(TblAccounts tblAccounts,
			TblBalChangeLog tblBalChangeLog) {
		// List<Object> list =listAllObject(new TblProvince());

		BalanceChangeResult balanceChangeResult = new BalanceChangeResult();
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Tim kiem ds nhung order hop he dang can xu ly
			Criteria listPartner = session.createCriteria(TblAccounts.class);
			Criterion crePartnerId = Restrictions.eq("accId", tblAccounts.getAccId());
			listPartner.add(crePartnerId);
			listPartner.setLockMode(LockMode.PESSIMISTIC_WRITE);
			listPartner.setMaxResults(1);
			List<Object> lstpartner = listPartner.list();
			TblAccounts tblAccountsTmp = (TblAccounts) lstpartner.get(0);

			Long currentBudget = new Long(
					tblAccountsTmp.getBalance() == null ? new Long(0) : tblAccountsTmp.getBalance().longValue());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>CURR:" + currentBudget);
			tblBalChangeLog.setBalBef(new BigDecimal(currentBudget));
			balanceChangeResult.setBalBefore(currentBudget);
			Long finalBalance = new Long(currentBudget - tblBalChangeLog.getBalChangeAmount());

			if (finalBalance < 0) {
				log.info("TRU TIEN DOI TAC THAT BAI DO BUDGET < 0 HOAC HET HAN MUC:" + tblAccountsTmp.getAccIndentify()
						+ ",CUR:" + currentBudget + ",AMT:" + tblBalChangeLog.getBalChangeAmount());
				// currentBudget = new Long(0);
				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.BALANCE_IS_NOT_ENOUGH.getValue());
			} else {
				tblAccountsTmp.setBalance(new BigDecimal(finalBalance));
				// tblAccountsTmp.setBlockBalance(new
				// BigDecimal(currentHolding));
				tblAccountsTmp.setLatestUpdate(new Date());

				log.info("TRU TIEN DOI TAC:" + tblAccountsTmp.getAccIndentify() + ",\rSO DU TRUOC:" + currentBudget
						+ ",\rSO DU SAU:" + finalBalance + ",\rSO TIEN YC:" + tblBalChangeLog.getBalChangeAmount());

				session.update(tblAccountsTmp);

				balanceChangeResult.setBalAfter(finalBalance);
				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.TRANSACTION_SUCCESS.getValue());
			}
			transaction.commit();
			tblBalChangeLog.setBalAf(new BigDecimal(finalBalance));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>FN:" + finalBalance);
			tblAccounts = tblAccountsTmp;

		} catch (Exception e) {
			log.fatal("debitPartnerBalance() ", e);
			e.printStackTrace();
			// TODO: handle exception
			transaction.rollback();
			balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.FATAL_ERROR.getValue());
		} finally {
			releaseSession(session);
		}
		return balanceChangeResult;
	}

	/**
	 * Tru tien tai khoan cua doi tac
	 * 
	 * @param tblPartners
	 * @param requestAmount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized Boolean roolbackPartnerbalance(TblAccounts tblAccounts, TblTransactions tblTransactions) {
		// List<Object> list =listAllObject(new TblProvince());

		Boolean debitResult = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Tim kiem ds nhung order hop he dang can xu ly
			Criteria listPartner = session.createCriteria(TblAccounts.class);
			Criterion crePartnerId = Restrictions.eq("accId", tblAccounts.getAccId());
			listPartner.add(crePartnerId);
			listPartner.setLockMode(LockMode.PESSIMISTIC_WRITE);
			listPartner.setMaxResults(1);
			List<Object> lstpartner = listPartner.list();
			TblAccounts tblAccountsTmp = (TblAccounts) lstpartner.get(0);

			Long currentBudget = new Long(
					tblAccountsTmp.getBalance() == null ? new Long(0) : tblAccountsTmp.getBalance().longValue());
			tblTransactions.setAccBalBf(new BigDecimal(currentBudget));
			Long currentHolding = new Long(tblAccountsTmp.getBlockBalance() == null ? new Long(0)
					: tblAccountsTmp.getBlockBalance().longValue());
			Long finalBalance = currentBudget + tblTransactions.getRequestAmt().longValue();
			currentHolding -= tblTransactions.getRequestAmt().longValue();
			if (finalBalance < 0) {
				log.info("HOAN TIEN DOI TAC THAT BAI DO BUDGET < 0 HOAC HET HAN MUC:"
						+ tblAccountsTmp.getAccIndentify());
			} else {
				Date updateFlag = new Date();
				// String checkSum =
				// Bussiness.getInstance().calculateCheckSum(budget,
				// tblPartners.getPartnerId(), updateFlag);

				tblAccountsTmp.setBalance(new BigDecimal(finalBalance));
				tblAccountsTmp.setBlockBalance(new BigDecimal(currentHolding < 0 ? 0 : currentHolding));
				tblAccountsTmp.setLatestUpdate(new Date());

				log.info("HOAN TIEN DOI TAC:" + tblAccountsTmp.getAccIndentify() + ",\rSO DU TRUOC:" + currentBudget
						+ ",\rSO DU SAU:" + finalBalance + ",\rSO TIEN YC:" + tblTransactions.getRequestAmt()
						+ "\rGIAO DICH HOAN TIEN:" + tblTransactions.toString());
				tblTransactions.setAccBalAf(new BigDecimal(finalBalance));
				session.update(tblAccountsTmp);
				debitResult = true;
			}
			transaction.commit();

			tblAccounts = tblAccountsTmp;
		} catch (Exception e) {
			log.fatal("debitPartnerBalance() ", e);
			e.printStackTrace();
			// TODO: handle exception
			transaction.rollback();
		} finally {
			releaseSession(session);
		}
		return debitResult;
	}

	/**
	 * Tru tien tai khoan cua doi tac
	 * 
	 * @param tblPartners
	 * @param requestAmount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized BalanceChangeResult creditAccountbalance(TblAccounts tblAccounts,
			TblBalChangeLog tblBalChangeLog) {
		// List<Object> list =listAllObject(new TblProvince());
		Session session = null;
		Transaction transaction = null;
		BalanceChangeResult balanceChangeResult = new BalanceChangeResult();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Tim kiem ds nhung order hop he dang can xu ly
			Criteria listPartner = session.createCriteria(TblAccounts.class);
			Criterion crePartnerId = Restrictions.eq("accId", tblAccounts.getAccId());
			listPartner.add(crePartnerId);
			listPartner.setLockMode(LockMode.PESSIMISTIC_WRITE);
			listPartner.setMaxResults(1);
			List<Object> lstpartner = listPartner.list();
			TblAccounts tblAccountsTmp = (TblAccounts) lstpartner.get(0);

			Long currentBudget = new Long(
					tblAccountsTmp.getBalance() == null ? new Long(0) : tblAccountsTmp.getBalance().longValue());
			tblBalChangeLog.setBalBef(new BigDecimal(currentBudget));

			Long finalBalance = currentBudget + tblBalChangeLog.getBalChangeAmount();

			if (finalBalance < 0) {
				log.info("CONG TIEN DOI TAC THAT BAI DO BUDGET < 0 HOAC HET HAN MUC:"
						+ tblAccountsTmp.getAccIndentify());
				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.BALANCE_IS_NOT_ENOUGH.getValue());
			} else {
				Date updateFlag = new Date();
				// String checkSum =
				// Bussiness.getInstance().calculateCheckSum(budget,
				// tblPartners.getPartnerId(), updateFlag);

				tblAccountsTmp.setBalance(new BigDecimal(finalBalance));
				tblAccountsTmp.setLatestUpdate(new Date());

				log.info("CONG TIEN CHO DOI TAC:" + tblAccountsTmp.getAccIndentify() + ",\rSO DU TRUOC:" + currentBudget
						+ ",\rSO DU SAU:" + finalBalance + ",\rSO TIEN YC:" + tblBalChangeLog.getBalChangeAmount()
						+ "\rGIAO DICH HOAN TIEN:" + tblBalChangeLog.toString());
				tblBalChangeLog.setBalAf(new BigDecimal(finalBalance));
				session.update(tblAccountsTmp);

				String remark = tblBalChangeLog.getRemark();
				remark += "\r CONG TIEN USER AMT:" + finalBalance;
				tblBalChangeLog.setRemark(remark);

				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.TRANSACTION_SUCCESS.getValue());
			}
			transaction.commit();

		} catch (Exception e) {
			log.fatal("debitPartnerBalance() ", e);
			e.printStackTrace();
			// TODO: handle exception
			transaction.rollback();
			balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.FATAL_ERROR.getValue());
		} finally {
			releaseSession(session);
		}
		return balanceChangeResult;
	}

	/**
	 * Tăng số dư tạm giữ
	 * 
	 * @param tblPartners
	 * @param requestAmount
	 * @return
	 */
	public synchronized BalanceChangeResult addHoldingBalance(TblAccounts tblAccounts, TblBalChangeLog tblBalChangeLog,
			SystemPaymentData systemPaymentData) {
		// List<Object> list =listAllObject(new TblProvince());
		Boolean addResult = false;
		Session session = null;
		Transaction transaction = null;
		BalanceChangeResult balanceChangeResult = new BalanceChangeResult();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Tim kiem ds nhung order hop he dang can xu ly
			Criteria listPartner = session.createCriteria(TblAccounts.class);
			Criterion crePartnerId = Restrictions.eq("accId", tblAccounts.getAccId());
			listPartner.add(crePartnerId);
			listPartner.setLockMode(LockMode.PESSIMISTIC_WRITE);
			listPartner.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> lstpartner = listPartner.list();
			TblAccounts tblAccountsTmp = (TblAccounts) lstpartner.get(0);

			Long currentBalance = new Long(tblAccountsTmp.getBlockBalance().longValue());
			Long finalBlockBalance = currentBalance + tblBalChangeLog.getBalChangeAmount();
			String remark = tblBalChangeLog.getRemark();
			balanceChangeResult.setBalBefore(currentBalance);
			tblBalChangeLog.setBalBef(new BigDecimal(currentBalance));
//			if (finalBlockBalance <= tblAccountsTmp.getBalance().longValue() + systemPaymentData.getMoney_active()) {
			if (finalBlockBalance < 0) {
				remark = "CONG TIEN HOLDING BALANCE DOI TAC THAT BAI DO BUDGET < 0 HOAC HET HAN[" + finalBlockBalance
						+ "] MUC:" + tblAccountsTmp.getAccIndentify();
				log.info(remark);
				tblBalChangeLog.setRemark(remark.trim());
				tblBalChangeLog.setBalAf(new BigDecimal(finalBlockBalance));
				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.BALANCE_IS_NOT_ENOUGH.getValue());
			} else {
				tblAccountsTmp.setBlockBalance(new BigDecimal(finalBlockBalance));
				tblAccountsTmp.setLatestUpdate(new Date());

				session.update(tblAccountsTmp);

				remark = "CONG HOLDING BALANCE AMOUNT USER AMT:" + tblBalChangeLog.getBalChangeAmount()
						+ "\r block balBF:" + currentBalance + "\r block balAF:" + finalBlockBalance;
				tblBalChangeLog.setRemark(remark.trim());
				tblBalChangeLog.setBalAf(new BigDecimal(finalBlockBalance));
				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.TRANSACTION_SUCCESS.getValue());
				balanceChangeResult.setBalAfter(finalBlockBalance);
			}
			addResult = true;

			transaction.commit();
		} catch (Exception e) {
			log.fatal("debitPartnerBalance() ", e);
			e.printStackTrace();
			// TODO: handle exception
			transaction.rollback();
			balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.FATAL_ERROR.getValue());
		} finally {
			releaseSession(session);
		}
		return balanceChangeResult;
	}

	/**
	 * Xoa tam giu
	 * 
	 * @param tblPartners
	 * @param requestAmount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized BalanceChangeResult cleanHoldingBlance(TblAccounts tblAccounts,
			TblBalChangeLog tblBalChangeLog) {
		// List<Object> list =listAllObject(new TblProvince());
		Session session = null;
		Transaction transaction = null;
		BalanceChangeResult balanceChangeResult = new BalanceChangeResult();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Tim kiem ds nhung order hop he dang can xu ly
			Criteria listPartner = session.createCriteria(TblAccounts.class);
			Criterion crePartnerId = Restrictions.eq("accId", tblAccounts.getAccId());
			listPartner.add(crePartnerId);
			listPartner.setLockMode(LockMode.PESSIMISTIC_WRITE);
			listPartner.setMaxResults(1);
			List<Object> lstpartner = listPartner.list();
			TblAccounts tblAccountsTmp = (TblAccounts) lstpartner.get(0);
			String remark = tblBalChangeLog.getRemark();
			Long currentBlockBalance = new Long(tblAccountsTmp.getBlockBalance().longValue());
			tblBalChangeLog.setBalBef(new BigDecimal(currentBlockBalance));
			Long finalBlockBalance = currentBlockBalance - tblBalChangeLog.getBalChangeAmount();
			if (finalBlockBalance < 0) {
				log.info("TRU SO DU TAM GIU DOI TAC THAT BAI DO BUDGET < 0 HOAC HET HAN MUC:"
						+ tblAccountsTmp.getAccIndentify() + " Han muc hien tai:" + currentBlockBalance
						+ ",so tien can tru:" + tblBalChangeLog.getBalChangeAmount());
				remark = "TRU SO DU TAM GIU DOI TAC THAT BAI DO BUDGET < 0 HOAC HET HAN MUC:"
						+ tblAccountsTmp.getAccIndentify() + " Han muc hien tai:" + currentBlockBalance
						+ ",so tien can tru:" + tblBalChangeLog.getBalChangeAmount();
				balanceChangeResult
						.setBalChangeResult(ResponseCode.RpCode.HOLDING_BALANCE_CANBE_SUBSTRACT_TO_NEGATIVE.getValue());
			} else {
				tblAccountsTmp.setBlockBalance(new BigDecimal(finalBlockBalance));
				tblAccountsTmp.setLatestUpdate(new Date());

				log.info("XOA TAM GIU DOI TAC:" + tblAccountsTmp.getAccIndentify() + ",\rTAM GIU TRUOC:"
						+ currentBlockBalance + ",\rTAM GIU SAU:" + finalBlockBalance + ",\rSO TIEN YC:"
						+ tblBalChangeLog.getBalChangeAmount());

				remark = "XOA TAM GIU DOI TAC:" + tblAccountsTmp.getAccIndentify() + ",\rTAM GIU TRUOC:"
						+ currentBlockBalance + ",\rTAM GIU SAU:" + finalBlockBalance + ",\rSO TIEN YC:"
						+ tblBalChangeLog.getBalChangeAmount();
				session.update(tblAccountsTmp);
				balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.TRANSACTION_SUCCESS.getValue());
				tblBalChangeLog.setBalAf(new BigDecimal(finalBlockBalance));
			}

			tblBalChangeLog.setRemark(remark);
			transaction.commit();

			tblAccounts = tblAccountsTmp;
		} catch (Exception e) {
			log.fatal("debitPartnerBalance() ", e);
			e.printStackTrace();
			// TODO: handle exception
			transaction.rollback();

			balanceChangeResult.setBalChangeResult(ResponseCode.RpCode.FATAL_ERROR.getValue());
		} finally {
			releaseSession(session);
		}
		return balanceChangeResult;
	}

	public static void main(String[] args) {
		TblAccountsHome tblAccountsHome = new TblAccountsHome();
		TblAccounts tblAccounts = new TblAccounts();
		tblAccounts.setAccId(new BigDecimal(124));
		try {
			tblAccounts = (TblAccounts) tblAccountsHome.findById(new BigDecimal(123), tblAccounts);

			System.out.println("OBJ BEFORE:" + tblAccounts.toString());
			TblBalChangeLog tblBalChangeLog = new TblBalChangeLog();
//			 tblBalChangeLog.setBalChangeAmount(100000);
//			 tblAccountsHome.addHoldingBalance(tblAccounts, tblBalChangeLog);
			tblAccounts = (TblAccounts) tblAccountsHome.findById(tblAccounts.getAccId(), tblAccounts);
			System.out.println("OBJ AFF:" + tblAccounts.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
