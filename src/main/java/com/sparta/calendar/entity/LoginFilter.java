package com.sparta.calendar.entity;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {
            "/", "/users/signup", "/users/login", "/error", "/boards"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        log.info("🔥 요청 URI: {}", requestURI);

        // 화이트리스트는 인증 필터를 건너뛰게 한다.
        if (isWhiteList(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        // 로그인하지 않은 사용자
        if (session == null || session.getAttribute("user") == null) {
            log.warn("🚫 로그인하지 않은 사용자 요청입니다.");
            throw new RuntimeException("로그인 해주세요.");
        }

        log.info("✅ 로그인된 사용자, 필터 통과");

        chain.doFilter(request, response); // 다음 필터로 넘기기
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
        // 로그인을 체크 해야하는 URL인지 검사
        // whiteListURL에 포함된 경우 true 반환 -> !true = false


    }

