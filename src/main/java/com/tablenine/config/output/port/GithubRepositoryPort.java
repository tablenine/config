package com.tablenine.config.output.port;

import com.tablenine.config.domain.GithubInfo;

public interface GithubRepositoryPort {
	GithubInfo getGithubInfo(String application, String profile, String label);
}
