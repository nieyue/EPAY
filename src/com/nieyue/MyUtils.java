package com.nieyue;


import com.apiins.jarTest.Config;
import com.apiins.jarTest.EpPayinterActiveDTO;
import com.apiins.jarTest.EpPayinteractivePayDTO;
import com.apiins.jarTest.MD5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtils  {
    public MyUtils() {
    }
    public static Map payCommonParame(EpPayinteractivePayDTO ep) throws Exception {
       //ep.setTypeChannel("8");
        Map map = checkParame(ep);
        if ("01".equals((String)map.get("errorCode"))) {
            return map;
        } else {
            String SignMsg = (String)map.get("sign");
            String parm = getComposeText(ep, ep.getEpPayinterActiveList());
            String url = Config.getUrl(ep.getIsTest(), "10");
            String reParm = url + parm + "&SignMsg=" + SignMsg;
            map.put("param", reParm);
            return map;
        }
    }

    public static Map pageParame(EpPayinteractivePayDTO ep) throws Exception {
        Map map = checkParame(ep);
        if ("01".equals((String)map.get("errorCode"))) {
            return map;
        } else {
            String SignMsg = (String)map.get("sign");
            String parm = getComposeText(ep, ep.getEpPayinterActiveList());
            String url = Config.getUrl(ep.getIsTest(), (String)null);
            String action = Config.getAction(ep.getTypeChannel());
            String reParm = url + action + parm + "&SignMsg=" + SignMsg;
            map.put("param", reParm);
            return map;
        }
    }

    private static Map checkParame(EpPayinteractivePayDTO ep) throws Exception {
        Map map = new HashMap();
        if (ep == null) {
            map.put("errorCode", "01");
            map.put("errorMessage", "֧���������EpPayinteractivePayDTOΪ��!");
            return map;
        } else {
            List<EpPayinterActiveDTO> ea = ep.getEpPayinterActiveList();
            if (ea == null) {
                map.put("errorCode", "01");
                map.put("errorMessage", "֧���������EpPayinterActiveDTOΪ��!");
                return map;
            } else if (ep.getTypeChannel() != null && !"".equals(ep.getTypeChannel())) {
                if (ep.getIsTest() != null && !"".equals(ep.getIsTest())) {
                    if (ep.getBusinessSource() != null && !"".equals(ep.getBusinessSource())) {
                        if (ep.getPageUrl() != null && !"".equals(ep.getPageUrl())) {
                            if (ep.getBgUrl() != null && !"".equals(ep.getBgUrl())) {
                                if (ep.getProductName() != null && !"".equals(ep.getProductName())) {
                                    if (ep.getProductNum() != null && !"".equals(ep.getProductNum())) {
                                        if (ep.getSumAmount() != null && !"".equals(ep.getSumAmount())) {
                                            if (ep.getExtContent() != null && !"".equals(ep.getExtContent())) {
                                                if (ep.getExtractNo() != null && !"".equals(ep.getExtractNo())) {
                                                    int sumfee = 0;
                                                    if (Integer.parseInt(ep.getSumAmount()) <= 0) {
                                                        map.put("errorCode", "01");
                                                        map.put("errorMessage", "֧���������SumAmount����С��1!");
                                                        return map;
                                                    } else {
                                                        int i = 0;

                                                        while(i < ea.size()) {
                                                            sumfee += Integer.parseInt(((EpPayinterActiveDTO)ea.get(i)).getAmount());
                                                            if (((EpPayinterActiveDTO)ea.get(i)).getProposalNo() != null && !((EpPayinterActiveDTO)ea.get(i)).getProposalNo().trim().equals("")) {
                                                                if (((EpPayinterActiveDTO)ea.get(i)).getPayerName() != null && !((EpPayinterActiveDTO)ea.get(i)).getPayerName().trim().equals("")) {
                                                                    if (Integer.parseInt(((EpPayinterActiveDTO)ea.get(i)).getAmount()) <= 0) {
                                                                        map.put("errorCode", "01");
                                                                        map.put("errorMessage", "��Ʒ���Amount������Ϊ�գ��Ҳ���С�ڻ�����㣡");
                                                                        return map;
                                                                    }

                                                                    ++i;
                                                                    continue;
                                                                }

                                                                map.put("errorCode", "01");
                                                                map.put("errorMessage", "֧��PayerName�������Ϊ��������ò�������Ϊ�գ�");
                                                                return map;
                                                            }

                                                            map.put("errorCode", "01");
                                                            map.put("errorMessage", "Ͷ������ProposalNo�������Ϊ��������ò�������Ϊ�գ�");
                                                            return map;
                                                        }

                                                        if (Integer.parseInt(ep.getSumAmount()) != sumfee) {
                                                            map.put("errorCode", "01");
                                                            map.put("errorMessage", "�����ܽ��Amount������ö����и����Ʒ���֮����ȣ�");
                                                            return map;
                                                        } else {
                                                            String sign = MD5Util.md5Hex(getComposeSignText(ep, ea, ep.getKey()).getBytes("UTF-8")).toUpperCase();
                                                            map.put("sign", sign);
                                                            map.put("errorCode", "00");
                                                            return map;
                                                        }
                                                    }
                                                } else {
                                                    map.put("errorCode", "01");
                                                    map.put("errorMessage", "֧���������ExtractNoΪ��!");
                                                    return map;
                                                }
                                            } else {
                                                map.put("errorCode", "01");
                                                map.put("errorMessage", "֧���������ExtContentΪ��!");
                                                return map;
                                            }
                                        } else {
                                            map.put("errorCode", "01");
                                            map.put("errorMessage", "֧���������SumAmountΪ��!");
                                            return map;
                                        }
                                    } else {
                                        map.put("errorCode", "01");
                                        map.put("errorMessage", "֧���������ProductNumΪ��!");
                                        return map;
                                    }
                                } else {
                                    map.put("errorCode", "01");
                                    map.put("errorMessage", "֧���������ProductNameΪ��!");
                                    return map;
                                }
                            } else {
                                map.put("errorCode", "01");
                                map.put("errorMessage", "֧���������BgUrlΪ��!");
                                return map;
                            }
                        } else {
                            map.put("errorCode", "01");
                            map.put("errorMessage", "֧���������PageUrlΪ��!");
                            return map;
                        }
                    } else {
                        map.put("errorCode", "01");
                        map.put("errorMessage", "֧���������BusinessSourceΪ��!");
                        return map;
                    }
                } else {
                    map.put("errorCode", "01");
                    map.put("errorMessage", "֧���������IsTestΪ��!");
                    return map;
                }
            } else {
                map.put("errorCode", "01");
                map.put("errorMessage", "֧���������TypeChannelΪ��!");
                return map;
            }
        }
    }

    private static String getComposeSignText(EpPayinteractivePayDTO epPayInterActivePayDto, List<EpPayinterActiveDTO> epPayInterActiveDtoList, String key) {
        String signText = "";
        signText = appendParam(signText, "TypeChannel", epPayInterActivePayDto.getTypeChannel());
        signText = appendParam(signText, "IsTest", epPayInterActivePayDto.getIsTest());
        signText = appendParam(signText, "BusinessSource", epPayInterActivePayDto.getBusinessSource());
        signText = appendParam(signText, "PageUrl", epPayInterActivePayDto.getPageUrl());
        signText = appendParam(signText, "BgUrl", epPayInterActivePayDto.getBgUrl());

        try {
            signText = appendParam(signText, "ProductName", URLEncoder.encode(epPayInterActivePayDto.getProductName(), "UTF-8"));
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        }

        signText = appendParam(signText, "ProductNum", epPayInterActivePayDto.getProductNum().toString());
        signText = appendParam(signText, "SumAmount", epPayInterActivePayDto.getSumAmount().toString());

        try {
            signText = appendParam(signText, "ExtContent", URLEncoder.encode(epPayInterActivePayDto.getExtContent(), "UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        signText = appendParam(signText, "ExtractNo", epPayInterActivePayDto.getExtractNo());

        for(int i = 0; i < epPayInterActiveDtoList.size(); ++i) {
            signText = appendParam(signText, "ProposalNo", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getProposalNo());

            try {
                signText = appendParam(signText, "PayerName", URLEncoder.encode(((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPayerName(), "UTF-8"));
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
            }

            signText = appendParam(signText, "Amount", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getAmount().toString());
        }

        signText = appendParam(signText, "key", key);
        return signText;
    }

    private static String getComposeText(EpPayinteractivePayDTO epPayInterActivePayDto, List<EpPayinterActiveDTO> epPayInterActiveDtoList) {
        String signText = "";
        signText = appendParam(signText, "TypeChannel", epPayInterActivePayDto.getTypeChannel());
        signText = appendParam(signText, "IsTest", epPayInterActivePayDto.getIsTest());
        signText = appendParam(signText, "BusinessSource", epPayInterActivePayDto.getBusinessSource());
        signText = appendParam(signText, "PageUrl", epPayInterActivePayDto.getPageUrl());
        signText = appendParam(signText, "BgUrl", epPayInterActivePayDto.getBgUrl());

        try {
            signText = appendParam(signText, "ProductName", URLEncoder.encode(epPayInterActivePayDto.getProductName(), "UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        signText = appendParam(signText, "ProductNum", epPayInterActivePayDto.getProductNum().toString());
        signText = appendParam(signText, "SumAmount", epPayInterActivePayDto.getSumAmount().toString());

        try {
            signText = appendParam(signText, "ExtContent", URLEncoder.encode(epPayInterActivePayDto.getExtContent(), "UTF-8"));
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        signText = appendParam(signText, "ExtractNo", epPayInterActivePayDto.getExtractNo());

        for(int i = 0; i < epPayInterActiveDtoList.size(); ++i) {
            signText = appendParam(signText, "ProposalNo", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getProposalNo());

            try {
                signText = appendParam(signText, "PayerName", URLEncoder.encode(((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPayerName(), "UTF-8"));
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }

            signText = appendParam(signText, "Amount", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getAmount().toString());
        }

        return signText;
    }

    private static String appendParam(String returnStr, String paramId, String paramValue) {
        StringBuilder builder = null;
        if (returnStr != null && !"".equals(returnStr)) {
            builder = new StringBuilder(returnStr);
            if (paramValue != null && !paramValue.trim().equals("")) {
                builder.append("&").append(paramId).append("=").append(paramValue);
            }
        } else {
            builder = new StringBuilder("");
            if (paramValue != null && !paramValue.trim().equals("")) {
                builder.append(paramId).append("=").append(paramValue);
            }
        }

        return builder.toString();
    }

    public static boolean checkReParam(EpPayinteractivePayDTO ep) throws Exception {
        boolean checkEp = false;
        String SignMsg = ep.getSignMsg();
        String reSignMsg = getBackComposeSign(ep, ep.getEpPayinterActiveList(), ep.getKey());
        String reSingnMsgs = MD5Util.md5Hex(reSignMsg.getBytes("UTF-8")).toUpperCase();
        return SignMsg.equals(reSingnMsgs) ? true : checkEp;
    }

    private static String getBackComposeSign(EpPayinteractivePayDTO epPayInterActivePayDto, List<EpPayinterActiveDTO> epPayInterActiveDtoList, String key) {
        String signText = "";
        signText = appendParam(signText, "TypeChannel", epPayInterActivePayDto.getTypeChannel());
        signText = appendParam(signText, "IsTest", epPayInterActivePayDto.getIsTest());
        signText = appendParam(signText, "BusinessSource", epPayInterActivePayDto.getBusinessSource());
        signText = appendParam(signText, "PayStatus", epPayInterActivePayDto.getPayStatus());

        try {
            signText = appendParam(signText, "ExtContent", epPayInterActivePayDto.getExtContent() == null ? null : URLDecoder.decode(epPayInterActivePayDto.getExtContent(), "UTF-8"));
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        }

        signText = appendParam(signText, "ExtractNo", epPayInterActivePayDto.getExtractNo());
        signText = appendParam(signText, "SumAmount", epPayInterActivePayDto.getSumAmount());
        signText = appendParam(signText, "TradeNo", epPayInterActivePayDto.getTradeNo());
        signText = appendParam(signText, "ErrCode", epPayInterActivePayDto.getErrCode());

        try {
            signText = appendParam(signText, "ErrMsg", epPayInterActivePayDto.getErrMsg() == null ? null : URLDecoder.decode(epPayInterActivePayDto.getErrMsg(), "UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        for(int i = 0; i < epPayInterActiveDtoList.size(); ++i) {
            signText = appendParam(signText, "ProposalNo", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getProposalNo());
            signText = appendParam(signText, "PolicyNo", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPolicyNo());
            signText = appendParam(signText, "Amount", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getAmount().toString());
            signText = appendParam(signText, "PolicyStatus", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPolicyStatus());
            signText = appendParam(signText, "PolicyErrCode", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPolicyErrCode());

            try {
                signText = appendParam(signText, "PolicyErrMsg", ((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPolicyErrMsg() == null ? null : URLDecoder.decode(((EpPayinterActiveDTO)epPayInterActiveDtoList.get(i)).getPolicyErrMsg(), "UTF-8"));
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
            }
        }

        signText = appendParam(signText, "key", key);
        return signText;
    }

}
