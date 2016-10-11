package com.apress.timesheets.entity;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Rate {
   private RateType rateType;

   private BigDecimal rate;

   public Rate() {
   }

   public Rate(final RateType rateType, final BigDecimal rate) {
      this.rateType = rateType;
      this.rate = rate;
   }

   public BigDecimal getRate() {
      return rate;
   }

   public void setRate(BigDecimal rate) {
      this.rate = rate;
   }

   @ManyToOne
   public RateType getRateType() {
      return rateType;
   }

   public void setRateType(RateType rateType) {
      this.rateType = rateType;
   }
}
