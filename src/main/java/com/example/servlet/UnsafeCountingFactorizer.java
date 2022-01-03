package com.example.servlet;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    private ServletConfig config;
    private long count = 0;

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
        BigInteger i = extratFromRequest(req);
        BigInteger[] factors = factor(i);
        count++;    // Not Thread Safe
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

    private BigInteger extratFromRequest(ServletRequest req) {
        return new BigInteger("111");
    }
}
