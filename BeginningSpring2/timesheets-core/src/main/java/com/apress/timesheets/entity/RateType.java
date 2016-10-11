package com.apress.timesheets.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
   @NamedQuery(name="findRateTypesOrdered",query="from RateType order by id")
})
public class RateType {
   private String id;

   public RateType() {
   }

   public RateType(final String id) {
      this.id = id;
   }

   @Id
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }
}
