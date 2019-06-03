package com.osf.test.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.MemberService;
import com.osf.test.service.impl.MemberServiceImpl;
import com.osf.test.vo.MemberVO;



@WebServlet("/member")
public class MemberServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private MemberService ms = new MemberServiceImpl();
   
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String uri = request.getRequestURI();      
      uri = uri.substring(7);
      MemberVO mv = new MemberVO();
      System.out.println(uri);
      if(("/login").equals(uri)) {         
         uri = "/WEB-INF/member" + uri + ".jsp";
         RequestDispatcher rd = request.getRequestDispatcher(uri);         
         String miId = request.getParameter("id");
         String miPwd = request.getParameter("pwd");
         mv.setMiPwd(miPwd);   
         mv.setMiId(miId);
         
         
         rd.forward(request,response);   
      }      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
      Map<String,String> param = new HashMap<String,String>();
      try {
         List<FileItem> fiList = sfu.parseRequest(request);
         for(FileItem fi:fiList) {
            if(fi.isFormField()) {
               String key = fi.getFieldName();
               String value = fi.getString("utf-8");
               param.put(key, value);
            }else {
               String key = fi.getFieldName();
               String fileName = fi.getName();
               param.put(key, fileName);
            }
         }
         request.setAttribute("cnt", ms.insertMember(param));
         RequestDispatcher rd = request.getRequestDispatcher("/member/signok.jsp");
         rd.forward(request,response);
      } catch (FileUploadException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }

}