package springboot.cache;

public enum QueueList {

	QUEUE_NOTIFY_BALANCE("QUEUE_NOTIFY"),
	QUEUE_NOTIFY_OTHER("QUEUE_NOTIFY_OTHER");

	private String hashName;

	QueueList(String hashName_) {
		this.hashName = hashName_;
	}

	public String getQueueName() {
		return hashName;
	}
}
