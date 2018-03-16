package dd.assistant.foreend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dd.assistant.CBaseImplement;
import dd.assistant.backend.InputPort;
import dd.assistant.backend.LogWriter;
import dd.assistant.backend.OutputPort;
import dd.assistant.backend.ProcessBridge;

public class PAssistant extends CBaseImplement{
	private JFrame frame = new JFrame("D_assistant");
	private JMenuBar dockBar = new JMenuBar();
	private JTextArea outputArea = null;
	
	public PAssistant() {
		
	}
	
	public void initial() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		//JMenu
		JMenu af = new JMenu("FILE");
		JMenuItem ai = new JMenuItem("OPEN");
		ai.addActionListener(new CmdPost(null));
		af.add(ai);
		
		dockBar.add(af);
		frame.setJMenuBar(dockBar);
		
		
		outputArea = new JTextArea("Hello World!\n");
		frame.getContentPane().add(new JScrollPane(outputArea), BorderLayout.CENTER);
		
		JTextField cmdLine = new JTextField(50);
		JButton postCmd = new JButton("执行");
		JPanel down = new JPanel();
		down.setLayout(new FlowLayout(FlowLayout.RIGHT));
		down.add(cmdLine);
		down.add(postCmd);
		postCmd.addActionListener(new CmdPost(cmdLine));
		frame.getContentPane().add(down,BorderLayout.SOUTH);

        // 显示窗口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,600));
        frame.pack();
        frame.setVisible(true);
	}
	
	public JTextArea getOutoutPort() {
		return this.outputArea;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            		LogWriter w = new LogWriter();
            		w.initialOnce(null);
        			ProcessBridge pb = new ProcessBridge();
        			pb.initial("./dataoperate.jar", w);
        			OutputPort out = new OutputPort();
        			out.initial(pb);
        			
            		PAssistant instance = new PAssistant();
            		instance.loadPostPort(out);
            		instance.initial();

        			InputPort in = new InputPort();
        			in.loadPostPort(out);
        			in.initial(pb, instance.getOutoutPort());

        			new Thread(in).start();
            }
        });
	}

	class CmdPost implements ActionListener{
		private JTextField cmdline = null;
		
		public CmdPost(JTextField cmdline) {
			if(cmdline != null)
				this.cmdline = cmdline;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object s = arg0.getActionCommand();
			if(s.equals("OPEN")) {
				PAssistant.this.getCmdSelector().loadFile(null);
				PAssistant.this.flushBuffer();
			}
			else if(s.equals("执行")){
				if(this.cmdline != null){
					String cmdstr = this.cmdline.getText();
					PAssistant.this.getCmdSelector().tempPostCmd(cmdstr);
					PAssistant.this.flushBuffer();
				}
			}
		}
		
	}
}
