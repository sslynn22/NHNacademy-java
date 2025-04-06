package spring.boot.core.day3.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.boot.core.day3.demo.bean.OrderProcessorBean;
import spring.boot.core.day3.demo.bean.OrderReceiverBean;
import spring.boot.core.day3.demo.bean.PaymentProcessorBean;
import spring.boot.core.day3.demo.properties.CookProperties;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Day3ApplicationTests {

	@Mock
	OrderReceiverBean orderReceiverBean;

	@Mock
	PaymentProcessorBean paymentProcessorBean;

	@Mock
	CookProperties cookProperties;

	@InjectMocks
	OrderProcessorBean orderProcessorBean;

	@Test
	void contextLoads() {
		when(cookProperties.getOrder()).thenReturn("order food");
		doNothing().when(orderReceiverBean).receiveOrder("order food");

		orderProcessorBean.processOrder();

		verify(orderReceiverBean, times(1)).receiveOrder("order food");
	}
}
