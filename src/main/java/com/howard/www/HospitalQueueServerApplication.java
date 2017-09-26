package com.howard.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @ClassName: HospitalQueueServerApplication
 * @Description:TODO
 * @author: mayijie
 * @date: 2017年8月23日 下午1:59:42
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
// @SpringBootApplication其包含@EnableAutoConfiguration和@ComponentScan注解,
// 可以自动扫描相关的自动配置类,从而实现自动配置功能的
// @SpringBootApplication(exclude={});
@SpringBootApplication
public class HospitalQueueServerApplication implements EmbeddedServletContainerCustomizer {
	private static final Logger logger = LoggerFactory.getLogger(HospitalQueueServerApplication.class);

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		ApplicationContext ctx = SpringApplication.run(HospitalQueueServerApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();

		for (String beanName : beanNames) {
			logger.info("Spring 容器中的Bean:" + ctx.getBean(beanName).getClass().getName());
		}

	}

	/**
	 * 
	 * <p>
	 * Title: customize
	 * </p>
	 * <p>
	 * Description:
	 * 在application.proerties中配置了端口无效所以在这里实现EmbeddedServletContainerCustomizer接口
	 * </p>
	 * 
	 * @param container
	 * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(9999);

	}
}
