package cn.alibaba.cloud.controller;

import cn.alibaba.cloud.dto.TProduct;
import cn.alibaba.cloud.service.TProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
