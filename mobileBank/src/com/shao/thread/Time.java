package com.shao.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class Time extends Thread{
	private JLabel jl;
	public Time(JLabel jl){
		this.jl=jl;
	}
	public void run(){
		while(true){
			DateFormat sdf=new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
			String time=sdf.format(new Date());
			jl.setText(time);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
