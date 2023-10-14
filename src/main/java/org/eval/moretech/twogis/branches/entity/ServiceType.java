package org.eval.moretech.twogis.branches.entity;

import java.util.*;

public enum ServiceType {
    GET_CREDIT,
    GET_MORTGAGE,
    GET_CAR_CREDIT,
    PAY_LOAN,
    GET_CASH,
    PUT_CASH,
    SAFE_BOX,
    INVESTMENT;

    public static final Set<ServiceType> DEFAULT = Set.of(
        PAY_LOAN,
        GET_CASH,
        PUT_CASH
    );

    public static final Set<ServiceType> NOT_DEFAULT;

    static {
        Set<ServiceType> serviceTypes = new HashSet<>(Set.of(values()));
        serviceTypes.removeAll(DEFAULT);
        NOT_DEFAULT = Collections.unmodifiableSet(serviceTypes);
    }
}
