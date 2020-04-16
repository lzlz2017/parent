package cn.alibaba.cloud.mapper;

import cn.alibaba.cloud.ApplicationTest;
import cn.alibaba.cloud.dto.TProduct;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {ApplicationTest.class})
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    private TProductMapper tProductMapper;

    @Test
    public void test(){
        Page<TProduct> page=new Page<>(2,2);
        System.out.println(JSON.toJSONString(tProductMapper.selectPage(page,null)));
    }
}
