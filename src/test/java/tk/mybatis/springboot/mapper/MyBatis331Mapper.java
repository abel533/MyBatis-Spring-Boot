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

import org.apache.ibatis.annotations.*;
import tk.mybatis.springboot.model.City2;

import java.util.List;

/**
 * mybatis3.3.1版本新增功能测试
 *
 * @author liuzh
 * @since 2016-03-06 17:22
 */
public interface MyBatis331Mapper {

    /**
     * 批量插入
     *
     * @param cities
     * @return
     */
    @Insert("<script>" +
            "insert into city (id, name, state) values " +
            "<foreach collection=\"list\" item=\"city\" separator=\",\" >" +
                "(#{city.id}, #{city.cityName}, #{city.cityState})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCities(List<City2> cities);

    /**
     * 根据主键查询一个
     *
     * @param id
     * @return
     */
    @Results(id = "cityResult", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "cityName", column = "name", id = true),
        @Result(property = "cityState", column = "state", id = true)
    })
    @Select("select id, name, state from city where id = #{id}")
    City2 selectByCityId(Integer id);

    /**
     * 查询全部，引用上面的Results
     *
     * @return
     */
    @ResultMap("cityResult")
    @Select("select id, name, state from city")
    List<City2> selectAll();
}
