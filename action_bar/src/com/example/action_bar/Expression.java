package com.example.action_bar;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;


public class Expression {
	public static double solve(String s)
	{
		
		
	Calculable calc=null;
	try {
		calc = new ExpressionBuilder(s).build();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
double result1=calc.calculate();
return result1;
	}
}

