package SerialComm;

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
}
