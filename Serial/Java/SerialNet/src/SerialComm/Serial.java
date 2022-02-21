package SerialComm;

import java.io.IOException;
import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class Serial {
	void connect(String port) {
		CommPortIdentifier com;
		CommPort commPort;
		SerialPort serialPort = null;
		
		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
		while(ports.hasMoreElements()) {
			CommPortIdentifier portCom = (CommPortIdentifier)ports.nextElement();
			String type;
			switch(portCom.getPortType()) {
			case CommPortIdentifier.PORT_PARALLEL:
				type = "Parallel";
				break;
			case CommPortIdentifier.PORT_SERIAL:
				type = "Serial";
				break;
			default:
				type = "Unknown";
				break;
			}
			System.out.println(portCom.getName() + ": " + type);
		}
		
		try {
			com = CommPortIdentifier.getPortIdentifier(port);
			
			
			if(com.isCurrentlyOwned()) System.out.println("Error : " + port + "포트를 사용중입니다.");
			else {
				commPort = com.open(this.getClass().getName(), 2000);
				if(commPort instanceof SerialPort) {
					serialPort = (SerialPort)commPort;
					serialPort.setSerialPortParams(19200,  SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); // 마지막 : 오류제어 비트
				}
				System.out.println("comport 성공");
			}

			
			//new SerialRead(serialPort.getInputStream()).start();
			new SerialWrite(serialPort.getOutputStream()).send();
			
		} catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
