package springboot.cache;

public enum HashList {

	 	HASH_SERVICE("HASH_ALL_SERVICES"), 
	 	HASH_SERVICE_KEYS("HASH_ALL_SERVICE_KEYS"), 
	 	HASH_ACCOUNTS("HASH_ACCOUNTS"), 
	    DEV("https://dev.domain.com:21323/");
	 
	    private String hashName;
	 
	    HashList(String hashName_) {
	        this.hashName = hashName_;
	    }
	 
	    public String getHashName() {
	        return hashName;
	    }
}
