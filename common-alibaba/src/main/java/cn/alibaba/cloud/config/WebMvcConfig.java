package cn.alibaba.cloud.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WebConfig <br/>
 * Function: WebMvc的配置 <br/>
 * date: 2018年5月18日 上午10:12:40 <br/>
 *
 * @author linz
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private UrlInterceptor urlInterceptor;



    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        // 创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        // 创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                // 格式化
                SerializerFeature.PrettyFormat,
                // 可解决long精度丢失 但会有带来相应的中文问题
                SerializerFeature.BrowserCompatible,
                // 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                // 是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                // 字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat,
                // List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty);
        // 处理中文乱码问题
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        // 将fastjson添加到视图消息转换器列表内
        return  fastJsonConverter;
    }

    //保证StringHttpMessageConverter在FastJsonHttpMessageConverter前被调用
    @Override
   public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        converters.add(converter);
//        converters.add(fastJsonHttpMessageConverters());
        int converter=converters.size();
        for (int i=0; i<converter;i++){
            if(converters.get(i) instanceof MappingJackson2HttpMessageConverter){
               converters.set(i,fastJsonHttpMessageConverters());
            }
            if(converters.get(i) instanceof StringHttpMessageConverter){
                converters.set(i,new StringHttpMessageConverter(Charset.forName("UTF-8")));
            }
        }
        converters.forEach(v->{
            System.out.println(v);
        });

   }


    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //	registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
    }

    /**
     * 注册登录用户信息解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大20MB
        factory.setMaxFileSize(DataSize.ofMegabytes(20));
        /// 设置总上传数据总大小50MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(50));
        return factory.createMultipartConfig();

    }

    /**
     * cors 跨域支持 可以用@CrossOrigin在controller上单独设置
     * ysp 移置网关统一处理
     */
    @Bean
    public CorsFilter corsFilter() {

        // 1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 放行哪些原始域
        config.addAllowedOrigin("*");
        // 是否发送Cookie信息
        config.setAllowCredentials(Boolean.TRUE);
        // 放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        // 放行哪些原始域(头部信息)
        config.addAllowedHeader("*");

        // 2.添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // 3.返回新的CorsFilter
        return new CorsFilter(source);

    }

}