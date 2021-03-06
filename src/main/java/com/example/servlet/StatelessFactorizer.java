package com.example.servlet;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

@ThreadSafe // There is no state of class, object.
public class StatelessFactorizer implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(res, factors);
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {
    }

    private void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{};
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("111");
    }
}
