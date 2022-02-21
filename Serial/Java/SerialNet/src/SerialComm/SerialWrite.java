package SerialComm;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

public class SerialWrite extends Thread {
	OutputStream out;
	
	public SerialWrite(OutputStream out) {
		this.out = out;
	}
	
	public void send() 
	{
		String cmd = "02C005000000C503";
		
		try 
		{
			out.write(decodeUsingDataTypeConverter(cmd));
			out.flush();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public byte[] decodeUsingDataTypeConverter(String hexString) {
	    return DatatypeConverter.parseHexBinary(hexString);
	}
}

//https://www.baeldung.com/java-byte-arrays-hex-strings