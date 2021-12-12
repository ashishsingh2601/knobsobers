package com.springboot.boilerplate.interceptor;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class InterceptorRequestWrapper extends HttpServletRequestWrapper {
  private final String body;

  public InterceptorRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    body = readBody(request);
  }

  public InterceptorRequestWrapper(HttpServletRequest request, String requestBody) {
    super(request);
    body = requestBody;
  }

  @Override
  public ServletInputStream getInputStream() {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return byteArrayInputStream.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
        // pass
      }

      @Override
      public int read() {
        return byteArrayInputStream.read();
      }
    };
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(this.getInputStream()));
  }

  public String getBody() {
    return this.body;
  }

  private String readBody(HttpServletRequest request) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    InputStream inputStream = request.getInputStream();
    if (inputStream != null) {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      char[] charBuffer = new char[128];
      int bytesRead;
      while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
        stringBuilder.append(charBuffer, 0, bytesRead);
      }
    }

    if (bufferedReader != null) {
      bufferedReader.close();
    }
    return stringBuilder.toString();
  }
}
