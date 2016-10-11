package com.apress.coupling;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.core.io.Resource;

public class ResourceConsumer {
   private static final Logger log = Logger.getAnonymousLogger();
   private List<Resource> resources;

   public void describeResources() {
      for( final Resource resource : resources) {
         log.info(resource.getDescription());
      }
   }

   public List<Resource> getResources() {
      return resources;
   }

   public void setResources(List<Resource> resources) {
      this.resources = resources;
   }
}
