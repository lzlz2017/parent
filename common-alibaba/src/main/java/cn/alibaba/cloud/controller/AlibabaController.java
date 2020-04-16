package cn.alibaba.cloud.controller;

import cn.alibaba.cloud.dto.TProduct;
import cn.alibaba.cloud.service.TProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class AlibabaController {

    @Autowired
    private TProductService tProductService;

    @GetMapping("test")
    public String test(){
        return "success";
    }

    @GetMapping("product/query")
    public Object getProduct(){
        return tProductService.getProduct();
    }

    @PostMapping("add")
    public Object addProduct(String name,Double price,Integer a){
        TProduct vo=new TProduct();
        vo.setProductName(name.equals("")?null:name);
        vo.setProductPrice(price);
        tProductService.add(vo,a);
        return "success";

    }

    private boolean flag=true;

    @PostMapping("set")
    public Object addProduct(boolean _flag){
        flag=_flag;
        return "success";

    }
    @GetMapping("get")
    public Object m() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(10);
        return "success";

    }
}
