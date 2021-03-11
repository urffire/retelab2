package hu.bme.mit.yakindu.analysis.workhere;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;


import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		List<String> allVariables = new ArrayList();
		
		
		
//		TreeIterator<EObject> iter = s.eAllContents();
//		List<String> allNames = new ArrayList();
//		
//		
//		while (iter.hasNext()) {
//			EObject content = iter.next();
//			if(content instanceof State) {
//				State state = (State) content;
//				allNames.add(state.getName());
//			}
//		}
//		
//		while (iterator.hasNext()) {
//			EObject content = iterator.next();
//			if(content instanceof State) {
//				State state = (State) content;
//				String name = state.getName();
//				if(state.getName()=="")
//				{
//					
//					
//					name = "Pisti";
//					while (allNames.contains(name))
//					{
//						name+="0";
//					}
//					System.out.println("Javasolom, hogy "+name+" legyen az állapot neve");
//					allNames.add(name);
//				}
//				
//				if (state.getOutgoingTransitions().size()==0)
//				{
//					System.out.println("Ez egy csapda állapot:"+name);
//				}
//				else {
//					System.out.println(name);
//				}
//				
//			}
//			if (content instanceof Transition)
//			{
//				Transition trans = (Transition) content;
//				System.out.println(trans.getSource().getName() + "->" + trans.getTarget().getName());
//			}
//		}
		
		System.out.println("package hu.bme.mit.yakindu.analysis.workhere;\r\n" + 
				"\r\n" + 
				"import java.io.IOException;\r\n" + 
				"\r\n" + 
				"import hu.bme.mit.yakindu.analysis.RuntimeService;\r\n" + 
				"import hu.bme.mit.yakindu.analysis.TimerService;\r\n" + 
				"import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;\r\n" + 
				"import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"public class RunStatechart {\r\n" + 
				"	\r\n" + 
				"	public static void main(String[] args) throws IOException {\r\n" + 
				"		ExampleStatemachine s = new ExampleStatemachine(); \r\n" + 
				"		s.setTimer(new TimerService()); \r\n" + 
				"		RuntimeService.getInstance().registerStatemachine(s, 200); \r\n" + 
				"		s.init(); s.enter(); \r\n" + 
				"		s.runCycle(); print(s); \r\n" + 
				"		s.raiseStart(); s.runCycle(); \r\n" + 
				"		String str=\"\";\r\n" + 
				"		char c;\r\n" + 
				"		while (true)\r\n" + 
				"		{\r\n" + 
				"			str=\"\";\r\n" + 
				"			while ((c=(char)System.in.read())!='\\n')\r\n" + 
				"			{\r\n" + 
				"				str+=c;\r\n" + 
				"			}\r\n" + 
				"			if(false){break;}"+
				"			");
		
		
		
		
		
		Arrays.asList(s.getSpecification().split("\n")).forEach(line->{

			if (line.substring(0,8).equals("in event"))
			{
				String cap = line.substring(9).substring(0, 1).toUpperCase() + line.substring(9).substring(1,line.substring(9).length()-1);
				System.out.println("			else if (str.equals(\""+line.substring(9,line.length()-1)+"\"))\r\n" + 
					"			{\r\n" + 
					"				s.raise"+cap+"(); \r\n" + 
					"				s.runCycle();\r\n" + 
					"				print(s); \r\n" + 
					"			}");
			}
		});
		System.out.println("			else if (str.equals(\"exit\"))\r\n" + 
				"			{\r\n" + 
				"				System.exit(0);\r\n" + 
				"				break;\r\n" + 
				"			}\n"+"		}\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public static void print(IExampleStatemachine s) {\r\n");
		
		Arrays.asList(s.getSpecification().split("\n")).forEach(line->{
			if (line.substring(0,3).equals("var"))
			{
				String tmp = line.substring(4);
				tmp = tmp.substring(0,tmp.indexOf(':'));
				
				
					String cap = tmp.substring(0, 1).toUpperCase() + tmp.substring(1);
					System.out.println("		System.out.println(\""+cap.substring(0,1)+" = \" + s.getSCInterface().get"+cap+"());\r\n");
			}
				
				
		});
		System.out.println("}}\r\n");
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}
