package org.eval.moretech.twogis.branches.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "atm.bank.titles")
public class AtmBankProperties extends HashMap<String, String> implements Map<String, String> {
}
