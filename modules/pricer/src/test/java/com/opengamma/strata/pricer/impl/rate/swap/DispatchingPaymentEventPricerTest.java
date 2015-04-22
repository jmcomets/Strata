/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.pricer.impl.rate.swap;

import static com.opengamma.strata.collect.TestHelper.assertThrowsIllegalArg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.opengamma.strata.finance.rate.swap.FxResetNotionalExchange;
import com.opengamma.strata.finance.rate.swap.NotionalExchange;
import com.opengamma.strata.finance.rate.swap.PaymentEvent;
import com.opengamma.strata.pricer.RatesProvider;
import com.opengamma.strata.pricer.impl.MockRatesProvider;
import com.opengamma.strata.pricer.rate.swap.PaymentEventPricer;
import com.opengamma.strata.pricer.rate.swap.SwapDummyData;

/**
 * Test.
 */
@Test
public class DispatchingPaymentEventPricerTest {

  private static final RatesProvider MOCK_PROV = new MockRatesProvider();
  private static final PaymentEventPricer<NotionalExchange> MOCK_NOTIONAL_EXG = mock(PaymentEventPricer.class);
  private static final PaymentEventPricer<FxResetNotionalExchange> MOCK_FX_NOTIONAL_EXG =
      mock(PaymentEventPricer.class);

  //-------------------------------------------------------------------------
  public void test_presentValue_NotionalExchange() {
    double expected = 0.0123d;
    PaymentEventPricer<NotionalExchange> mockCalledFn = mock(PaymentEventPricer.class);
    when(mockCalledFn.presentValue(MOCK_PROV, SwapDummyData.NOTIONAL_EXCHANGE_REC_GBP))
        .thenReturn(expected);
    DispatchingPaymentEventPricer test = new DispatchingPaymentEventPricer(
        mockCalledFn,
        MOCK_FX_NOTIONAL_EXG);
    assertEquals(test.presentValue(MOCK_PROV, SwapDummyData.NOTIONAL_EXCHANGE_REC_GBP), expected, 0d);
  }

  public void test_presentValue_FxResetNotionalExchange() {
    double expected = 0.0123d;
    PaymentEventPricer<FxResetNotionalExchange> mockCalledFn = mock(PaymentEventPricer.class);
    when(mockCalledFn.presentValue(MOCK_PROV, SwapDummyData.FX_RESET_NOTIONAL_EXCHANGE))
        .thenReturn(expected);
    DispatchingPaymentEventPricer test = new DispatchingPaymentEventPricer(
        MOCK_NOTIONAL_EXG,
        mockCalledFn);
    assertEquals(test.presentValue(MOCK_PROV, SwapDummyData.FX_RESET_NOTIONAL_EXCHANGE), expected, 0d);
  }

  public void test_presentValue_unknownType() {
    PaymentEvent mockPaymentEvent = mock(PaymentEvent.class);
    DispatchingPaymentEventPricer test = DispatchingPaymentEventPricer.DEFAULT;
    assertThrowsIllegalArg(() -> test.presentValue(MOCK_PROV, mockPaymentEvent));
  }

  //-------------------------------------------------------------------------
  public void test_futureValue_NotionalExchange() {
    double expected = 0.0123d;
    PaymentEventPricer<NotionalExchange> mockCalledFn = mock(PaymentEventPricer.class);
    when(mockCalledFn.futureValue(MOCK_PROV, SwapDummyData.NOTIONAL_EXCHANGE_REC_GBP))
        .thenReturn(expected);
    DispatchingPaymentEventPricer test = new DispatchingPaymentEventPricer(
        mockCalledFn,
        MOCK_FX_NOTIONAL_EXG);
    assertEquals(test.futureValue(MOCK_PROV, SwapDummyData.NOTIONAL_EXCHANGE_REC_GBP), expected, 0d);
  }

  public void test_futureValue_FxResetNotionalExchange() {
    double expected = 0.0123d;
    PaymentEventPricer<FxResetNotionalExchange> mockCalledFn = mock(PaymentEventPricer.class);
    when(mockCalledFn.futureValue(MOCK_PROV, SwapDummyData.FX_RESET_NOTIONAL_EXCHANGE))
        .thenReturn(expected);
    DispatchingPaymentEventPricer test = new DispatchingPaymentEventPricer(
        MOCK_NOTIONAL_EXG,
        mockCalledFn);
    assertEquals(test.futureValue(MOCK_PROV, SwapDummyData.FX_RESET_NOTIONAL_EXCHANGE), expected, 0d);
  }

  public void test_futureValue_unknownType() {
    PaymentEvent mockPaymentEvent = mock(PaymentEvent.class);
    DispatchingPaymentEventPricer test = DispatchingPaymentEventPricer.DEFAULT;
    assertThrowsIllegalArg(() -> test.futureValue(MOCK_PROV, mockPaymentEvent));
  }

}
