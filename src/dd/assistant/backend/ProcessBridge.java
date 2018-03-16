package dd.assistant.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProcessBridge{
	private LogWriter logPort = null;
	private Process exi = null;
	
	public void initial(String classFilePath, LogWriter logOut) {
		this.logPort = logOut;
		try {
			exi = Runtime.getRuntime().exec("java -jar " + classFilePath);
			this.logPort.basicLogWrite("SUCCESS:<OPEN>打开进程");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.logPort.basicLogWrite("ERROR:<OPEN>打开进程失败");
			this.logPort.basicBufferFlush();
		}
	}
	public LogWriter getLogPort(){
		return this.logPort;
	}
	
	public BufferedWriter getOutputPort() {
		return new BufferedWriter(new OutputStreamWriter(exi.getOutputStream()));
	}
	
	public BufferedReader getBufferedInputPort() {
		return new BufferedReader(new InputStreamReader(exi.getInputStream()));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
