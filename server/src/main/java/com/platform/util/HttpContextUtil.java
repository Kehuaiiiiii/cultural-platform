package com.platform.util;

import com.platform.VO.HttpResult;
import com.platform.constant.JwtConst;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtil {
    public static int getUid(HttpServletRequest request) {
        return (int) request.getAttribute(JwtConst.UID_KEY);
    }

    public static int getRid(HttpServletRequest request) {
        return (int) request.getAttribute(JwtConst.RID_KEY);
    }
}
