package com.apress.coupling.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

public class PropertyTypeExample implements InitializingBean 
{
   private Set<String> set;
   private Map<String,List<String>> map;
   private List<String> list;
   private String text;
   private RefExample ref;
   
   public PropertyTypeExample() {
   }
   
   public List<String> getList() {
      return list;
   }

   @Required
   public void setList(final List<String> list) {
      this.list = list;
   }

   public Map<String, List<String>> getMap() {
      return map;
   }

   @Required
   public void setMap(final Map<String, List<String>> map) {
      this.map = map;
   }

   public Set<String> getSet() {
      return set;
   }

   @Required
   public void setSet(final Set<String> set) {
      this.set = set;
   }

   public String getText() {
      return text;
   }

   @Required
   public void setText(final String text) {
      this.text = text;
   }

   public void afterPropertiesSet() 
      throws Exception
   {
      if( set  == null ) 
         throw new NullPointerException("set property is null");
      if( map  == null ) 
         throw new NullPointerException("map property is null");
      if( list == null ) 
         throw new NullPointerException("list property is null");
      if( text == null ) 
         throw new NullPointerException("text property is null");
      if( ref == null ) 
         throw new NullPointerException("ref property is null");
   }
   
   public RefExample getRef() {
      return ref;
   }

   public void setRef(RefExample ref) {
      this.ref = ref;
   }

   public void dump() {
      System.out.println("Set");
      for( final String item : set ) {
         System.out.print(item);
         System.out.print(", ");
      }
      System.out.println();

      System.out.println();
      System.out.println("Map");
      for( final String key : map.keySet() ) {
         System.out.print(key);
         System.out.print(": ");
         for( final String value : map.get(key)) {
            System.out.print(value);
            System.out.print(", ");
         }
         System.out.println();
      }

      System.out.println();
      System.out.println("List");
      for( final String item : list ) {
         System.out.print(item);
         System.out.print(", ");
      }
      System.out.println();

      System.out.println();
      System.out.println("Text");
      System.out.println(text);
      
      System.out.println();
      System.out.println("Ref");
      System.out.println(ref);
   }
}
