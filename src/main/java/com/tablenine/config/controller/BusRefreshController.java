package com.tablenine.config.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.bus.endpoint.RefreshBusEndpoint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusRefreshController {

	private final RefreshBusEndpoint refreshBusEndpoint;

	@PostMapping("/custom-bus-refresh/{appKey}/{destination}")
	public void customBusRefresh(@PathVariable String destination) {
		// 여기에서 사용자 정의 로직을 구현합니다.
		// 예를 들어, destination 값을 기반으로 특정 서비스 인스턴스에 대한 설정 갱신을 수행할 수 있습니다.

		// Spring Cloud Bus의 RefreshBusEndpoint를 호출하여 모든 서비스 또는 특정 서비스를 대상으로 설정 갱신을 수행합니다.
		refreshBusEndpoint.busRefreshWithDestination(new String[]{destination});
//		refreshBusEndpoint.busRefresh();
	}

}
