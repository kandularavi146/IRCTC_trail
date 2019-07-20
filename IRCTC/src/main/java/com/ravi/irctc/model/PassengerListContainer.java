package com.ravi.irctc.model;

import java.util.LinkedList;
import java.util.List;

public class PassengerListContainer {

	private LinkedList<Passenger> linkedList=new LinkedList<Passenger>();

	public PassengerListContainer(List<Passenger> passengerList) {
		super();
		this.linkedList = (LinkedList<Passenger>) passengerList;
	}

	public PassengerListContainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Passenger> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<Passenger> linkedList) {
		this.linkedList = linkedList;
	}
	
}
