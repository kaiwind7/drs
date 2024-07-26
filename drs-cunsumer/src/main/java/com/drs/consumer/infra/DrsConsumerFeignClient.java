package com.drs.consumer.infra;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "DrsConsumerFeignClient", url = "")
public interface DrsConsumerFeignClient {
}
