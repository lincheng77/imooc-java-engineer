package cn.edkso.mybatis;

import cn.edkso.entity.Goods;
import cn.edkso.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JUNIT单元测试类
 */
public class MybatisTestor {

    @Test
    public void testSqlSessionFactory() throws IOException {
        //利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader mybatisConfigReader = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象,同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfigReader);
        System.out.println("SessionFactory加载成功");


        SqlSession sqlSession = null;
        try {
            //创建SqlSession对象,SqlSession是JDBC的扩展类,用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
            //创建数据库连接(测试用)
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                //如果type="POOLED",代表使用连接池,close则是将连接回收到连接池中
                //如果type="UNPOOLED",代表直连,close则会调用Connection.close()方法关闭连接
                sqlSession.close();
            }
        }
    }



    /**
     * select查询语句执行
     * @throws Exception
     */
    @Test
    public void testSelectAll() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            List<Goods> list = session.selectList("goods.selectAll");
            list.forEach(e -> System.out.println(e));
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 传递单个SQL参数
     * @throws Exception
     */
    @Test
    public void testSelectById() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById" , 1603);
            System.out.println(goods.getTitle());
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 传递多个SQL参数
     * @throws Exception
     */
    @Test
    public void testSelectByPriceRange() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            Map param = new HashMap();
            param.put("min",100);
            param.put("max" , 500);
            param.put("limt" , 10);
            List<Goods> list = session.selectList("goods.selectByPriceRange", param);
            for(Goods g:list){
                System.out.println(g.getTitle() + ":" + g.getCurrentPrice());

            }
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 新增数据
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            //insert()方法返回值代表本次成功插入的记录总数
            int num = session.insert("goods.insert", goods);
            session.commit();//提交事务数据
            System.out.println(goods.getGoodsId());
        }catch (Exception e){
            if(session != null){
                session.rollback();//回滚事务
            }
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 更新数据
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 2675);
            goods.setTitle("更新测试商品");
            int num = session.update("goods.update" , goods);
            session.commit();//提交事务数据
        }catch (Exception e){
            if(session != null){
                session.rollback();//回滚事务
            }
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 删除数据
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            int num = session.delete("goods.delete" , 739);
            session.commit();//提交事务数据
        }catch (Exception e){
            if(session != null){
                session.rollback();//回滚事务
            }
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }


    /**
     * 预防SQL注入
     * @throws Exception
     */
    @Test
    public void testSelectByTitle() throws Exception {
        SqlSession session = null;
        try{
            session = MyBatisUtils.openSession();
            Map param = new HashMap();
            /*
                ${}原文传值
                select * from t_goods
                where title = '' or 1 =1 or title = '【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版'
            */
            /*
               #{}预编译
               select * from t_goods
                where title = "'' or 1 =1 or title = '【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版'"
            */

            param.put("title","'' or 1=1 or title='【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版'");
            param.put("order" , " order by title desc");
            List<Goods> list = session.selectList("goods.selectByTitle", param);
            for(Goods g:list){
                System.out.println(g.getTitle() + ":" + g.getCurrentPrice());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(session);
        }
    }
}
