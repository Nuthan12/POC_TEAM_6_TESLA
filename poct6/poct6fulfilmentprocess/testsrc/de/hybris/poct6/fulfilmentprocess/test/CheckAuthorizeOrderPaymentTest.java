/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.poct6.fulfilmentprocess.test;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction.Transition;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.poct6.fulfilmentprocess.actions.order.CheckAuthorizeOrderPaymentAction;

import java.util.Arrays;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class CheckAuthorizeOrderPaymentTest
{
	@Mock
	private ModelService modelService;

	@InjectMocks
	private final CheckAuthorizeOrderPaymentAction checkAuthorizeOrderPayment = new CheckAuthorizeOrderPaymentAction();

	@Test
	public void testExecuteActionOK() throws RetryLaterException, Exception
	{
		final OrderProcessModel businessProcessModel = new OrderProcessModel();

		final OrderModel order = new OrderModel();
		final PaymentTransactionModel paymentTransaction = new PaymentTransactionModel();
		final PaymentTransactionEntryModel entry = new PaymentTransactionEntryModel();
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		paymentTransaction.setEntries(Arrays.asList(entry));
		businessProcessModel.setOrder(order);
		order.setPaymentTransactions(Arrays.asList(paymentTransaction));

		Assertions.assertThat(checkAuthorizeOrderPayment.executeAction(businessProcessModel)).isEqualTo(Transition.OK);
	}

	@Test
	public void testExecuteActionNOK() throws RetryLaterException, Exception
	{
		final OrderProcessModel businessProcessModel = new OrderProcessModel();

		final OrderModel order = new OrderModel();
		final PaymentTransactionModel paymentTransaction = new PaymentTransactionModel();
		final PaymentTransactionEntryModel entry = new PaymentTransactionEntryModel();
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTransactionStatus(TransactionStatus.REJECTED.name());
		paymentTransaction.setEntries(Arrays.asList(entry));
		businessProcessModel.setOrder(order);
		order.setPaymentTransactions(Arrays.asList(paymentTransaction));
		Assertions.assertThat(checkAuthorizeOrderPayment.executeAction(businessProcessModel)).isEqualTo(Transition.NOK);
	}
}
