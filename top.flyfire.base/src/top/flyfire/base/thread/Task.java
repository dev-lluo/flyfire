package top.flyfire.base.thread;

public interface Task {
	boolean REP_EXEC = false;
	int INTERVAL = 1000;
	boolean getRepExec();
	void setRepExec(Boolean repExec);
}
