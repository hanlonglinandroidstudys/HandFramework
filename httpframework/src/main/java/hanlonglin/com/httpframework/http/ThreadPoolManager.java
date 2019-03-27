package hanlonglin.com.httpframework.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }


    //1.把用户输入的任务添加到队列
    LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>();

    //添加任务
    public void excute(Runnable runnable) {
        if (runnable != null) {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //2.把队列中的任务放入到线程池执行
    ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        threadPoolExecutor.execute(MainRunnable);
    }

    //线程池拒绝处理
    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //r就是被线程丢出来还未完成的线程
            excute(r);
        }
    };

    //3.让1和2无限循环下去 大总管
    private Runnable MainRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable runnable=null;
                try {
                    runnable=queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(runnable!=null){
                    threadPoolExecutor.execute(runnable);
                }

            }
        }
    };


}
