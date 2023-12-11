package com.tang.appdemo.portalapi.interceptor;

import com.mysql.cj.util.StringUtils;
import com.tang.appdemo.common.constants.AppConstants;
import com.tang.appdemo.portalapi.utils.ReflexUtil;
import com.tang.appdemo.portalapi.interceptor.annotation.Language;
import com.tang.appdemo.portalapi.utils.RequestComponent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * @description 语言拦截器
 *
 * @author tanghx
 * @date 2023/12/11 9:59
 */

@Slf4j
@Component
public class I18nInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestComponent requestComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        Language language = ReflexUtil.getClazzOrMethodAnnotation(method, Language.class);

        if (language == null){
            return true;
        }

        Locale locale = null;

        String langHeader = requestComponent.getHeader(AppConstants.ACCEPT_LANGUAGE);

        log.error("language:" + langHeader);

        if (StringUtils.isNullOrEmpty(langHeader)){
            locale = Locale.US;
        }

        if ("en_US".equals(langHeader)){
            locale = Locale.US;
        }

        if ("zh_CN".equals(langHeader)){
            locale = Locale.CHINA;
        }

        RequestContextHolder.getRequestAttributes().setAttribute(AppConstants.LOCALE, locale, RequestAttributes.SCOPE_REQUEST);

        return true;
    }
}