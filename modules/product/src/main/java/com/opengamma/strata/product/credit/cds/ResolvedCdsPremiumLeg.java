package com.opengamma.strata.product.credit.cds;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableList;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.product.common.PayReceive;

@BeanDefinition
public final class ResolvedCdsPremiumLeg
    implements ImmutableBean, Serializable {

  /**
   * Whether the leg is pay or receive.
   * <p>
   * A value of 'Pay' implies that the resulting amount is paid to the counterparty.
   * A value of 'Receive' implies that the resulting amount is received from the counterparty.
   * <p>
   * The value of this flag should match the signs of the payment period notionals.
   */
  @PropertyDefinition(validate = "notNull")
  private final PayReceive payReceive;
  /**
   * The periodic payments based on the fixed rate.
   * <p>
   * Each payment period represents part of the life-time of the leg.
   * In most cases, the periods do not overlap. However, since each payment period
   * is essentially independent the data model allows overlapping periods.
   */
  @PropertyDefinition(validate = "notEmpty")
  private final ImmutableList<CreditCouponPaymentPeriod> periodicPayments;
  /**
   * The day count convention applicable.
   * <p>
   * The conversion from dates to a numerical value is made based on this day count.
   * For the fixed bond, the day count convention is used to compute accrued interest.
   * <p>
   * Note that the year fraction of a coupon payment is computed based on the unadjusted
   * dates in the schedule.
   */
  @PropertyDefinition(validate = "notNull")
  private final DayCount dayCount;
  /**
   * Whether the accrued premium is paid in the event of a default.
   */
  @PropertyDefinition(validate = "notNull")
  private final boolean payAccruedOnDefault;

  // TODO accruedInterest computation

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedCdsPremiumLeg}.
   * @return the meta-bean, not null
   */
  public static ResolvedCdsPremiumLeg.Meta meta() {
    return ResolvedCdsPremiumLeg.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedCdsPremiumLeg.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ResolvedCdsPremiumLeg.Builder builder() {
    return new ResolvedCdsPremiumLeg.Builder();
  }

  private ResolvedCdsPremiumLeg(
      PayReceive payReceive,
      List<CreditCouponPaymentPeriod> periodicPayments,
      DayCount dayCount,
      boolean payAccruedOnDefault) {
    JodaBeanUtils.notNull(payReceive, "payReceive");
    JodaBeanUtils.notEmpty(periodicPayments, "periodicPayments");
    JodaBeanUtils.notNull(dayCount, "dayCount");
    JodaBeanUtils.notNull(payAccruedOnDefault, "payAccruedOnDefault");
    this.payReceive = payReceive;
    this.periodicPayments = ImmutableList.copyOf(periodicPayments);
    this.dayCount = dayCount;
    this.payAccruedOnDefault = payAccruedOnDefault;
  }

  @Override
  public ResolvedCdsPremiumLeg.Meta metaBean() {
    return ResolvedCdsPremiumLeg.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether the leg is pay or receive.
   * <p>
   * A value of 'Pay' implies that the resulting amount is paid to the counterparty.
   * A value of 'Receive' implies that the resulting amount is received from the counterparty.
   * <p>
   * The value of this flag should match the signs of the payment period notionals.
   * @return the value of the property, not null
   */
  public PayReceive getPayReceive() {
    return payReceive;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the periodic payments based on the fixed rate.
   * <p>
   * Each payment period represents part of the life-time of the leg.
   * In most cases, the periods do not overlap. However, since each payment period
   * is essentially independent the data model allows overlapping periods.
   * @return the value of the property, not empty
   */
  public ImmutableList<CreditCouponPaymentPeriod> getPeriodicPayments() {
    return periodicPayments;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the day count convention applicable.
   * <p>
   * The conversion from dates to a numerical value is made based on this day count.
   * For the fixed bond, the day count convention is used to compute accrued interest.
   * <p>
   * Note that the year fraction of a coupon payment is computed based on the unadjusted
   * dates in the schedule.
   * @return the value of the property, not null
   */
  public DayCount getDayCount() {
    return dayCount;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether the accrued premium is paid in the event of a default.
   * @return the value of the property, not null
   */
  public boolean isPayAccruedOnDefault() {
    return payAccruedOnDefault;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedCdsPremiumLeg other = (ResolvedCdsPremiumLeg) obj;
      return JodaBeanUtils.equal(payReceive, other.payReceive) &&
          JodaBeanUtils.equal(periodicPayments, other.periodicPayments) &&
          JodaBeanUtils.equal(dayCount, other.dayCount) &&
          (payAccruedOnDefault == other.payAccruedOnDefault);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(payReceive);
    hash = hash * 31 + JodaBeanUtils.hashCode(periodicPayments);
    hash = hash * 31 + JodaBeanUtils.hashCode(dayCount);
    hash = hash * 31 + JodaBeanUtils.hashCode(payAccruedOnDefault);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("ResolvedCdsPremiumLeg{");
    buf.append("payReceive").append('=').append(payReceive).append(',').append(' ');
    buf.append("periodicPayments").append('=').append(periodicPayments).append(',').append(' ');
    buf.append("dayCount").append('=').append(dayCount).append(',').append(' ');
    buf.append("payAccruedOnDefault").append('=').append(JodaBeanUtils.toString(payAccruedOnDefault));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedCdsPremiumLeg}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code payReceive} property.
     */
    private final MetaProperty<PayReceive> payReceive = DirectMetaProperty.ofImmutable(
        this, "payReceive", ResolvedCdsPremiumLeg.class, PayReceive.class);
    /**
     * The meta-property for the {@code periodicPayments} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableList<CreditCouponPaymentPeriod>> periodicPayments = DirectMetaProperty.ofImmutable(
        this, "periodicPayments", ResolvedCdsPremiumLeg.class, (Class) ImmutableList.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCount> dayCount = DirectMetaProperty.ofImmutable(
        this, "dayCount", ResolvedCdsPremiumLeg.class, DayCount.class);
    /**
     * The meta-property for the {@code payAccruedOnDefault} property.
     */
    private final MetaProperty<Boolean> payAccruedOnDefault = DirectMetaProperty.ofImmutable(
        this, "payAccruedOnDefault", ResolvedCdsPremiumLeg.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "payReceive",
        "periodicPayments",
        "dayCount",
        "payAccruedOnDefault");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          return payReceive;
        case -367345944:  // periodicPayments
          return periodicPayments;
        case 1905311443:  // dayCount
          return dayCount;
        case -43782841:  // payAccruedOnDefault
          return payAccruedOnDefault;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedCdsPremiumLeg.Builder builder() {
      return new ResolvedCdsPremiumLeg.Builder();
    }

    @Override
    public Class<? extends ResolvedCdsPremiumLeg> beanType() {
      return ResolvedCdsPremiumLeg.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code payReceive} property.
     * @return the meta-property, not null
     */
    public MetaProperty<PayReceive> payReceive() {
      return payReceive;
    }

    /**
     * The meta-property for the {@code periodicPayments} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ImmutableList<CreditCouponPaymentPeriod>> periodicPayments() {
      return periodicPayments;
    }

    /**
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public MetaProperty<DayCount> dayCount() {
      return dayCount;
    }

    /**
     * The meta-property for the {@code payAccruedOnDefault} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> payAccruedOnDefault() {
      return payAccruedOnDefault;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          return ((ResolvedCdsPremiumLeg) bean).getPayReceive();
        case -367345944:  // periodicPayments
          return ((ResolvedCdsPremiumLeg) bean).getPeriodicPayments();
        case 1905311443:  // dayCount
          return ((ResolvedCdsPremiumLeg) bean).getDayCount();
        case -43782841:  // payAccruedOnDefault
          return ((ResolvedCdsPremiumLeg) bean).isPayAccruedOnDefault();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ResolvedCdsPremiumLeg}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ResolvedCdsPremiumLeg> {

    private PayReceive payReceive;
    private List<CreditCouponPaymentPeriod> periodicPayments = ImmutableList.of();
    private DayCount dayCount;
    private boolean payAccruedOnDefault;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ResolvedCdsPremiumLeg beanToCopy) {
      this.payReceive = beanToCopy.getPayReceive();
      this.periodicPayments = beanToCopy.getPeriodicPayments();
      this.dayCount = beanToCopy.getDayCount();
      this.payAccruedOnDefault = beanToCopy.isPayAccruedOnDefault();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          return payReceive;
        case -367345944:  // periodicPayments
          return periodicPayments;
        case 1905311443:  // dayCount
          return dayCount;
        case -43782841:  // payAccruedOnDefault
          return payAccruedOnDefault;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          this.payReceive = (PayReceive) newValue;
          break;
        case -367345944:  // periodicPayments
          this.periodicPayments = (List<CreditCouponPaymentPeriod>) newValue;
          break;
        case 1905311443:  // dayCount
          this.dayCount = (DayCount) newValue;
          break;
        case -43782841:  // payAccruedOnDefault
          this.payAccruedOnDefault = (Boolean) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public ResolvedCdsPremiumLeg build() {
      return new ResolvedCdsPremiumLeg(
          payReceive,
          periodicPayments,
          dayCount,
          payAccruedOnDefault);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets whether the leg is pay or receive.
     * <p>
     * A value of 'Pay' implies that the resulting amount is paid to the counterparty.
     * A value of 'Receive' implies that the resulting amount is received from the counterparty.
     * <p>
     * The value of this flag should match the signs of the payment period notionals.
     * @param payReceive  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder payReceive(PayReceive payReceive) {
      JodaBeanUtils.notNull(payReceive, "payReceive");
      this.payReceive = payReceive;
      return this;
    }

    /**
     * Sets the periodic payments based on the fixed rate.
     * <p>
     * Each payment period represents part of the life-time of the leg.
     * In most cases, the periods do not overlap. However, since each payment period
     * is essentially independent the data model allows overlapping periods.
     * @param periodicPayments  the new value, not empty
     * @return this, for chaining, not null
     */
    public Builder periodicPayments(List<CreditCouponPaymentPeriod> periodicPayments) {
      JodaBeanUtils.notEmpty(periodicPayments, "periodicPayments");
      this.periodicPayments = periodicPayments;
      return this;
    }

    /**
     * Sets the {@code periodicPayments} property in the builder
     * from an array of objects.
     * @param periodicPayments  the new value, not empty
     * @return this, for chaining, not null
     */
    public Builder periodicPayments(CreditCouponPaymentPeriod... periodicPayments) {
      return periodicPayments(ImmutableList.copyOf(periodicPayments));
    }

    /**
     * Sets the day count convention applicable.
     * <p>
     * The conversion from dates to a numerical value is made based on this day count.
     * For the fixed bond, the day count convention is used to compute accrued interest.
     * <p>
     * Note that the year fraction of a coupon payment is computed based on the unadjusted
     * dates in the schedule.
     * @param dayCount  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder dayCount(DayCount dayCount) {
      JodaBeanUtils.notNull(dayCount, "dayCount");
      this.dayCount = dayCount;
      return this;
    }

    /**
     * Sets whether the accrued premium is paid in the event of a default.
     * @param payAccruedOnDefault  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder payAccruedOnDefault(boolean payAccruedOnDefault) {
      JodaBeanUtils.notNull(payAccruedOnDefault, "payAccruedOnDefault");
      this.payAccruedOnDefault = payAccruedOnDefault;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("ResolvedCdsPremiumLeg.Builder{");
      buf.append("payReceive").append('=').append(JodaBeanUtils.toString(payReceive)).append(',').append(' ');
      buf.append("periodicPayments").append('=').append(JodaBeanUtils.toString(periodicPayments)).append(',').append(' ');
      buf.append("dayCount").append('=').append(JodaBeanUtils.toString(dayCount)).append(',').append(' ');
      buf.append("payAccruedOnDefault").append('=').append(JodaBeanUtils.toString(payAccruedOnDefault));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
