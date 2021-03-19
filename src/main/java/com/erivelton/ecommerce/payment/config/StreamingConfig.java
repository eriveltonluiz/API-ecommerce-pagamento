package com.erivelton.ecommerce.payment.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.erivelton.ecommerce.payment.streaming.CheckoutProcessor;

@SuppressWarnings("deprecation")
@Configuration
@EnableBinding(CheckoutProcessor.class)
public class StreamingConfig {

}
