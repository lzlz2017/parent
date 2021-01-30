package cn.alibaba.cloud.util;

import cn.alibaba.cloud.sso.ISsoService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SpringContextUtil implements ApplicationListener<ContextRefreshedEvent> {


    private static Map<String, ISsoService> ssoService;


    public static ISsoService getSsoService(String sscCode){
        if(StringUtils.isEmpty(sscCode)){
            sscCode=SsoType.AUTH;
        }
        return ssoService.get(sscCode);
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext=contextRefreshedEvent.getApplicationContext();
        Map<String, Object> map= applicationContext.getBeansWithAnnotation(SsoCode.class);
        log.info("SsoCode注解的bean:"+ JSON.toJSONString(map));
        if(map!=null){
            ssoService=new HashMap<>();
            map.forEach((key,value)->{
                String name=value.getClass().getAnnotation(SsoCode.class).name();
                ssoService.put(name,(ISsoService)value);
            });
        }
    }
}
