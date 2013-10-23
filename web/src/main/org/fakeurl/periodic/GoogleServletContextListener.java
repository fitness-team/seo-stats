package main.org.fakeurl.periodic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: dimahum
 * Date: 10/23/13
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoogleServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread th = new Thread() {
            public void run() {
                while (!interrupted()){

                    System.out.println("---- google ----");
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
