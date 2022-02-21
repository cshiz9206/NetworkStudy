package SerialComm;

import java.io.IOException;
import java.io.OutputStream;

public class SerialWrite extends Thread {
	OutputStream out;
	
	public SerialWrite(OutputStream out) {
		this.out = out;
	}
	
	public void run() 
	{
		int move = 1;
		//while(true) {
			try 
			{
				//out.write(DataProc.decodeUsingDataTypeConverter(DataProc.createPacketString("C2", 1, 2 + move, "FF", "FF", "00")));
				out.write(DataProc.decodeUsingDataTypeConverter("02C2070102FFFF00C603"));
				out.flush();
			} catch (IOException e) {e.printStackTrace();}
			
//			try {
//				sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//}
	}
}

//https://www.baeldung.com/java-byte-arrays-hex-strings