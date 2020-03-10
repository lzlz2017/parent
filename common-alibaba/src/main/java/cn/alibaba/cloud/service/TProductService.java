package cn.alibaba.cloud.service;

import cn.alibaba.cloud.dto.TProduct;
import cn.alibaba.cloud.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TProductService {

    @Autowired
    private TProductMapper tProductMapper;

    public Object getProduct(){
       return tProductMapper.selectList(null);
    }

    public void add(TProduct vo,Integer a){
        if(a==1){
           throw new RuntimeException();
        }

        tProductMapper.insert(vo);
    }

}
