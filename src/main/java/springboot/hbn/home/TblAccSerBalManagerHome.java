package springboot.hbn.home;

/**
 * Home object for domain model class TblAccSerBalManager.
 * 
 * @see .TblAccSerBalManager
 * @author Hibernate Tools
 */

public class TblAccSerBalManagerHome extends BaseHibernateHome {

	/**
	 * Tim kiếm mapping account và service
	 * @param tblAccounts
	 * @return
	 * @throws Exception
	 */
//	public TblAccSerBalManager findByUserAndService(TblAccounts tblAccounts) throws Exception {
//		// log.debug("finding TblACategories instance by example");
//
//		Session session = null;
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			Criteria listTrans = session.createCriteria(TblAccounts.class);
//			Criterion accIndentify = Restrictions.eq("accId", tblAccounts.getAccId());
//			Criterion accStatus = Restrictions.eq("ownByServiceId", tblAccounts.getOwnByServiceId());
//
//			listTrans.add(accIndentify);
//			listTrans.add(accStatus);
//
//			listTrans.setMaxResults(1);
//			@SuppressWarnings("unchecked")
//			List<Object> listAcc = listTrans.list();
//			if (listAcc.size() > 0)
//				return (TblAccSerBalManager) listAcc.get(0);
//		} catch (Exception re) {
//			log.fatal("find by findUserByPassword failed", re);
//
//			throw re;
//		} finally {
//			releaseSession(session);
//		}
//		return null;
//	}
}
