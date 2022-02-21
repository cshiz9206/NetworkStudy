package SerialComm;

import javax.xml.bind.DatatypeConverter;

public class DataProc {
	String recvData;
	
	public DataProc(String recvData) 
	{
		this.recvData = recvData;
		print();
	}
	
	public void print()
	{
		System.out.println("DataProc : "+recvData);
	}
	
	public static String createPacketString(String command, int row, int col, String R, String G, String B) { // command : C0 or C2 / row, col : 두자리로(0-)
		String packetStr;
		String head = "02"; //STX
		String tail = "03"; //ETX
		
		String length = null;
		if(command.contentEquals("C0")) length = "05";
		else if(command.contentEquals("C2")) length = "07";
		
		String colStr = String.format("%02d", col);
		String rowStr = String.format("%02d", row);
		
		String part = command + length + colStr + rowStr + R + G + B;
		String checksum = calcChecksum(part); // EOR
		
		packetStr = head + part + checksum + tail;
		return packetStr;
	}
	
	public static String calcChecksum(String packetPart) {
		int resultDec = 0;
		for(int i = 0; i < packetPart.length() / 2; i++) {
			if(i == 0) resultDec = Integer.parseInt(packetPart.substring(0, 2), 16);
			resultDec ^= Integer.parseInt(packetPart.substring(i * 2, i * 2 + 2), 16);
		}
		String resultHex = Integer.toHexString(resultDec);
		if(resultHex.length() == 1) resultHex = "0" + resultHex;
		return resultHex;
	}
	
	public static byte[] decodeUsingDataTypeConverter(String hexString) {
	    return DatatypeConverter.parseHexBinary(hexString);
	}
}
