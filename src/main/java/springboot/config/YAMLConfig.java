package springboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@ContextConfiguration("file:./config/application.yml")
public class YAMLConfig {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	private String environment;
	private List<String> servers = new ArrayList<>();
	private List<String> schedule = new ArrayList<>();
	private List<String> whitelist_ip = new ArrayList<>();

	public List<String> getWhitelist_ip() {
		return whitelist_ip;
	}

	public void setWhitelist_ip(List<String> whitelist_ip) {
		this.whitelist_ip = whitelist_ip;
	}

	public List<String> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
	}

	private int listen_port;
	// standard getters and setters

	public int getListen_port() {
		return listen_port;
	}

	public void setListen_port(int listen_port) {
		this.listen_port = listen_port;
	}

	int time_to_reload;

	public int getTime_to_reload() {
		return time_to_reload;
	}

	public void setTime_to_reload(int time_to_reload) {
		this.time_to_reload = time_to_reload;
	}

	String card_url;
	String partner_name;
	String key_private_rsa;
	String key_softpin;

	public String getKey_softpin() {
		return key_softpin;
	}

	public void setKey_softpin(String key_softpin) {
		this.key_softpin = key_softpin;
	}

	public String getCard_url() {
		return card_url;
	}

	public void setCard_url(String card_url) {
		this.card_url = card_url;
	}

	public String getPartner_name() {
		return partner_name;
	}

	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	public String getKey_private_rsa() {
		return key_private_rsa;
	}

	public void setKey_private_rsa(String key_private_rsa) {
		this.key_private_rsa = key_private_rsa;
	}
}
