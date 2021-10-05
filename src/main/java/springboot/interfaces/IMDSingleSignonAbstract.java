package springboot.interfaces;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.data.redis.connection.RedisConnection;

import springboot.cache.HashList;
import springboot.cache.RedisCaching;
import springboot.cache.RedisDataConfig;
import springboot.config.MainConfig;
import springboot.config.ResponseCode;
import springboot.hbn.entities.TblAccounts;
import springboot.hbn.entities.TblServiceKeys;
import springboot.hbn.entities.TblServices;
import springboot.hbn.home.TblAccSerBalManagerHome;
import springboot.hbn.home.TblAccountsHome;
import springboot.hbn.home.TblServiceKeysHome;
import springboot.hbn.home.TblServicesHome;
import springboot.logs.Logs;
import springboot.service.entities.RequestResponseMsg;
import springboot.service.entities.SSOBaseObject;
import springboot.service.entities.SSOUserData;
import springboot.utils.Commons;
import springboot.utils.GsonUltilities;

public abstract class IMDSingleSignonAbstract {

	

}
