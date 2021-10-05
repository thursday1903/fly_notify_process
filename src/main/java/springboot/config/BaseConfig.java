package springboot.config;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author bolide - Base case for config file that load configuration-properties
 *         from file.
 */
public class BaseConfig {
	protected String configPath = "conf/base_config.conf";
	protected boolean loadConfigResult = false;
	static RootConfig root = new RootConfig();
	protected Properties properties = new Properties();

	public boolean isLoadConfigResult() {
		return loadConfigResult;
	}

	public BaseConfig(String configPath) {
		this.configPath = configPath;
		loadProperties();
	}

	public void loadProperties() {
//		if (!loadConfigResult){
		try {
			FileInputStream propsFile = new FileInputStream(getFullConfigPath());
			properties.load(propsFile);
			propsFile.close();
			getAllParas();
			loadConfigResult = true;
		} catch (Exception e) {

		}
//		}
	}

	protected void getAllParas() {

	}

	protected String getFullConfigPath() {
		RootConfig root = new RootConfig();
		String fullConfigPath = root.getRoot() + configPath;
		// Debug.Info("fullConfigPath: " + fullConfigPath);
		return fullConfigPath;
	}

	public boolean getBoolProperty(String propName, boolean defaultValue) {
		try {
			if (properties.getProperty(propName).equalsIgnoreCase("true"))
				return true;
			else if (properties.getProperty(propName).equalsIgnoreCase("false"))
				return false;
			else
				return defaultValue;
		} catch (Exception e) {
			// TODO: handle exception
			return defaultValue;
		}
	}

	/**
	 * - Get config value (in integer) of key parameter
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getInt(String key, int defaultValue) {
		if (key == null || key.equals("")) {
			return defaultValue;
		}
		if (properties == null)
			return defaultValue;
		String value1 = properties.getProperty(key, "");
		value1 = value1.trim();
		if (value1.equals(""))
			return defaultValue;
		try {
			return Integer.parseInt(value1);
		} catch (NumberFormatException e) {

			return defaultValue;
		}
	}

	public float getFloat(String key, float defaultValue) {
		if (key == null || key.equals("")) {
			return defaultValue;
		}
		if (properties == null)
			return defaultValue;
		String value1 = properties.getProperty(key, "");
		value1 = value1.trim();
		if (value1.equals(""))
			return defaultValue;
		try {
			return Float.parseFloat(value1);
		} catch (NumberFormatException e) {

			return defaultValue;
		}
	}

	/**
	 * - Get config value (in Long) of key parameter
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long getLong(String key, long defaultValue) {
		if (key == null || key.equals("")) {
			return defaultValue;
		}
		if (properties == null)
			return defaultValue;
		String value1 = properties.getProperty(key, "");
		value1 = value1.trim();
		if (value1.equals(""))
			return defaultValue;
		try {
			return Long.parseLong(value1);
		} catch (NumberFormatException e) {

			return defaultValue;
		}
	}

	/**
	 * - Get config value (in integer) of key parameter
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public ArrayList<String> getList(String key) {
		ArrayList<String> listResult = new ArrayList<String>();
		if (key == null || key.equals("")) {
			return listResult;
		}
		if (properties == null)
			return listResult;
		String value1 = properties.getProperty(key, "");
		value1 = value1.trim();
		if (value1.equals(""))
			return listResult;

		try {
			String[] list = value1.split(",");
			String one = null;
			for (int i = 0; i < list.length; i++) {
				one = list[i];
				if (one.isEmpty()) {
					one = one.trim();
					if (one != "")
						listResult.add(one);
				}
			}

		} catch (Exception e) {

		}
		return listResult;
	}

}
