package com.example.servlet;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
    private ServletConfig config;
    private final AtomicReference<BigInteger> lastNumber;
    private final AtomicReference<BigInteger[]> lastFactors;

    public UnsafeCachingFactorizer() {
        lastNumber = new AtomicReference<>();
        lastFactors = new AtomicReference<>();
    }

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
        if (i.equals(lastNumber.get())) {
            return lastFactors.get();
        }

        BigInteger[] factorResult = new BigInteger[] {};
        lastNumber.set(i);
        lastFactors.set(factorResult);  // two atomic reference operation is not atomic and not thread safe.
        return factorResult;
    }

    private BigInteger extratFromRequest(ServletRequest req) {
        return new BigInteger("111");
    }
}
