package com.tablenine.config.controller;

import com.tablenine.config.repository.CustomEnvironmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigServerController {
	private final CustomEnvironmentRepository customEnvironmentRepository;

	@GetMapping("/custom/{appKey}/{application}/{profile}")
	public Environment customEnvironmentWithoutLabel(@PathVariable String appKey, @PathVariable String application, @PathVariable String profile) {
		// 여기에서 커스텀 로직을 추가하거나,
		// 기존 EnvironmentRepository를 사용하여 환경 설정 정보를 조회하고 반환할 수 있습니다.
		return customEnvironmentRepository.findOne(application, profile, "master");
	}

	@GetMapping("/custom/{appKey}/{application}/{profile}/{label}")
	public Environment customEnvironment(@PathVariable String appKey, @PathVariable String application, @PathVariable String profile, @PathVariable String label) {
		// 여기에서 커스텀 로직을 추가하거나,
		// 기존 EnvironmentRepository를 사용하여 환경 설정 정보를 조회하고 반환할 수 있습니다.
		return customEnvironmentRepository.findOne(application, profile, label);
	}
}
