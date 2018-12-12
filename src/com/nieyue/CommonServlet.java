package com.nieyue;

import com.apiins.jarTest.Config;
import com.apiins.jarTest.EpPayinterActiveDTO;
import com.apiins.jarTest.EpPayinteractivePayDTO;
import com.apiins.jarTest.TCheckUtils;
import com.sun.deploy.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/pay")
public class CommonServlet<sout> extends HttpServlet{

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
		String Key=req.getParameter("Key");
		if(Key==null||"".equals(Key)){
			ep.setKey("8934e7d15453e97507ef794cf7b0519d");
		}else{
			ep.setKey(Key);

		}
		String TypeChannel=req.getParameter("TypeChannel");
		if(TypeChannel==null||"".equals(TypeChannel)){
			ep.setTypeChannel("8");
		}else{
			ep.setTypeChannel(TypeChannel);

		}
		String IsTest=req.getParameter("IsTest");
		if(IsTest==null||"".equals(IsTest)){
			ep.setIsTest("0");
		}else{
			ep.setIsTest(IsTest);

		}
		String BusinessSource=req.getParameter("BusinessSource");
		if(BusinessSource==null||"".equals(BusinessSource)){
			ep.setBusinessSource("1");
		}else{
			ep.setBusinessSource(BusinessSource);

		}
		String BgUrl=req.getParameter("BgUrl");
		if(BgUrl==null||"".equals(BgUrl)){
			ep.setBgUrl("https://sxb.dianv.net/app/index.php?i=2&go=sxb_hkj");
		}else{
			ep.setBgUrl(BgUrl);

		}
		String PageUrl=req.getParameter("PageUrl");
		if(BgUrl==null||"".equals(BgUrl)){
			ep.setPageUrl("https://sxb.dianv.net/app/index.php?i=2&go=sxb_hkj");
		}else{
			ep.setPageUrl(PageUrl);

		}
		String ProductName=req.getParameter("ProductName");
		if(ProductName==null||"".equals(ProductName)){
			ep.setProductName("测试");
		}else{
			ep.setProductName(ProductName);

		}
		String SumAmount=req.getParameter("SumAmount");
		if(SumAmount==null||"".equals(SumAmount)){
			ep.setSumAmount("1");
		}else{
			ep.setSumAmount(SumAmount);

		}
		String ExtContent=req.getParameter("ExtContent");
		if(ExtContent==null||"".equals(ExtContent)){
			ep.setExtContent("test");
		}else{
			ep.setExtContent(ExtContent);

		}
		String ExtractNo=req.getParameter("ExtractNo");
		if(ExtractNo==null||"".equals(ExtractNo)){
			ep.setExtractNo("new20161117"+System.currentTimeMillis());
		}else{
			ep.setExtractNo(ExtractNo);

		}
		List<EpPayinterActiveDTO> lt=new ArrayList<EpPayinterActiveDTO>();
		EpPayinterActiveDTO ea=new EpPayinterActiveDTO();
		String PayerName=req.getParameter("PayerName");
		if(PayerName==null||"".equals(PayerName)){
			ea.setPayerName("何朋");
		}else{
			ea.setPayerName(PayerName);

		}
		String Amount=req.getParameter("Amount");
		if(Amount==null||"".equals(Amount)){
			ea.setAmount("1");
		}else{
			ea.setAmount(Amount);

		}
		String ProposalNo=req.getParameter("ProposalNo");
		if(ProposalNo==null||"".equals(ProposalNo)){
			ea.setProposalNo("25435345345"+System.currentTimeMillis());
		}else{
			ea.setProposalNo(ProposalNo);
		}
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
			map=MyUtils.payCommonParame(ep);
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
