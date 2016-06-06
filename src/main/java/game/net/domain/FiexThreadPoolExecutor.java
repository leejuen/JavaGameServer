package game.net.domain;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: netty额外给我们提供了两种线程池：
 *               MemoryAwareThreadPoolExecutor和OrderedMemoryAwareThreadPoolExecutor
 *               MemoryAwareThreadPoolExecutor确保jvm不会因为过多的线程而导致内存溢出错误
 *               OrderedMemoryAwareThreadPoolExecutor是前一个线程池的子类
 *               ，除了保证没有内存溢出之外，还可以保证 channel event的处理次序。
 * @version:0.0.2
 */
public class FiexThreadPoolExecutor extends ThreadPoolExecutor {
	private static Logger logger = LoggerFactory.getLogger(FiexThreadPoolExecutor.class);
	private String poolName;
	private static final int QUEUE_BASE = 100;
	private static final int MAX_BLOCK_ERR = 1000;

	public FiexThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveSecond, String poolName) {
		super(corePoolSize, maximumPoolSize, keepAliveSecond, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(QUEUE_BASE * corePoolSize), Executors.defaultThreadFactory());
		this.poolName = poolName;
		setRejectedExecutionHandler(new DiscardPolicy() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
				StackTraceElement stes[] = Thread.currentThread().getStackTrace();
				for (StackTraceElement ste : stes) {
					logger.warn(ste.toString());
				}
			}

		});
	}

	public FiexThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveSecond) {
		super(corePoolSize, maximumPoolSize, keepAliveSecond, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(QUEUE_BASE * corePoolSize));
		setRejectedExecutionHandler(new DiscardPolicy() {

			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
				StackTraceElement stes[] = Thread.currentThread().getStackTrace();
				for (StackTraceElement ste : stes) {
					logger.warn(ste.toString());
				}
			}

		});
	}

	@Override
	public void execute(Runnable command) {
		super.execute(command);
		if (super.getCorePoolSize() * QUEUE_BASE - this.getQueue().remainingCapacity() > MAX_BLOCK_ERR) {
			logger.error(poolName + " ThreadPool blocking Queue  size : "
					+ (super.getCorePoolSize() * QUEUE_BASE - this.getQueue().remainingCapacity()));
		}
	}

}
