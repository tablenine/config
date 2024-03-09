package com.tablenine.config.output.port;

import com.tablenine.config.domain.GithubInfo;
import org.springframework.stereotype.Component;

@Component
public class GithubMapper implements GithubRepositoryPort {
	@Override
	public GithubInfo getGithubInfo(String application, String profile, String label) {

		if ("app1".equals(application)) {
			return GithubInfo.builder()
					.uri("https://github.com/tablenine/test-config.git")
					.userName("tablenine")
					.defaultLabel("master")
					.cloneOnStart(true)
					.build();
		} else {
			return GithubInfo.builder()
					.uri("https://github.com/tablenine/test-config2.git")
					.userName("tablenine")
					.defaultLabel("master")
					.cloneOnStart(true)
					.build();
		}
	}
}
