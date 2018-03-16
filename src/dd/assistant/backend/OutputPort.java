package dd.assistant.backend;

import java.io.BufferedWriter;
import java.io.IOException;

public class OutputPort {
	private LogWriter logPort = null;
	private BufferedWriter interactivePort = null;
	
	public void initial(ProcessBridge bridge) {
		this.logPort = bridge.getLogPort();
		this.interactivePort = bridge.getOutputPort();
	}
	public void basicBufferFlush() {
		this.logPort.basicBufferFlush();
		try {
			this.interactivePort.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void basicLogWrite(String msg) {
		this.logPort.basicLogWrite(msg + "\n");
	}
	//interactive list
	private void basicProcessWrite(String strmsg) {
		try {
			this.interactivePort.write(strmsg + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addTemple(String template_id, String name, String is_simple, String extend) {
		String cmdStr = CmdSymbo.ADD.TEMPLATE.getCmd(template_id, name, is_simple, extend);
		this.basicProcessWrite(cmdStr);
		this.logPort.basicLogWrite(cmdStr);
	}
	public void addBranch(String template_id, String branch_id, String name, String is_simple, String is_static) {
		String cmdStr = CmdSymbo.ADD.BRANCH.getCmd(template_id, branch_id, name, is_simple, is_static);
		this.basicProcessWrite(cmdStr);
		this.logPort.basicLogWrite(cmdStr);
	}
	public void addModule(String module_id, String name, String type) {
		String cmdStr = CmdSymbo.ADD.MODULE.getCmd(module_id, name, type);
		this.basicProcessWrite(cmdStr);
		this.logPort.basicLogWrite(cmdStr);
	}
	public void addRelation(String relation_id, String belongs_branch_id, String to_branch_id, String from_branch_id) {
		String cmdStr = CmdSymbo.ADD.RELATION.getCmd(belongs_branch_id, relation_id, from_branch_id, to_branch_id);
		this.basicProcessWrite(cmdStr);
		this.logPort.basicLogWrite(cmdStr);
	}
	public void fuzzyQuery(String id) {
		String cmdStr = CmdSymbo.QUERY.FUZZY.getCmd(id);
		this.basicProcessWrite(cmdStr);
		this.logPort.basicLogWrite(cmdStr);
	}
	public void fuzzyRemove(String id) {
		String cmdStr = CmdSymbo.REMOVE.FUZZY.getCmd(id);
		this.basicProcessWrite(cmdStr);
		this.logPort.basicLogWrite(cmdStr);
	}
	public void loadFile(String filepath) {
		if(filepath==null) {
			String cmdStr = CmdSymbo.SYSTEM.LOAD_FILE.getCmd(CmdSymbo._constraint.FILEPATH.V_DEFAULT, filepath);
			this.basicProcessWrite(cmdStr);
		}
		else {
			String cmdStr = CmdSymbo.SYSTEM.LOAD_FILE.getCmd(CmdSymbo._constraint.FILEPATH.V_MANUALLY, filepath);
			this.basicProcessWrite(cmdStr);
		}
	}
	public void tempPostCmd(String cmdline) {
		this.basicProcessWrite(cmdline);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogWriter w = new LogWriter();
		w.initialOnce(null);
		ProcessBridge pb = new ProcessBridge();
		pb.initial("./dataoperate.jar", w);
		OutputPort out = new OutputPort();
		out.initial(pb);
		InputPort in = new InputPort();
		in.initial(pb, null);
		in.loadPostPort(out);
		
		new Thread(in).start();
		
		out.loadFile(null);
		out.basicBufferFlush();
		
		out.addTemple("t_abcd", "t_name", "no", "none");
		out.basicBufferFlush();
		out.fuzzyQuery("t_abcd");
		out.basicBufferFlush();
		
		out.fuzzyRemove("t_abcd");
		out.basicBufferFlush();
		out.fuzzyQuery("t_abcd");
		out.basicBufferFlush();
		
		out.addTemple("t_abcd", "t_name", "no", "none");
		out.basicBufferFlush();
		out.fuzzyQuery("t_abcd");
		out.basicBufferFlush();
		
		
	}

}