package com.nieyue;

import com.apiins.jarTest.EpPayinterActiveDTO;
import com.apiins.jarTest.EpPayinteractivePayDTO;
import com.apiins.jarTest.TCheckUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@WebServlet("/pay2")
public class TestJarServlet  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
       // resp.setCharacterEncoding("UTF-8");
		String TypeChannel=req.getParameter("TypeChannel");
		String IsTest=req.getParameter("IsTest");
		EpPayinteractivePayDTO ep=new EpPayinteractivePayDTO();
		
		
		if("7".equals(TypeChannel)||"3".equals(TypeChannel)&&"1".equals(IsTest)){
			ep.setKey("m3CWiK.GT3Su9cknAvy9z#}*Oxy3o9");
			ep.setSumAmount("10000");
		}else if("7".equals(TypeChannel)||"3".equals(TypeChannel)&&"0".equals(IsTest)){
			ep.setKey("n38SGpK,EM4Wdi7@zko{$yQ5iU2Xjh");
			ep.setSumAmount("1");
		}else{
			ep.setKey("lk9snr2zx1gpsohz7i8tl7ke92bymp9r");
			ep.setSumAmount("1");
		}

		ep.setTypeChannel(TypeChannel);
		ep.setIsTest(IsTest);
		ep.setBusinessSource("1");
		ep.setBgUrl("http://nieyue.ngrok.xiaomiqiu.cn/notify_url_card.jsp");
		ep.setPageUrl("http://nieyue.ngrok.xiaomiqiu.cn/return_url_card.jsp");
		ep.setProductName("测试");
		ep.setProductNum("1");

		ep.setExtContent("test");
		ep.setExtractNo("new20161117"+System.currentTimeMillis());
		List<EpPayinterActiveDTO> lt=new ArrayList<EpPayinterActiveDTO>();
		EpPayinterActiveDTO ea=new EpPayinterActiveDTO();
			ea.setPayerName("何朋");
			ea.setAmount("10000");	
			ea.setProposalNo("45435345345"+System.currentTimeMillis());
			lt.add(ea);
		
		ep.setEpPayinterActiveList(lt);
		String sendUrl="";
		String errorMessage="";
		Map map = null;
		try {
			 map=TCheckUtils.pageParame(ep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("00".equals((String)map.get("errorCode"))){
			sendUrl=(String)map.get("param");
			req.setAttribute("url", sendUrl);
			req.getRequestDispatcher("/SendMessage.jsp").forward(req, resp);
			return;
		}else{
			errorMessage=(String)map.get("errorMessage");
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/ErrorMessage.jsp").forward(req, resp);
			return;
		}
		
	}
	
	
	

}
