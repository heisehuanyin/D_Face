package dd.assistant.backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter{
	private PrintWriter logPort = null;
	private String defaultPath = "./slog.txt";
	
	public void initialOnce(String newPath) {
		if(newPath != null)
			this.defaultPath = newPath;
		try {
			this.logPort = new PrintWriter(new BufferedWriter(new FileWriter(this.defaultPath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void basicBufferFlush() {
		this.logPort.flush();
	}
	public void basicLogWrite(String msg) {
		this.logPort.println(msg);
	}
}