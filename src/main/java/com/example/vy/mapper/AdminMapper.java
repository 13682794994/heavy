package com.example.vy.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vy.bean.Admin;
import com.example.vy.bean.Bucket;
import com.example.vy.bean.Good;
import com.example.vy.bean.Record;




@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描

@Repository

public interface AdminMapper {

 

    /**

     * 查询用户名是否存在，若存在，不允许注册

     * 注解@Param(value) 若value与可变参数相同，注解可省略

     * 注解@Results  列名和字段名相同，注解可省略

     * @param username

     * @return

     */

    /*@Select(value = "select u.username,u.password from user u where u.username=#{username}")

    @Results

            ({@Result(property = "username",column = "username"),

              @Result(property = "password",column = "password")})

    Admin find(@Param("username") String username);*/

    

    /**

     * 注册  插入一条user记录

     * @param user

     * @return

     */

    /*@Insert("insert into user values(#{id},#{username},#{password},#{pos})")

    //加入该注解可以保存对象后，查看对象插入id

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")

    void regist(Admin user);*/


    @Select("select Aid from admin where Aid = #{s}")

    String existAdmin(String s);

    @Select("select Aid from admin where Aid = #{Aid} and Akey=#{Akey}")
    
	String login(Admin admin);
    
    @Insert("insert into admin values(#{Aid},#{Akey})")

	boolean signin(Admin admin);
    
    /*@Select("select u.pos from user u where u.username = #{username} and password = #{password}")

    String getPos(Admin user);*/
    
    @Insert("insert into good values(#{Gid},#{Gname},#{Des},#{Price})")

	boolean insertGood(Good good);
    
    @Insert("insert into bucket values(#{Aid},#{Gid})")

	boolean insertBucket(Bucket bucket);
    
    @Insert("insert into record values(#{Rid},#{Buytime},#{Aid},#{Gname})")

	boolean insertRecord(Record record);
    
    @Select("select * from good")

	List<Good> selectAllGoods();

    @Select("select * from bucket where Aid = #{m}")
    
	List<Bucket> selectAllBucket(String m);
    
    @Select("select * from record")
	List<Record> selectAllRecords();

    @Select("select * from record where Aid = #{m}")
	List<Record> selectMyRecord(String m);
    
    @Select("select * from good where Gid = #{m}")
    Good selectMyGoods(String m);

    

    @Select("select * from good g where g.Gid not in (select b.Gid from bucket b where b.Aid = #{m})")
	List<Good> selectNotGoods(String m);
    
    @Delete("delete from bucket where Gid=#{gid}")

	boolean deleteBucket(String string);

    
    @Delete("delete from good where Gid=#{gid}")
	boolean deleteGood(String string);
    






    
}





