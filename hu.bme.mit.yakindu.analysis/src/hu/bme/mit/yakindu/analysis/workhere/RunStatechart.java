package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;

import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;



public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine(); 
		s.setTimer(new TimerService()); 
		RuntimeService.getInstance().registerStatemachine(s, 200); 
		s.init(); s.enter(); 
		s.runCycle(); print(s); 
		s.raiseStart(); s.runCycle(); 
		String str="";
		char c;
		while (true)
		{
			str="";
			while ((c=(char)System.in.read())!='\n')
			{
				str+=c;
			}
			
			if (str.equals("white"))
			{
				s.raiseWhite(); 
				s.runCycle();
				print(s); 
			}
			else if (str.equals("black"))
			{
				s.raiseBlack(); 
				s.runCycle();
				print(s); 
			}
			else if (str.equals("start"))
			{
				s.raiseStart(); 
				s.runCycle();
				print(s); 
			}
			else if (str.equals("exit"))
			{
				System.exit(0);
				break;
			}
		}
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
}
