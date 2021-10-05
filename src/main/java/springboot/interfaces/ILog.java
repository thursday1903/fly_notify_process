package springboot.interfaces;

public interface ILog {

	void setClass(Class clazz);
	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#info(java.lang.String)
	 */
	void info(String input);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#info(java.lang.String,
	 * java.lang.Throwable)
	 */
	void info(String input, Throwable e);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#fatal(java.lang.String)
	 */
	void fatal(String input);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#fatal(java.lang.String,
	 * java.lang.Throwable)
	 */
	void fatal(String input, Throwable e);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#debug(java.lang.String)
	 */
	void debug(String input);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#debug(java.lang.String,
	 * java.lang.Throwable)
	 */
	void debug(String input, Throwable e);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#error(java.lang.String)
	 */
	void error(String input);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#error(java.lang.String,
	 * java.lang.Throwable)
	 */
	void error(String input, Throwable e);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#trace(java.lang.String)
	 */
	void trace(String input);

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vnptepay.megav.logs.ILog#trace(java.lang.String,
	 * java.lang.Throwable)
	 */
	void trace(String input, Throwable e);


}