package SerialComm;

import java.io.IOException;
import java.io.InputStream;

public class SerialRead extends Thread {
	InputStream in;
	
	public SerialRead(InputStream in){
		this.in = in;
	}
	
	@Override
	public void run() 
	{
		//while(true) {
			byte[] buffer = new byte[1024];
			
			try {
				System.out.println(in.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
}
