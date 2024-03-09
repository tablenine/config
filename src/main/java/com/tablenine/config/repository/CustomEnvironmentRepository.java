package com.tablenine.config.repository;

import com.tablenine.config.domain.GithubInfo;
import com.tablenine.config.output.port.GithubRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.JGitEnvironmentProperties;
import org.springframework.cloud.config.server.environment.JGitEnvironmentRepository;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CustomEnvironmentRepository implements EnvironmentRepository {
	private final ConcurrentHashMap<String, EnvironmentRepository> repositoryCache = new ConcurrentHashMap<>();

	private final ConfigurableEnvironment environment;
	private final GithubRepositoryPort githubRepositoryPort;

	@Override
	public Environment findOne(String application, String profile, String label) {
		String cacheKey = application + ":" + profile + ":" + label;

		// 캐시에서 해당 요청에 대한 delegate 조회
		EnvironmentRepository delegate = repositoryCache.computeIfAbsent(cacheKey, key -> {
			// 캐시에 없는 경우, 새로운 delegate 생성 로직
			return createDelegate(application, profile, label);
		});

		// delegate를 사용하여 환경 정보 조회
		return delegate.findOne(application, profile, label);
	}

	@Override
	public Environment findOne(String application, String profile, String label, boolean includeOrigin) {
		return EnvironmentRepository.super.findOne(application, profile, label, includeOrigin);
	}

	private EnvironmentRepository createDelegate(String application, String profile, String label) {
		// JGitEnvironmentProperties 인스턴스 생성 및 구성
		JGitEnvironmentProperties properties = new JGitEnvironmentProperties();
		GithubInfo githubInfo = githubRepositoryPort.getGithubInfo(application, profile, label);
		properties.setUri(githubInfo.getUri());
		properties.setCloneOnStart(githubInfo.isCloneOnStart());
		properties.setUsername(githubInfo.getUserName());
		properties.setPassword(githubInfo.getPassword());
		properties.setDefaultLabel(githubInfo.getDefaultLabel());

		// JGitEnvironmentRepository 인스턴스 생성 및 초기화
		JGitEnvironmentRepository repository = new JGitEnvironmentRepository(environment, properties, null);

		// 추가 초기화 로직이 필요한 경우 여기에 구현
		// 예: repository.setSearchPaths(new String[]{"config"});

		return repository;
	}
}
