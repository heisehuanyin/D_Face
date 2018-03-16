package dd.assistant.backend;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

import dd.assistant.CBaseImplement;

public class InputPort extends CBaseImplement implements Runnable{
	private BufferedReader interactivePort = null;
	private JTextArea pout = null;
	
	public void initial(ProcessBridge bridge, JTextArea outputPanel) {
		this.interactivePort = bridge.getBufferedInputPort();
		this.pout = outputPanel;
	}
	
	private void LoopForGetResult() {
		String str1;
		try {
			while((str1 = this.interactivePort.readLine())!=null) {
				System.out.println(str1);
				if(this.pout != null)
					this.pout.append(str1 + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.flushBuffer();
		}
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.LoopForGetResult();
	}

}
