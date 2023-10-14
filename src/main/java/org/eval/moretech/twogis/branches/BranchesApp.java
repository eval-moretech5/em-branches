package org.eval.moretech.twogis.branches;

import org.eval.moretech.twogis.branches.config.AtmBankProperties;
import org.eval.moretech.twogis.branches.config.PlacesRequestProperties;
import org.eval.moretech.twogis.branches.config.VtbApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableConfigurationProperties({VtbApiProperties.class, AtmBankProperties.class, PlacesRequestProperties.class})
public class BranchesApp {

	public static void main(String[] args) {
		SpringApplication.run(BranchesApp.class, args);
	}

}
