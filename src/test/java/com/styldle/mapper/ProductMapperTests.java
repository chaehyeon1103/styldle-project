package com.styldle.mapper;

import com.styldle.vo.ProductAttachVO;
import com.styldle.vo.ProductVO;
import com.styldle.vo.StoreCriteria;
import com.styldle.vo.StyleImgVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
@Slf4j
public class ProductMapperTests {
    @Setter(onMethod_=@Autowired)
    private ProductMapper mapper;

    @Setter(onMethod_=@Autowired)
    private ProductAttachMapper productAttachMapper;

    @Test
    public void insertOne() {
        ProductVO product = new ProductVO();
        product.setType("outer");
        product.setProductName("coat");
        product.setPrice(1000);
        product.setDetail("long coat");
        product.setStock(10);
        mapper.insert(product);
    }

    @Test
    public void insertTest(){
        for (int i=0;i<30;i++){
            ProductVO product = new ProductVO();
            product.setType("outer");
            product.setProductName("coat"+(i+1));
            product.setPrice((i+1)*1000);
            product.setDetail("long coat");
            product.setStock(10);
            mapper.insert(product);
        }
    }

    @Test
    public void insertImages() {
//       for (int i = 1; i <= 30; i++) {
            ProductAttachVO vo = new ProductAttachVO();
            vo.setFileName("outfit.jpg");
            vo.setProductId(30);
            vo.setUuid("a5740784-0f93-45a8-8334-970e616f0260");
            vo.setUploadPath("2021\\12\\23");
            productAttachMapper.insert(vo);
//       }
    }

    @Test
    public void testSearch(){
        StoreCriteria cri = new StoreCriteria();
        List<ProductVO> list = mapper.selectListWithPaging(cri);
        list.forEach(i -> System.out.println(i));
    }


}
