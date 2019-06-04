package com.osf.test.servlet;

import java.io.File;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.MemberService;
import com.osf.test.service.impl.MemberServiceImpl;
import com.osf.test.util.SHAEncoder;
import com.osf.test.vo.MemberVO;


@WebServlet("/mem/*")
public class MemberServlet extends HttpServlet {
   private MemberService ms = new MemberServiceImpl();
   private static final String PATH = "D:\\study\\workspace\\jsp-web\\WebContent\\upload";
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String url = request.getRequestURI();
      if("/mem/list".equals(url)) {
         request.setAttribute("mvo", ms.selectMembers(null));
         RequestDispatcher rd = request.getRequestDispatcher("/member/list.jsp");
         rd.forward(request, response);
      }
      response.getWriter().append("Served at: ").append(request.getContextPath());

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String url = request.getRequestURI().replace("/mem/", "");
      ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
      
      if ("insert".equals(url)) {
         try {
            List<FileItem> fiList = sfu.parseRequest(request);
            Map<String, String> param = new HashMap<>();
            for (FileItem fi : fiList) {
               if (fi.isFormField()) {
                  String key = fi.getFieldName();
                  String value = fi.getString("utf-8");
                  param.put(key, value);
               } else {
                  String key = fi.getFieldName();
                  String fileName = fi.getName();
                  String ext = fileName.substring(fileName.lastIndexOf("."));
                  fileName = Long.toString(System.nanoTime());
                  param.put(key, fileName);
                  File f = new File(PATH + "//" + fileName+ext);
                  try {
                     fi.write(f);
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
               }
            }
            param.put("miPwd", SHAEncoder.encode(param.get("miPwd")));
            request.setAttribute("cnt", ms.insertMember(param));
         } catch (FileUploadException e) {
            e.printStackTrace();
         }
         RequestDispatcher rd = request.getRequestDispatcher("/member/signok.jsp");
         rd.forward(request, response);
      }

      else if ("login".equals(url)) {
         MemberVO mvo = new MemberVO();
         mvo.setMiId(request.getParameter("id"));
         mvo.setMiPwd(SHAEncoder.encode(request.getParameter("miPwd")));
         mvo = ms.selectMemberByIdAndPwd(mvo);
         if(mvo!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("mvo", mvo);
            RequestDispatcher rd = request.getRequestDispatcher("/member/loginok.jsp");
            rd.forward(request, response);
         }else {
            RequestDispatcher rd = request.getRequestDispatcher("/member/login.jsp");
            request.setAttribute("msg", "실패");
            rd.forward(request, response);
         }
      }
      
   }
   public static void main(String[] args) {
      String fileName="dddd.jpg";
      System.out.println();
   }
}