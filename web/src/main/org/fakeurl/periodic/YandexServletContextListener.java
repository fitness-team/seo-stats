package main.org.fakeurl.periodic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 */
public class YandexServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread th = new Thread() {
            public void run() {
                while (!interrupted()){
                    System.out.println("---- test ----");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        };
        th.setDaemon(true);
        th.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // you could notify your thread you're shutting down if
        // you need it to clean up after itself
    }
}
