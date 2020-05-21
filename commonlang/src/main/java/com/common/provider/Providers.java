package com.common.provider;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;

public enum Providers {
    BC("bc", new BouncyCastleProvider());
    private String name;
    private Provider provider;

    Providers(String name, Provider provider) {
        this.name = name;
        this.provider = provider;
    }

    public static Provider getProviderOfName(String name) {
        for (Providers providers : values()) {
            if (providers.name.equalsIgnoreCase(name)) {
                return providers.provider;
            }
        }
        return null;
    }
}
