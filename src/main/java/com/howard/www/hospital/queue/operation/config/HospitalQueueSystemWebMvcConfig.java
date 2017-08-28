package com.howard.www.hospital.queue.operation.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import com.howard.www.core.base.web.mvc.argument.IDataTransferObjectMethodArgumentResolver;
import com.howard.www.hospital.queue.operation.application.startup.HospitalQueueApplicationStartup;
/**
 * 
 * @ClassName:  SystemWebConfig   
 * @Description:TODO(这是提供MVC Java配置背后的配置的主要类。
 * 它通常通过添加@EnableWebMvc到应用程序@Configuration类来导入。
 * 另一种更高级的选项是直接从该类扩展，并根据需要重写方法，
 * 记住要添加@Configuration到子类和@Bean覆盖@Bean方法。
 * 有关详细信息，请参阅Javadoc @EnableWebMvc。)  
 * 如果希望可以继续使用WebMvcAutoConfiguration的自动配置,
 * 而只是需要修改或者增加MVC中的某些配置时,可以创建一个配置类,
 * 并继承于抽象类WebMvcConfigurerAdapter,
 * 可以通过实现抽象类的方法来注册控制器 
 * @author: mayijie
 * @date:   2017年8月21日 下午5:01:29   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Configuration
public class HospitalQueueSystemWebMvcConfig extends WebMvcConfigurationSupport {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueSystemWebMvcConfig.class);
    /**
     * 
     * <p>Title: addResourceHandlers</p>   
     * <p>Description: 覆盖此方法以添加用于提供静态资源的资源处理程序</p>   
     * @param registry   
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		log.info("execute addResourceHandlers(ResourceHandlerRegistry registry)");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(false)
				.addResolver(new GzipResourceResolver())
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
		super.addResourceHandlers(registry);
	}
	/**
	 * 
	 * <p>Title: addViewControllers</p>   
	 * <p>Description: Assists with the registration of simple automated controllers pre-configured with status code and/or a view.</p>   
	 * @param registry   
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		log.info("Assists with the registration of simple automated controllers pre-configured with status code and/or a view.");
		registry.addViewController("/hospital/queue/operation/consultation/room.exhibition").setViewName("/consultationRoom");
		registry.addViewController("/hospital/queue/operation/consultation/room.P1933.exhibition").setViewName("/consultationRoomP1933");
		registry.addViewController("/hospital/queue/operation/consultation/room.P1932.exhibition").setViewName("/consultationRoomP1932");
		super.addViewControllers(registry);
	}



	/**
	 * 
	 * <p>
	 * Title: addArgumentResolvers
	 * </p>
	 * <p>
	 * Description: 自定义的参数解析器除了那些依赖于注解的存在 （如内置解析器之前调用 @RequestParameter，
	 * 
	 * @PathVariable等等）。 后者可以通过RequestMappingHandlerAdapter直接配置进行定制 。
	 *                   </p>
	 * @param argumentResolvers
	 *            定制转换器列表; 最初是一个空的列表
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addArgumentResolvers(java.util.List)
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		log.info("execute addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)");
		argumentResolvers.add(iDataTransferObjectMethodArgumentResolver());
	}
	 
	
	
	@Bean(name = "iDataTransferObjectMethodArgumentResolver")  
	public IDataTransferObjectMethodArgumentResolver iDataTransferObjectMethodArgumentResolver() {
		IDataTransferObjectMethodArgumentResolver dataTransferObjectMethodArgumentResolver = new IDataTransferObjectMethodArgumentResolver();
		return dataTransferObjectMethodArgumentResolver;
	}
	
	@Bean
    public HospitalQueueApplicationStartup applicationStartListener(){
        return new HospitalQueueApplicationStartup();
    }
}
