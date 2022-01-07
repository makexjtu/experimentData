package cn.waifutong.experimentData;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("cn.waifutong.experimentData.repository")
@EnableFeignClients
public class ExperimentDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExperimentDataApplication.class, args);
	}

}


/*
 * 为何要继承SpringBootServletInitializer，为何要实现configure这方法
 * 继承SpringBootServletInitializer可以使用外部tomcat(web容器启动项目)，自己可以设置端口号，项目名。
 * 继承之后要实现他的configure方法
 * 不需要用外部tomcat的话继承不继承都可以。
 */

class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(ExperimentDataApplication.class);
    }
}
