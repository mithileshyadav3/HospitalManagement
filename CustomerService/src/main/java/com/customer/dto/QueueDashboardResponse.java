package com.customer.dto;

public class QueueDashboardResponse {
    private long totalAppointments;
    private long waiting;
   
    private long inProgress;
    private long completed;
    
    private long cancelled;

	public long getTotalAppointments() {
		return totalAppointments;
	}

	public void setTotalAppointments(long totalAppointments) {
		this.totalAppointments = totalAppointments;
	}

	public long getWaiting() {
		return waiting;
	}

	public void setWaiting(long waiting) {
		this.waiting = waiting;
	}

	public long getInProgress() {
		return inProgress;
	}

	public void setInProgress(long inProgress) {
		this.inProgress = inProgress;
	}

	public long getCompleted() {
		return completed;
	}

	public void setCompleted(long completed) {
		this.completed = completed;
	}

	public long getCancelled() {
		return cancelled;
	}

	public void setCancelled(long cancelled) {
		this.cancelled = cancelled;
	}
    
}
