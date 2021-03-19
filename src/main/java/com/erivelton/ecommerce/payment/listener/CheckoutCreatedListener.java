package com.erivelton.ecommerce.payment.listener;

import java.util.UUID;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.erivelton.ecommerce.payment.event.CheckoutCreatedEvent;
import com.erivelton.ecommerce.payment.event.PaymentCreatedEvent;
import com.erivelton.ecommerce.payment.streaming.CheckoutProcessor;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {

	private final CheckoutProcessor checkoutProcessor;
	
	@StreamListener(CheckoutProcessor.INPUT)
	public void handler(CheckoutCreatedEvent event) {
		final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
				.setCheckoutCode(event.getCheckoutCode())
				.setCheckoutStatus(event.getStatus())
				.setPaymentCode(UUID.randomUUID().toString())
				.build();
		checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
	}
}
