package cn.alibaba.cloud.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@MapperScan(basePackages = {"cn.alibaba.cloud.mapper"})
@Configuration
public class MybatisConfig {
//    /**
//     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
//     */
//    @Bean
//    public SqlExplainInterceptor performanceInterceptor() {
//        return new SqlExplainInterceptor();
//    }
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
