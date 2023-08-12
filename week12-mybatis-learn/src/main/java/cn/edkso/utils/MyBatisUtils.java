package cn.edkso.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * MyBatisUtils工具类,创建全局唯一的SqlSessionFactory对象
 */
public class MyBatisUtils {

        //SqlSessionFactory对象是线程安全的,一个应用中只需要一个即可
        private static SqlSessionFactory sqlSessionFactory = null;

        //静态代码块,在类加载时执行,只执行一次
        static {
            try {
                //利用Reader加载classpath下的mybatis-config.xml核心配置文件
                Reader mybatisConfigReader = Resources.getResourceAsReader("mybatis-config.xml");
                //初始化SqlSessionFactory对象,同时解析mybatis-config.xml文件
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfigReader);
                System.out.println("SessionFactory加载成功");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        /**
        * 获取SqlSession对象
        * @return SqlSession对象
        */
        public static SqlSession openSqlSession(){
            return sqlSessionFactory.openSession();
        }

    /**
     * 关闭SqlSession对象
     * @param sqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession){
            if(sqlSession != null){
                sqlSession.close();
            }
        }
}
