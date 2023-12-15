package org.ryan.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.ryan.constant.ResponseCode;
import org.ryan.dto.ResponseDto;
import org.ryan.utils.JSONUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException
  ) throws IOException, ServletException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    final PrintWriter out = response.getWriter();
    out.print(JSONUtils.stringify(ResponseDto.error(ResponseCode.UNAUTHORIZED)));
    out.flush();
  }
}
