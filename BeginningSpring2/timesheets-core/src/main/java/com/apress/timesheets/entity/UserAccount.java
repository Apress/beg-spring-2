package com.apress.timesheets.entity;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@NamedQueries( {
      @NamedQuery(name = "getUserAccountByName", 
               query = "from UserAccount where accountName = :name"),
      @NamedQuery(name = "listUserAccountByName", 
               query = "from UserAccount order by accountName")
})
public class UserAccount implements Serializable {
   private static final long serialVersionUID = 0L;

   private Long id;
   private String accountName;
   private Set<UserRole> roles = new HashSet<UserRole>();

   public UserAccount() {
   }

   public UserAccount(final String accountName) {
      this.accountName = accountName;
   }

   @Id
   @GeneratedValue
   public Long getId() {
      return id;
   }

   public void setId(final Long id) {
      this.id = id;
   }

   @ManyToMany(fetch = LAZY, cascade = PERSIST)
   @JoinTable(name = "account_role", 
            joinColumns = { @JoinColumn(name = "user") }, 
            inverseJoinColumns = { @JoinColumn(name = "role") })
   public Set<UserRole> getRoles() {
      return roles;
   }

   public void setRoles(final Set<UserRole> roles) {
      this.roles = roles;
   }

   @Column(unique = true, nullable = false)
   public String getAccountName() {
      return accountName;
   }

   public void setAccountName(String accountName) {
      this.accountName = accountName;
   }
}
