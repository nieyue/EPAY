<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map"%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page import="com.apiins.jarTest.TCheckUtils" %>
<%@ page import="com.apiins.jarTest.EpPayinterActiveDTO" %>
<%@ page import="com.apiins.jarTest.EpPayinteractivePayDTO" %>
<html>
<%

String TypeChannel=(String)request.getParameter("TypeChannel");
String IsTest=(String)request.getParameter("IsTest");
String BusinessSource=(String)request.getParameter("BusinessSource");
String PayStatus=(String)request.getParameter("PayStatus");
String ExtContent=(String)request.getParameter("ExtContent");
String ExtractNo=(String)request.getParameter("ExtractNo");
String TradeNo=(String)request.getParameter("TradeNo");
String SumAmount=(String)request.getParameter("SumAmount");
String ErrCode=(String)request.getParameter("ErrCode");
String ErrMsg=(String)request.getParameter("ErrMsg");
String signMsg=(String)request.getParameter("SignMsg");
String[] ProposalNoStr= request.getParameterValues("ProposalNo");
String[] PolicyNoStr= request.getParameterValues("PolicyNo");
String[] PolicyStatusStr= request.getParameterValues("PolicyStatus");
String[] PolicyErrCodeStr= request.getParameterValues("PolicyErrCode");
String[] PolicyErrMsgStr= request.getParameterValues("PolicyErrMsg");
String[] AmountStr= request.getParameterValues("Amount");
EpPayinteractivePayDTO ep=new EpPayinteractivePayDTO();

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
List<EpPayinterActiveDTO> list=new  ArrayList<EpPayinterActiveDTO>();
EpPayinterActiveDTO ea=null;
for(int i=0;i<ProposalNoStr.length;i++){
	ea=new EpPayinterActiveDTO();
	ea.setAmount(AmountStr[i]);
	ea.setProposalNo(ProposalNoStr[i]);
	ea.setPolicyNo(PolicyNoStr[i]);
	ea.setPolicyErrCode(PolicyErrCodeStr[i]);
	ea.setPolicyErrMsg(PolicyErrMsgStr[i]);
	ea.setPolicyStatus(PolicyStatusStr[i]);
	list.add(ea);
}
ep.setEpPayinterActiveList(list); 

String reType="";
if(TCheckUtils.checkReParam(ep)){//验证成功
	reType="SUCCESS";
	System.out.println("SUCCESS");
}else{
	reType="FAIL";
	System.out.println("FAIL");
}	
	
%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/emall.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.8.0/jquery-1.8.0.min.js"></script>
<title>页面跳转同步通知页面</title>
<style type="text/css">
/*.m_head{margin:0px; height:33px!important;height:33px;
	vertical-align:middle;
	background: url("/images/maincontentbg.jpg") no-repeat;color:#cc0000;font-weight:bold;}*/
</style>
<meta name="robots" content="NOINDEX,NOFOLLOW,NOSNIPPET,NOARCHIVE,NOODP">		
</head>
<body>
<%=reType %>
</body>       
</html>