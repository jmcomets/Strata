/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.credit.cds;

import static com.opengamma.strata.basics.currency.Currency.USD;
import static com.opengamma.strata.basics.date.BusinessDayConventions.FOLLOWING;
import static com.opengamma.strata.basics.date.DayCounts.ACT_360;
import static com.opengamma.strata.basics.date.HolidayCalendarIds.SAT_SUN;
import static com.opengamma.strata.basics.schedule.Frequency.P3M;
import static com.opengamma.strata.basics.schedule.StubConvention.SHORT_INITIAL;
import static com.opengamma.strata.collect.TestHelper.assertSerialization;
import static com.opengamma.strata.collect.TestHelper.coverBeanEquals;
import static com.opengamma.strata.collect.TestHelper.coverImmutableBean;
import static com.opengamma.strata.product.common.BuySell.BUY;
import static com.opengamma.strata.product.credit.cds.PaymentOnDefault.ACCRUED_PREMIUM;
import static com.opengamma.strata.product.credit.cds.ProtectionStartOfDay.BEGINNING;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.testng.annotations.Test;

import com.opengamma.strata.basics.StandardId;
import com.opengamma.strata.basics.currency.AdjustablePayment;
import com.opengamma.strata.basics.date.BusinessDayAdjustment;
import com.opengamma.strata.basics.date.DaysAdjustment;
import com.opengamma.strata.basics.date.HolidayCalendarId;
import com.opengamma.strata.basics.date.HolidayCalendarIds;
import com.opengamma.strata.product.TradeInfo;
import com.opengamma.strata.product.credit.cds.type.CdsQuoteConvention;

/**
 * Test {@link CdsCalibrationTrade} and {@link CdsQuote}.
 */
@Test
public class CdsCalibrationTradeTest {
  private static final HolidayCalendarId CALENDAR = HolidayCalendarIds.SAT_SUN;
  private static final DaysAdjustment SETTLE_DAY_ADJ = DaysAdjustment.ofBusinessDays(3, CALENDAR);
  private static final DaysAdjustment STEPIN_DAY_ADJ = DaysAdjustment.ofCalendarDays(1);
  private static final StandardId LEGAL_ENTITY = StandardId.of("OG", "ABC");
  private static final double COUPON = 0.05;
  private static final double NOTIONAL = 1.0e9;
  private static final LocalDate START_DATE = LocalDate.of(2013, 12, 20);
  private static final LocalDate END_DATE = LocalDate.of(2024, 9, 20);
  private static final BusinessDayAdjustment BUSS_ADJ = BusinessDayAdjustment.of(FOLLOWING, SAT_SUN);

  private static final Cds PRODUCT = Cds.of(BUY, LEGAL_ENTITY, USD, NOTIONAL, START_DATE, END_DATE, P3M, BUSS_ADJ,
      SHORT_INITIAL, COUPON, ACT_360, ACCRUED_PREMIUM, BEGINNING, STEPIN_DAY_ADJ, SETTLE_DAY_ADJ);
  private static final TradeInfo TRADE_INFO = TradeInfo.of(LocalDate.of(2014, 1, 9));
  private static final AdjustablePayment UPFRONT = AdjustablePayment.of(USD, NOTIONAL, LocalDate.of(2014, 1, 12));
  private static final CdsTrade TRADE = CdsTrade.builder()
      .product(PRODUCT)
      .upfrontFee(UPFRONT)
      .info(TRADE_INFO)
      .build();
  private static final CdsQuote QUOTE1 = CdsQuote.of(CdsQuoteConvention.POINTS_UPFRONT, 0.95);
  private static final CdsQuote QUOTE2 = CdsQuote.of(CdsQuoteConvention.QUOTED_SPREAD, 0.0155);
  private static final CdsQuote QUOTE3 = CdsQuote.of(CdsQuoteConvention.PAR_SPREAD, 0.012);

  //-------------------------------------------------------------------------
  public void test_of_quote() {
    assertEquals(QUOTE3.getQuoteConvention(), CdsQuoteConvention.PAR_SPREAD);
    assertEquals(QUOTE3.getQuotedValue(), 0.012);
  }

  //-------------------------------------------------------------------------
  public void coverage_quote() {
    coverImmutableBean(QUOTE2);
    coverBeanEquals(QUOTE2, QUOTE3);
  }

  public void test_serialization_quote() {
    assertSerialization(QUOTE1);
  }

  public void test_of_trade() {
    CdsCalibrationTrade test = CdsCalibrationTrade.of(TRADE, QUOTE1);
    assertEquals(test.getUnderlyingTrade(), TRADE);
    assertEquals(test.getQuote(), QUOTE1);
    assertEquals(test.getInfo(), TRADE.getInfo());
  }

  //-------------------------------------------------------------------------
  public void coverage_trade() {
    CdsCalibrationTrade test1 = CdsCalibrationTrade.of(TRADE, QUOTE1);
    coverImmutableBean(test1);
    CdsCalibrationTrade test2 = CdsCalibrationTrade.of(
        CdsTrade.builder()
            .product(PRODUCT)
            .info(TRADE_INFO)
            .build(),
        QUOTE2);
    coverBeanEquals(test1, test2);
  }

  public void test_serialization_trade() {
    CdsCalibrationTrade test = CdsCalibrationTrade.of(TRADE, QUOTE1);
    assertSerialization(test);
  }

}