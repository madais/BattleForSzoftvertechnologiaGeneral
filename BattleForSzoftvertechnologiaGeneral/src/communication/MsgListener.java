package communication;
/**
 * Interface for the String based message receiving.
 * Subscribers will be notfied through this method, if a new message has been received.
 * @author mdi
 *
 */
public interface MsgListener {
	public void recieveMsg(String msg);
}
