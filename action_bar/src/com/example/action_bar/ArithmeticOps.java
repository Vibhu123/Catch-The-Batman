package com.example.action_bar;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
public class ArithmeticOps {
	static String answer="";
	public static String solveArithmetic(String a,String type)
	{
		double mean = 0,var=0,sd = 0,median = 0,sq=0,gm=0;
		DescriptiveStatistics ds=new DescriptiveStatistics();
		String[] b=a.split(" |\n|\r|,");
		for(int i=0;i<b.length;i++)
		{
			ds.addValue(Double.valueOf(b[i]));
		}
		if(type.contentEquals("mean"))
		{	
			mean=ds.getMean();
			answer="Mean is \n"+mean;
		}
		if(type.contentEquals("sd"))
		{
			sd=ds.getStandardDeviation();
			answer="Standard Deviation is \n"+sd;
		}
		if(type.contentEquals("var"))
		{
			var=ds.getVariance();
			answer="Variance is \n"+var;
		}
		if(type.contentEquals("median"))
		{
			median=ds.getMean();
			answer="Median is \n"+median;
			
		}
		if(type.contentEquals("sosq"))
		{
			sq=ds.getSumsq();
			answer="Sum of Squares is \n"+sq;
			
		}
		if(type.contentEquals("gm"))
		{
			gm=ds.getGeometricMean();
			answer="Geometric Mean is \n"+gm;
			
		}
		return answer;
	}
	public static void main(String[] args)
	{
	}
}