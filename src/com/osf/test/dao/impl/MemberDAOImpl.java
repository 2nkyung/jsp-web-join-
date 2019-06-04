package com.osf.test.dao.impl;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.osf.test.dao.MemberDAO;
import com.osf.test.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
   private static SqlSessionFactory ssFactory;

   static {
      InputStream is = MemberDAOImpl.class.getClassLoader().getResourceAsStream("config/mybatis-config.xml");
      SqlSessionFactoryBuilder ssBuilder = new SqlSessionFactoryBuilder();
      ssFactory = ssBuilder.build(is);
   }

   @Override
   public List<MemberVO> selectMembers(MemberVO mvo) {
      try (SqlSession ss = ssFactory.openSession()) {
         return ss.selectList("com.osf.test.Member.selectList", mvo);

      }
   }

   @Override
   public MemberVO selectMemberByIdAndPwd(MemberVO mvo) {
      try(SqlSession ss = ssFactory.openSession()){
         return ss.selectOne("com.osf.test.Member.selectMemberByIdAndPwd",mvo);
         //매퍼네임스페이스.셀렉트에 있는 아이디를 썼다.
      }
   }

   @Override
   public int insertMember(Map<String,String> mvo) {
      try (SqlSession ss = ssFactory.openSession()) {
         if (ss.insert("com.osf.test.Member.insertMember", mvo) == 1) {
            ss.commit();
            return 1;
         }
      }
      return 0;
   }

   public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      MemberVO mvo = new MemberVO();
      System.out.println(mvo.getMiName());
      mvo.getClass().getDeclaredMethods();
      Method[] methods = mvo.getClass().getDeclaredMethods();
      String mName = "miName";
      mName = "set" + mName.substring(0, 1).toUpperCase() + mName.substring(1);
      for (Method method : methods) {
         Parameter[] prms = method.getParameters();
         if(prms[0].getType().getName().equals("java.lang.Integer")){
            method.invoke(mvo, 1);
         }else{
            method.invoke(mvo, "나나나");
         }
      }
      System.out.println(mvo.getMiName());
   }
}