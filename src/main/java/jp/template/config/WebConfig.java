package jp.template.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;

/**
 * Spring Web の設定。
 * 
 * @author hosomi.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * デフォルトサーブレット (HttpServletRequest) の挙動の設定。
	 * 
	 * @param configurer {@link DefaultServletHandlerConfigurer}
	 */
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		// 該当リソースが見つからない場合、DispatcherServlet -> デフォルトサーブレット (HttpServletRequest)経由でアクセスを試みる設定を有効。
		configurer.enable(); 
	}

	/**
	 * パスとビューの定義。
	 * <pre>
	 * ※基本、コントローラで定義のこと。
	 * ここでは SpringSecurity がらみのみ。
	 * </pre>
	 * 
	 * @param registry {@link ViewControllerRegistry}
	 * 
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName("forward:/login");
		registry.addViewController("/login").setViewName("login");
	}

	/**
	 * 静的リソースに対する定義。
	 * 
	 * @param registry {@link ResourceHandlerRegistry}
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// src/main/resources/static -> http://ip:port/context/static/
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

		// webjars の定義
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
				.resourceChain(false) // 自動で WebJarsResourceResolver が有効化される。
				.addResolver(new GzipResourceResolver()); // gz ファイルへのアクセス有効化。
	}

}
