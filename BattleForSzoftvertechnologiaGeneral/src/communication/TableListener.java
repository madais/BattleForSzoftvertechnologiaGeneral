package communication;

import game.game.*;
import game.map.*;
/**
 * Interface for the gamemap message receiving.
 * Subscribers will be notfied through this method, if a new message has been received.
 * @author mdi
 *
 */
public interface TableListener {
	public void recieveTable(Map table);
}
