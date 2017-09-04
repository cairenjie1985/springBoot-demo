package me.caixin;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import me.caixin.filter.MyFilter;
import me.caixin.listener.MyApplicationListener;
import me.caixin.listener.MyListener;
import me.caixin.servlet.MyServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBootDemoApplication {


	//region servlet
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", "admin");
		reg.addInitParameter("loginPassword", "admin123");
		return reg;
	}
	//endregion

	//region filter

	/** * druid监控过滤 * @return */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean myFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.setName("myFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
	//endregion

	//region listener
	@Bean
	public ServletListenerRegistrationBean myListener(){
		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
		servletListenerRegistrationBean.setListener(new MyListener());
		servletListenerRegistrationBean.setName("myListener");
		return  servletListenerRegistrationBean;
	}
	//endregion

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootDemoApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run();
	}
}
