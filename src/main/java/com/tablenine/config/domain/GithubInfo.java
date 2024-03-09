package com.tablenine.config.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GithubInfo {
	private String uri;
	private String userName;
	private String password;
	private String defaultLabel;
	private boolean cloneOnStart;
}
