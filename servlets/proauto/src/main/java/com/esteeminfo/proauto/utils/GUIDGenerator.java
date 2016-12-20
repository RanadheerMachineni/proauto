package com.esteeminfo.proauto.utils;

import java.util.UUID;

public class GUIDGenerator {

     public String getNextGuid() {
    	 UUID uuid = UUID.randomUUID();
    	 System.out.println("in GUIDGenerator "+uuid);
    	 return uuid.toString();
     }
}
