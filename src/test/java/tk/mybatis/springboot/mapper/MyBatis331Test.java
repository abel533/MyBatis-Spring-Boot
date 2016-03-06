/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package tk.mybatis.springboot.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.springboot.Application;
import tk.mybatis.springboot.model.City2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzh
 * @since 2016-03-06 17:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringApplicationConfiguration(Application.class)
public class MyBatis331Test {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MyBatis331Mapper mapper;

    @Test
    @Rollback
    public void testInsertList() {
        List<City2> city2List = new ArrayList<City2>();
        city2List.add(new City2("石家庄", "河北"));
        city2List.add(new City2("邯郸", "河北"));
        city2List.add(new City2("秦皇岛", "河北"));
        Assert.assertEquals(3, mapper.insertCities(city2List));
        for (City2 c2 : city2List) {
            logger.info(c2.toString());
            Assert.assertNotNull(c2.getId());
        }
    }

    @Test
    public void testSelectById(){
        City2 city2 = mapper.selectByCityId(1);
        logger.info(city2.toString());
        Assert.assertNotNull(city2);
        Assert.assertNotNull(city2.getCityName());
        Assert.assertNotNull(city2.getCityState());
    }

    @Test
    public void testSelectAll(){
        List<City2> city2List = mapper.selectAll();
        for(City2 c2 : city2List){
            logger.info(c2.toString());
            Assert.assertNotNull(c2);
            Assert.assertNotNull(c2.getCityName());
            Assert.assertNotNull(c2.getCityState());
        }
    }

}
