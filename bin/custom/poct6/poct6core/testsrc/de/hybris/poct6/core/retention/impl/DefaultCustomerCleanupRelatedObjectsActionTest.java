/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.poct6.core.retention.impl;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.enums.RetentionState;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processing.model.FlexibleSearchRetentionRuleModel;
import de.hybris.platform.retention.ItemToCleanup;
import de.hybris.platform.retention.hook.ItemCleanupHook;
import de.hybris.platform.retention.job.AfterRetentionCleanupJobPerformable;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * The Class DefaultCustomerCleanupRelatedObjectsActionTest.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultCustomerCleanupRelatedObjectsActionTest
{
	@InjectMocks
	private final DefaultCustomerCleanupRelatedObjectsAction cleanupAction = new DefaultCustomerCleanupRelatedObjectsAction();

	@Mock
	private ItemCleanupHook customerCleanupHook;
	@Mock
	private ModelService modelService;

	private FlexibleSearchRetentionRuleModel rule;
	private ItemToCleanup item;
	private AfterRetentionCleanupJobPerformable retentionJob;


	@Before
	public void setup()
	{
		cleanupAction.setItemCleanupHooks(Collections.singletonList(customerCleanupHook));
		rule = new FlexibleSearchRetentionRuleModel();
		item = mock(ItemToCleanup.class);
		retentionJob = new AfterRetentionCleanupJobPerformable();
	}

	@Test
	public void shouldCleanupAndInvokeHooks()
	{
		final CustomerModel customerModel = mock(CustomerModel.class);
		given(modelService.get(nullable(PK.class))).willReturn(customerModel);

		cleanupAction.cleanup(retentionJob, rule, item);
		verify(customerCleanupHook).cleanupRelatedObjects(customerModel);
		verify(customerModel).setRetentionState(RetentionState.PROCESSED);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldNotCleanupIfItemTypeIsNotCustomerModel()
	{
		final AddressModel addressModel = new AddressModel();
		given(modelService.get(nullable(PK.class))).willReturn(addressModel);
		cleanupAction.cleanup(retentionJob, rule, item);
	}
}
