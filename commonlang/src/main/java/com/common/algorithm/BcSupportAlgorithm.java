package com.common.algorithm;

public enum BcSupportAlgorithm {

    RSA_SHA256("SHA256WithRSA"),
    RSA_SHA512("SHA512WithRSA"),
    RSA_SHA1("SHA1WithRSA"),
    ECDSA("ECDSA");

    private String value;

    private BcSupportAlgorithm(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
