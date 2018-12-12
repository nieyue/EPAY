package com.nieyue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apiins.jarTest.EpPayinterActiveDTO;
import com.apiins.jarTest.EpPayinteractivePayDTO;
import com.apiins.jarTest.MD5Util;
import com.apiins.jarTest.TCheckUtils;
@WebServlet("/testpay")
public class TestCommonServlet<sout> extends HttpServlet{

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
		//resp.setCharacterEncoding("UTF-8");
		
		EpPayinteractivePayDTO ep=new EpPayinteractivePayDTO();
		ep.setKey("8934e7d15453e97507ef794cf7b0519d");
		ep.setTypeChannel("7");
		ep.setIsTest("1");
		ep.setBusinessSource("1");
		ep.setBgUrl("https://sxb.dianv.net/app/index.php?i=2&go=sxb_hkj");
		ep.setPageUrl("https://sxb.dianv.net/app/index.php?i=2&go=sxb_hkjp");
		ep.setProductName("测试");
		ep.setProductNum("1");
		ep.setSumAmount("1");
		ep.setExtContent("test");
		ep.setExtractNo("new20161117"+System.currentTimeMillis());
		List<EpPayinterActiveDTO> lt=new ArrayList<EpPayinterActiveDTO>();
		EpPayinterActiveDTO ea=new EpPayinterActiveDTO();

			ea.setPayerName("何朋");
			ea.setAmount("1");
			ea.setProposalNo("25435345345"+System.currentTimeMillis());
			lt.add(ea);
		/*EpPayinterActiveDTO ea1=new EpPayinterActiveDTO();
			ea1.setPayerName("何朋2");
			ea1.setAmount("2");
			ea1.setProposalNo("45435345345"+System.currentTimeMillis());
			lt.add(ea1);*/
		ep.setEpPayinterActiveList(lt);
		String sendUrl="";
		String errorMessage="";
		Map map = null;
		try {
			map=TCheckUtils.payCommonParame(ep);
			 //map=TCheckUtils.pageParame(ep);
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
