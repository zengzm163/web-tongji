package app.service.logpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.common.SpringHelper;
import app.ddl.Log;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.dsl.ProducerType;
/**
 * 日志缓冲池
 * @author Administrator
 *
 */
public class LogPool {
	
	private static LogPool logPool = null;
	
	private  RingBuffer<LogEvent> buffer ;
	
	private WorkerPool<LogEvent>  workerPool = null;
	
	private  ExecutorService executor = Executors.newCachedThreadPool();
	
	private LogPool() {
		//System.out.println("日志缓存队列开始启动");
		LogEventFactory eventFactory = new LogEventFactory();
		buffer = RingBuffer.create(ProducerType.SINGLE, eventFactory, 1024, new BlockingWaitStrategy());
		LogWorkerHandler[] handlers = new LogWorkerHandler[5];
        for(int i = 0; i < 5; i++){
            handlers[i] = new LogWorkerHandler();
        }
        workerPool = new WorkerPool<LogEvent>(buffer, buffer.newBarrier(),
                new IgnoreExceptionHandler(), handlers);
        Sequence[] sequences = workerPool.getWorkerSequences();
        buffer.addGatingSequences(sequences);
        workerPool.start(executor);
        //System.out.println("日志缓存队列开始启动完毕");
	}
	
	public static synchronized LogPool getInstance() {
		if(logPool == null) {
			logPool = new LogPool();
		}
		return logPool;
	}
	
	private class LogEventFactory implements EventFactory<LogEvent> {

		@Override
		public LogEvent newInstance() {
			return new LogEvent();
		}
		
	}
	
	private class LogWorkerHandler implements WorkHandler<LogEvent> {
		
		private LogService logService = (LogService)SpringHelper.getBean("logService");

		@Override
		public void onEvent(LogEvent logEvent) throws Exception {
			//System.out.println("处理日志，线程id：" + Thread.currentThread().getId());
			Log log = logEvent.getLog();
			logService.saveLog(log);
		}
	
	}
	
	/**
	 * 将日志丢进缓冲池
	 * @param log
	 */
	public void push(Log log) {
		//System.out.println("日志入列");
		long sequence = buffer.next();
        try{           
          LogEvent logEvent = buffer.get(sequence);
          logEvent.setLog(log);
        }finally{
            buffer.publish(sequence);
        }
	}
}
