package com.esteeminfo.proauto.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MachineUsageDTO {

	private int id;

	private String actualTime;

	private String authouredby;

	private int quantity;

	private String shift;

	private Date useDate;

	private Machine machine;

	private Customer customer;

	private PurchaseOrder purchaseOrder;

	private Jobcard jobcard;

	private JobcardTask jobcardTask;

	private Employee assignee;

	private Employee programmer;

	private Purchase purchase;

}
