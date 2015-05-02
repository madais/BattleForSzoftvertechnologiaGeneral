
import java.io.IOException;

import communication.*;

public class ListenerTest implements MsgListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListenerTest tesztize=new ListenerTest();
		tesztize.test();
	}
	@Override
	public void recieveMsg(String msg) {
		// TODO Auto-generated method stub
		System.out.println("Uzenet: " + msg);
	}

	public void test(){

		try{
			Communication.start_server();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		
		Communication.subscribe_message(this);
		
		try{
			Thread.sleep(1000);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		Communication.send_message("Hellobello");
	}

}

