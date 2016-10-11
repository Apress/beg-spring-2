package com.apress.timesheets.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
   @NamedQuery(name="findUserRole",query="from UserRole where roleName = :roleName"),
   @NamedQuery(name="findUserRoles",query="from UserRole order by roleName")
})
public class UserRole implements Serializable {
   public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
   public static final String ROLE_USER = "ROLE_USER";
   public static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRATOR";

   private static final long serialVersionUID = 0L;
   private Long id;
   private String roleName;

   public UserRole() {
   }

   public UserRole(final String roleName) {
      this.roleName = roleName;
   }

   @Id
   @GeneratedValue
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Column(nullable = false, unique = true)
   public String getRoleName() {
      return roleName;
   }

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }
}
