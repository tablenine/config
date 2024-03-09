package com.tablenine.config;

import java.util.concurrent.*;

public class ShutdownOnSuccessExample {
	public static void main(String[] args) {
		// StructuredTaskScope.ShutdownOnSuccess를 사용하여 스코프 생성
		try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
			// 여러 병렬 작업 제출
			StructuredTaskScope.Subtask<String> task1 = scope.fork(() -> task("Task 1", 2)); // 2초 후 완료
			StructuredTaskScope.Subtask<String> task2 = scope.fork(() -> task("Task 2", 3)); // 3초 후 완료
			StructuredTaskScope.Subtask<String> task3 = scope.fork(() -> task("Task 3", 10)); // 1초 후 완료 - 이 작업이 가장 먼저 완료될 것

			// 첫 번째로 성공적으로 완료된 작업 기다림
			scope.join();
			// 성공적으로 완료된 작업의 결과 얻기
			String result = scope.result().toString();

			System.out.println("First successful result: " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	// 시뮬레이션된 작업을 수행하는 메서드
	static String task(String name, int seconds) throws InterruptedException {
		System.out.println(name + " started");
		TimeUnit.SECONDS.sleep(seconds); // 작업 시뮬레이션
		System.out.println(name + " completed");
		return name + " result";
	}
}
