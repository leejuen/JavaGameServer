package game.net.netty;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import game.net.netty.ServerInitializer;

/**
 * 
 * @author: leejun
 * @email: 619344879@qq.com
 * @description: Netty启动类
 * @version:0.0.1
 */
public class NettyServerStart {
	private static int port;
	public static ApplicationContext factory;

	public static void main(String[] args) throws Exception {
		//初始化日志配置
		DOMConfigurator.configureAndWatch("config/log4j.xml");
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		else {
			port = 8818;
		}
		run();
	}

	private static void run() throws Exception {
		//根据Spring获取配置信息，初始化Server参数
		factory = new FileSystemXmlApplicationContext("config/propholder.xml","config/spring.xml","config/spring-mybatis.xml");
		ServerInitializer initializer = (ServerInitializer) factory.getBean(ServerInitializer.class);

		NettyServer server = new NettyServer(port);
		server.setInitializer(initializer);
		server.run();
		System.out.println("server is running……");
	}
}