package communication;

import java.io.IOException;

public class CommTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.out.println("kezd");
		
		if (args[0].equals("server")){
			Communication.start_server();
		}
		if (args[0].equals("client")){
			Communication.connect_server(args[1]);
		}
		
		
		while(true){
			try{
				Thread.sleep(10000);
				}
				catch (Exception ex){
					ex.printStackTrace();
				}
				
		}
		
	}

}
