package top.flyfire.base.thread;

import top.flyfire.base.Pool;

public interface ThreadPool extends Pool<ThreadPool> {
	void execute();
}
