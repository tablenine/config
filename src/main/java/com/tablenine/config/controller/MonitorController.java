package com.tablenine.config.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.config.monitor.PropertyPathEndpoint;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MonitorController {
	private final PropertyPathEndpoint endpoint;

	@PostMapping("/v1/tablenine/{appkey}/custom-monitor/{applicationName}")
	public Set<String> handleCustomMonitor(@RequestHeader HttpHeaders headers, @PathVariable String appkey, @PathVariable String applicationName) {
		Map<String, Object> request = new HashMap<>();
		String path = STR."\{applicationName}:**:\{appkey}";
		request.put("path", path);

		return endpoint.notifyByPath(headers, request);
	}
}
