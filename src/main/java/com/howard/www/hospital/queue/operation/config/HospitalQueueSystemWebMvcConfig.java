package com.howard.www.hospital.queue.operation.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.base.web.mvc.argument.IDataTransferObjectMethodArgumentResolver;
import com.howard.www.hospital.queue.operation.application.startup.HospitalQueueApplicationStartup;
import com.howard.www.hospital.queue.operation.domain.BackInteractivenfoEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: SystemWebConfig
 * @Description:TODO(这是提供MVC Java配置背后的配置的主要类。
 *                           它通常通过添加@EnableWebMvc到应用程序@Configuration类来导入。
 *                           另一种更高级的选项是直接从该类扩展，并根据需要重写方法，
 *                           记住要添加@Configuration到子类和@Bean覆盖@Bean方法。
 *                           有关详细信息，请参阅Javadoc @EnableWebMvc。)
 *                           如果希望可以继续使用WebMvcAutoConfiguration的自动配置,
 *                           而只是需要修改或者增加MVC中的某些配置时,可以创建一个配置类,
 *                           并继承于抽象类WebMvcConfigurerAdapter, 可以通过实现抽象类的方法来注册控制器
 * @author: mayijie
 * @date: 2017年8月21日 下午5:01:29
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Configuration
public class HospitalQueueSystemWebMvcConfig extends WebMvcConfigurationSupport {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueSystemWebMvcConfig.class);

	/**
	 * 
	 * <p>
	 * Title: addResourceHandlers
	 * </p>
	 * <p>
	 * Description: 覆盖此方法以添加用于提供静态资源的资源处理程序
	 * </p>
	 * 
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
	 * <p>
	 * Title: addViewControllers
	 * </p>
	 * <p>
	 * Description: Assists with the registration of simple automated
	 * controllers pre-configured with status code and/or a view.
	 * </p>
	 * 
	 * @param registry
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		log.info(
				"Assists with the registration of simple automated controllers pre-configured with status code and/or a view.");
		registry.addViewController("/hospital/queue/operation/consultation/room.exhibition")
				.setViewName("/consultationRoom");
		registry.addViewController("/hospital/queue/operation/interrogation/spot.P1932.exhibition")
				.setViewName("/interrogationSpotP1932");
		registry.addViewController("/hospital/queue/operation/consultation/room.P1933.exhibition")
				.setViewName("/consultationRoomP1933");
		registry.addViewController("/hospital/queue/operation/interrogation/spot.P1934.exhibition")
		.setViewName("/interrogationSpotP1934");
		
		super.addViewControllers(registry);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter() {
			@Override
			protected void writeInternal(Object t, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {
				// TODO Auto-generated method stub
				ObjectMapper mapper = new ObjectMapper();
				BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
				backInteractivenfoEntity.setInteractiveData(mapper.writeValueAsString(t));
				outputMessage.getBody().write(
						FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity)).getBytes());
			}
		};
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		/**
		 * text/html;charset=UTF-8顺序不要写反了，否则IE会出现下载提示
		 * application/json;charset=UTF-8
		 */
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
		supportedMediaTypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
		converter.setSupportedMediaTypes(supportedMediaTypes);
		/**
		 * There was an unexpected error (type=Not Acceptable, status=406).Could
		 * not find acceptable representation
		 */
		return converter;
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		/**
		 * Content-Type:text/html;charset=UTF-8
		 */
		/**
		 * HttpMessageConverter即消息转换器机制 public ServletInputStream
		 * getInputStream() throws IOException; public ServletOutputStream
		 * getOutputStream() throws IOException;
		 */
		/**
		 * HttpMessageConverter接口提供了 5 个方法: canRead:判断该转换器是否能将请求内容转换成 Java 对象
		 * canWrite:判断该转换器是否可以将 Java 对象转换成返回内容 getSupportedMediaTypes:获得该转换器支持的
		 * MediaType 类型 read:读取请求内容并转换成 Java 对象 write:将 Java 对象转换后写入返回内容
		 */
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8")) {

			@Override
			protected void writeInternal(String str, HttpOutputMessage outputMessage) throws IOException {
				outputMessage.getHeaders().setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
				BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
				backInteractivenfoEntity.setInteractiveData(JSONArray.fromObject(str));
				log.info(FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity)));
				/**
				 * add by mayijie at 2017.09.06 需要重新计算
				 * Response Header 的 Content-Length 其实就是计算了buffer的数据长度
				 * 
				 * 重新设置 Content-Length import
				 */
				outputMessage.getHeaders().setContentLength(FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity)).getBytes().length);
				
				super.writeInternal(FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity)), outputMessage);
			}

		};
		return converter;
	}

	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub
		/**
		 * favorPathExtension表示支持后缀匹配，
		 * 属性ignoreAcceptHeader默认为fasle,表示accept-header匹配,defaultContentType开启默认匹配。
		 * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
		 */
		configurer.favorPathExtension(false);
		super.configureContentNegotiation(configurer);
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);

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
	public HospitalQueueApplicationStartup applicationStartListener() {
		return new HospitalQueueApplicationStartup();
	}

	/**
	 * ByteArrayHttpMessageConverter 数据与字节数组的相互转换 \/\ application/octet-stream
	 * StringHttpMessageConverter 数据与 String 类型的相互转换 text/\* text/plain
	 * FormHttpMessageConverter 表单与 MultiValueMap的相互转换
	 * application/x-www-form-urlencoded application/x-www-form-urlencoded
	 * SourceHttpMessageConverter 数据与 javax.xml.transform.Source 的相互转换 text/xml
	 * 和 application/xml text/xml 和 application/xml
	 * MarshallingHttpMessageConverter 使用 Spring 的 Marshaller/Unmarshaller 转换
	 * XML 数据 text/xml 和 application/xml text/xml 和 application/xml
	 * MappingJackson2HttpMessageConverter 使用 Jackson 的 ObjectMapper 转换 Json 数据
	 * application/json application/json MappingJackson2XmlHttpMessageConverter
	 * 使用 Jackson 的 XmlMapper 转换 XML 数据 application/xml application/xml
	 * BufferedImageHttpMessageConverter 数据与 java.awt.image.BufferedImage 的相互转换
	 * Java I/O API 支持的所有类型 Java I/O API 支持的所有类型
	 */
}
