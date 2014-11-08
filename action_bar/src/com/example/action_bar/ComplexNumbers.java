package com.example.action_bar;
import org.apache.commons.math3.complex.Complex;

public class ComplexNumbers {
	public static String solveComplex(String a,String b,String type,double constant)
	{
		Complex ans = null;
		String answer;
		a=a.replaceAll("[-]","+-");
		String rep[]=a.split("[+]");
		b=b.replaceAll("[-]","+-");
		String rep1[]=b.split("[+]");
		if(rep[0].contentEquals(""))
		{	rep[0]=rep[1];
			rep[1]=rep[2];
		}
		if(rep1[0].contentEquals(""))
		{	rep1[0]=rep1[1];
			rep1[1]=rep1[2];
		}
		Complex lh=new Complex(Double.valueOf(rep[0]),Double.valueOf(rep[1].substring(0,rep[1].indexOf("i"))));
		Complex rh=new Complex(Double.valueOf(rep1[0]),Double.valueOf(rep1[1].substring(0,rep1[1].indexOf("i"))));
		if(type=="multiply")
			ans=lh.multiply(rh);
		if(type=="divide")
			ans=lh.divide(rh);
		if(type=="multiply_constant")
			ans=lh.multiply(constant);
		if(type=="divide_constant")
			ans=lh.divide(constant);
		double real=ans.getReal();
		double imag=ans.getImaginary();
		if(imag>=0)
			answer=real+"+"+imag+"i";
		else
			answer=real+imag+"i";
		return answer;
	}
	
	
}
