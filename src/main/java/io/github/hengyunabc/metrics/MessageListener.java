package io.github.hengyunabc.metrics;

/**
 * not thread safe.
 * 
 * @author hengyunabc
 *
 */
public interface MessageListener {

	/**
	 * receive metrics message, not threadsafe.
	 * 
	 * @param jsonStringMessage message
	 */
	public void onMessage(String jsonStringMessage);
}
