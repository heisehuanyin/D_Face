package dd.assistant;

import dd.assistant.backend.*;

public interface CBaseInterface {
	void loadPostPort(OutputPort outPort);
	void writeLog_Err(String className, String msg);
	void writeLog_Warning(String className, String msg);
	void writeLog_Operate(String className,String supplement);
	void flushBuffer();
	OutputPort getCmdSelector();
}
