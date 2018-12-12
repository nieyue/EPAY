<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.InetSocketAddress"%>
<%@page import="java.net.Proxy"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apiins.jarTest.TCheckUtils"%>
<%@ page import="com.apiins.jarTest.EpPayinterActiveDTO"%>
<%@ page import="com.apiins.jarTest.EpPayinteractivePayDTO"%>
<%--<%@ page import="org.apache.commons.httpclient.HttpClient"%>
<%@ page
	import="org.apache.commons.httpclient.SimpleHttpConnectionManager"%>
<%@ page import="org.apache.commons.httpclient.methods.PostMethod"%>--%>
<%
	//获取亚太POST过来反馈信息
	String TypeChannel = (String) request.getParameter("TypeChannel");
	String IsTest = (String) request.getParameter("IsTest");
	String BusinessSource = (String) request
			.getParameter("BusinessSource");
	String PayStatus = (String) request.getParameter("PayStatus");
	String ExtContent = (String) request.getParameter("ExtContent");
	String ExtractNo = (String) request.getParameter("ExtractNo");
	String TradeNo = (String) request.getParameter("TradeNo");
	String SumAmount = (String) request.getParameter("SumAmount");
	String ErrCode = (String) request.getParameter("ErrCode");
	String ErrMsg = (String) request.getParameter("ErrMsg");
	String signMsg = (String) request.getParameter("SignMsg");
	String[] ProposalNoStr = request.getParameterValues("ProposalNo");
	String[] PolicyNoStr = request.getParameterValues("PolicyNo");
	String[] PolicyStatusStr = request
			.getParameterValues("PolicyStatus");
	String[] PolicyErrCodeStr = request
			.getParameterValues("PolicyErrCode");
	String[] PolicyErrMsgStr = request
			.getParameterValues("PolicyErrMsg");
	String[] AmountStr = request.getParameterValues("Amount");
	EpPayinteractivePayDTO ep = new EpPayinteractivePayDTO();
	if("7".equals(TypeChannel)&&"1".equals(IsTest)){
		ep.setKey("m3CWiK.GT3Su9cknAvy9z#}*Oxy3o9");
	}else if("7".equals(TypeChannel)&&"0".equals(IsTest)){
		ep.setKey("n38SGpK,EM4Wdi7@zko{$yQ5iU2Xjh");
	}else{
		ep.setKey("lk9snr2zx1gpsohz7i8tl7ke92bymp9r");
	}

	ep.setTypeChannel(TypeChannel);
	ep.setIsTest(IsTest);
	ep.setBusinessSource(BusinessSource);
	ep.setPayStatus(PayStatus);
	ep.setExtractNo(ExtractNo);
	ep.setExtContent(ExtContent);
	ep.setTradeNo(TradeNo);
	ep.setSumAmount(SumAmount);
	ep.setErrCode(ErrCode);
	ep.setErrMsg(ErrMsg);
	ep.setSignMsg(signMsg);
	List<EpPayinterActiveDTO> list = new ArrayList<EpPayinterActiveDTO>();
	EpPayinterActiveDTO ea = null;
	for (int i = 0; i < ProposalNoStr.length; i++) {
		ea = new EpPayinterActiveDTO();
		ea.setAmount(AmountStr[i]);
		ea.setProposalNo(ProposalNoStr[i]);
		ea.setPolicyNo(PolicyNoStr[i]);
		ea.setPolicyErrCode(PolicyErrCodeStr[i]);
		ea.setPolicyErrMsg(PolicyErrMsgStr[i]);
		ea.setPolicyStatus(PolicyStatusStr[i]);
		list.add(ea);
	}
	ep.setEpPayinterActiveList(list);
	if (TCheckUtils.checkReParam(ep)) {//验证成功
		System.out.println("SUCCESS");
	} else {
		System.out.println("FAIL");
	}
%>
