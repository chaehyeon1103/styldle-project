package com.styldle.mapper;

import com.styldle.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
@Slf4j
public class SnsBoardMapperTests {
    @Setter(onMethod_ = @Autowired)
    private SnsBoardMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private StyleImgMapper styleImgMapper;

    @Setter(onMethod_ = @Autowired)
    private SnsCommentMapper snsCommentMapper;

    @Setter(onMethod_ = @Autowired)
    private TagMapper tagMapper;

    @Setter(onMethod_ = @Autowired)
    private HeartMapper heartMapper;

    @Setter(onMethod_ = @Autowired)
    private UserMapper userMapper;

    @Test
    public void userDummies() {
        String[] users = {"coding", "jihye", "green1201", "red1202", "blue1234", "white_12", "black_321"};
        for (int i = 0; i < 7; i++) {
            UserVO vo = new UserVO();
            vo.setUserId(users[i]);
            vo.setPwd("$2a$10$ifTUK5i/tClwpnw0zLy8i.HaS3M58tsZ/CoBIwNZqSQbMCp67bBoe");
            userMapper.insert(vo);
        }
    }

    @Test
    public void boardDummies() {
        for (int i = 0; i < 24; i++) {
            SnsBoardVO vo = new SnsBoardVO()
                    .setUserId("jihye")
                    .setContent("내용" + i);
            mapper.insert(vo);
        }
    }

    @Test
    public void imgDummies() {
        for (int i = 25; i <= 36; i++) {
            StyleImgVO vo = new StyleImgVO();
//            vo.setFileName("a70f7ecd15833a350ef043f953262ab0.jpg");
            vo.setPostId(i);
//            vo.setUuid("f9227ed5-6f81-4ead-ba26-b7b473980bc3");
//            vo.setUploadPath("2021\\12\\27");
            styleImgMapper.insertMany(vo);
        }
    }

    @Test
    public void test() {
        mapper.getList().forEach(i-> System.out.println(i));
    }

    @Test
    public void insert() {
        for (int i = 0; i < 100; i++) {
            SnsBoardVO vo = new SnsBoardVO()
                    .setUserId("jihye")
                    .setContent("내용" + i);
            mapper.insert(vo);
        }
    }

    @Test
    public void insertImages() {
        for (int i = 2; i <= 100; i++) {
            StyleImgVO vo = new StyleImgVO();
            vo.setFileName("1309813935.jpg");
            vo.setPostId(i);
            vo.setUuid("d4214deb-65cc-4f98-9beb-f42b62f8de9f");
            vo.setUploadPath("2021\\12\\13");
            styleImgMapper.insertMany(vo);
        }
    }

    @Test
    public void insertComments() {
        for (int i = 1; i <= 36; i++) {
            SnsCommentVO vo = new SnsCommentVO();
            vo.setPostId(i);
            for(int j = 0; j < (int)(Math.random() * 30); j++) {
                vo.setComment("댓글입니다." + j);
                vo.setUserId("jihye");
                System.out.println("댓글 등록: " + snsCommentMapper.insert(vo));
            }
        }
    }

    @Test
    public void insertGreen() {
        for (int i = 0; i < 100; i++) {
            UserVO vo = new UserVO();
            vo.setUserId("green" + i);
            vo.setPwd("$2a$10$ifTUK5i/tClwpnw0zLy8i.HaS3M58tsZ/CoBIwNZqSQbMCp67bBoe");
            userMapper.insert(vo);
        }
    }

    @Test
    public void insertHearts() {
        for (int i = 25; i <= 36; i++) {
            for (int j = 0; j < ((int)(Math.random() * 30)); j++) {
                heartMapper.save(i, "green" + j);
            }
        }
    }

    @Test
    public void insertTags() {
        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < (int)(Math.random() * 5); j++) {
                TagVO vo = new TagVO();
                vo.setPostId(i);
                vo.setTagName("태그이름" + j);
                vo.setLink("https://www.naver.com/");
                tagMapper.insert(vo);
            }
        }
    }

//    @Test
    public void getOne() {
        System.out.println(mapper.getOne(2));
    }

//    @Test
    public void update() {
        SnsBoardVO vo = mapper.getOne(2);
        vo.setContent("수정된 내용입니다.");
        mapper.update(vo);
    }

    @Test
    public void paging() {
        SnsCriteria cri = new SnsCriteria();
        cri.setPageNum(1);
        cri.setAmount(12);
        cri.setStartNum(0);
        List<SnsBoardVO> list = mapper.getListWithPaging(cri);
        list.forEach(i -> System.out.println(i));
    }

    @Test
    public void getTotalCount() {
        System.out.println(mapper.getTotalCount());
    }

    @Test
    public void tagRemove() {
        tagMapper.deleteAll(3);
    }

}
