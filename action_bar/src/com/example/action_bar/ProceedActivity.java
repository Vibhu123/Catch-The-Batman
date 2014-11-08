package com.example.action_bar;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.capture_math.action_bar.R;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProceedActivity extends SherlockFragmentActivity
{

	protected TextView t1;
	ActionBar actionBar;
	String text;
	String answer="";
	String[] lines;
	String classification;
	String[] vars;
	public int nRoots;

    public double x1;

    public double x2;

    public double x3;
    
    private static final double TWO_PI = 2.0 * Math.PI;
    private static final double FOUR_PI = 4.0 * Math.PI;

	public void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
		setContentView(R.layout.proceed);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Result");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw_green));
        t1=(TextView)findViewById(R.id.textView1);
		text=this.getIntent().getStringExtra("Corrected_Text");
		classification=this.getIntent().getStringExtra("Classification");
		Log.v("Classification", classification);
		Log.v("Text", text);
		
		int c;
		try{	
			if(text==null||text==""||text==" ")
			{
				callErrorDialogBox();
			}
			if(classification.equalsIgnoreCase("two_var")||classification.equalsIgnoreCase("three_var")
					||classification.equalsIgnoreCase("quad"))
			{
				int lines=text.split("\r\n|\n").length;
				c=countUniqueVariables(lines);
				if(c>lines)
				{
					callErrorDialogBox();
				}
				else if(c<=lines)
				{
					answer=solver();
					Log.v("Line text",text);
					t1.setTextColor(Color.BLACK);
					t1.setText(answer);

				}
			}
			else if(classification.equalsIgnoreCase("inverse")||classification.equalsIgnoreCase("transpose")
					||classification.equalsIgnoreCase("determinant")||classification.equalsIgnoreCase("cofactor")||
					classification.equalsIgnoreCase("numeric")||classification.equalsIgnoreCase("trignometric"))
			{
				answer=solver();
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.equalsIgnoreCase("chemistry"))
			{
				MainClass m1=new MainClass();
				String result = null;
				try {
					result = m1.balance(text);
					Log.v("Result", "here---"+result);
					if(result==null)
					{
						callErrorDialogBox();
					}
					else
						t1.setText(result);
					t1.setTextColor(Color.BLACK);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("Exception","here---"+e);
					callErrorDialogBox();
				}
				
			}
			else if(classification.equalsIgnoreCase("add/subtract"))
			{
				text=text.replaceAll("[-]", "+-");
				String[] tokens=text.split("[+]");
				String im="",re="";
				for(int i=0;i<tokens.length;i++)
				{
					System.out.println(tokens[i]);
				}
				for(int i=0;i<tokens.length;i++)
				{
					if(tokens[i].contains("i"))
					{
						if(Double.valueOf(tokens[i].split("i")[0])>=0)
							im=im+"+"+tokens[i].split("i")[0];
						else
						{
							im=im+tokens[i].split("i")[0];
								
						}
					}
					else
					{
						if(Double.valueOf(tokens[i])>=0)
							re=re+"+"+tokens[i];
						else
						{
							re=re+tokens[i];
						}
					}
				}
				double r=((Trignometry.solve(re))*100.0)/100.0;
				double i=((Trignometry.solve(im))*100.0)/100.0;
				if(i<0)
					answer="The result of the complex expression is \n"+r+i+"i";
				else
					answer="The result of the complex expression is \n"+r+"+"+i+"i";
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.equalsIgnoreCase("multiply_complex"))
			{
				String[] tok=text.split("()");
				answer="Multiplying complex numbers the result is \n"+ComplexNumbers.solveComplex(tok[2]+tok[3]+tok[4]+tok[5],tok[8]+tok[9]+tok[10]+tok[11], "multiply", 5);
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.equalsIgnoreCase("divide_complex"))
			{
				String[] tok=text.split("/");
				answer="Dividing complex numbers the result is \n"+ComplexNumbers.solveComplex(tok[0],tok[1], "divide", 5);
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.equalsIgnoreCase("sd")||classification.equalsIgnoreCase("gm")
					||classification.equalsIgnoreCase("sosq")||classification.equalsIgnoreCase("var")||
					classification.equalsIgnoreCase("mean")||classification.equalsIgnoreCase("median"))
			{
				answer=ArithmeticOps.solveArithmetic(text, classification);
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.contentEquals("cubic"))
			{
				try {
					answer=solveCube(text);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.contentEquals("factorial"))
			{
				answer=factorial(text);
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else if(classification.contentEquals("log"))
			{
				answer=solveLogarithmic(text);
				Log.v("Answer",""+answer);
				double ans=Trignometry.solve(answer);
				answer="Answer of logarithmic expression is \n"+String.valueOf(ans);
				t1.setTextColor(Color.BLACK);
				t1.setText(answer);
			}
			else
			{
				Log.v("MSG","Here error");
				//callErrorDialogBox();
			}
			if(classification.contentEquals("numeric"))
		        {
		        	actionBar.setIcon(R.drawable.direct_exp);
		        }
		        if(classification.contentEquals("add/subtract")||classification.contentEquals("multiply_complex")||classification.contentEquals("divide_complex"))
		        {
		        	actionBar.setIcon(R.drawable.complex_num);
		        }
		        if(classification.contentEquals("mean"))
		        {
		        	actionBar.setIcon(R.drawable.mean);
		        }
		        if(classification.contentEquals("trignometric"))
		        {
		        	actionBar.setIcon(R.drawable.trig);
		        }
		        if(classification.contentEquals("log"))
		        {
		        	actionBar.setIcon(R.drawable.log);
		        }
		        if(classification.contentEquals("factorial"))
		        {
		        	actionBar.setIcon(R.drawable.fact_exp);
		        }
		        if(classification.contentEquals("median"))
		        {
		        	actionBar.setIcon(R.drawable.median);
		        }
		        if(classification.contentEquals("var"))
		        {
		        	actionBar.setIcon(R.drawable.variance);
		        }
		        if(classification.contentEquals("sd"))
		        {
		        	actionBar.setIcon(R.drawable.stand_dev);
		        }
		        if(classification.contentEquals("sosq"))
		        {
		        	actionBar.setIcon(R.drawable.sosq);
		        }
		        if(classification.contentEquals("gm"))
		        {
		        	actionBar.setIcon(R.drawable.gm);
		        }
		        if(classification.contentEquals("two_var"))
		        {
		        	actionBar.setIcon(R.drawable.two_var);
		        }
		        if(classification.contentEquals("three_var"))
		        {
		        	actionBar.setIcon(R.drawable.three_var);
		        }
		        if(classification.contentEquals("quad"))
		        {
		        	actionBar.setIcon(R.drawable.quad);
		        }
		        if(classification.contentEquals("cubic"))
		        {
		        	actionBar.setIcon(R.drawable.cube);
		        }
		        if(classification.contentEquals("inverse"))
		        {
		        	actionBar.setIcon(R.drawable.inverse);
		        }
		        if(classification.contentEquals("transpose"))
		        {
		        	actionBar.setIcon(R.drawable.transpose);
		        }
		        if(classification.contentEquals("determinant"))
		        {
		        	actionBar.setIcon(R.drawable.deter);
		        }
		        if(classification.contentEquals("cofactor"))
		        {
		        	actionBar.setIcon(R.drawable.cofactor);
		        }
		
		}catch(Exception e)
		{
			try
			{
				if(e.getMessage().toString().contentEquals("Expression can not be empty!."))
				{
					callErrorDialogBox();
				}
				if(e.getMessage().toString().contentEquals("Division by zero!"))
				{
					t1.setTextColor(Color.BLACK);
					t1.setText("Infinity");
				}
				else
					callErrorDialogBox();
			}catch(Exception e11)
			{
				callErrorDialogBox();
			}
		}
		
	}
	private double[] solveCubic(String line) throws Exception
    {
		String cube=line.toLowerCase();
		cube=cube.replaceAll("[a-zA-Z]3", "C");
		cube=cube.replaceAll("[a-zA-Z]2", "Q");
		cube=cube.replaceAll("[a-z]", "x");
		System.out.println(cube);
		double constants[]=getRequiredConstants(cube);
		double[][] coeffs=new double[3][3];
		String[] vars={"C","Q","x"};
		for(int i=0;i<3;i++)
		{
			coeffs[i]=Coefficients.extractMatrix(vars, cube);
		}
		double a = 0,b=0,c=0,d=0;
		a=coeffs[0][0];
		b=coeffs[0][1];
		c=coeffs[0][2];
		d=constants[0];
		System.out.println(a+" "+b+" "+c+" "+d);
		// Verify preconditions.
	    if (a == 0.0)
	        {
	        throw new Exception ("Cubic.solve(): a = 0");
	        }
	
	    // Normalize coefficients.
	    double denom = a;
	    a = b/denom;
	    b = c/denom;
	    c = d/denom;
	
	    // Commence solution.
	    double a_over_3 = a / 3.0;
	    double Q = (3*b - a*a) / 9.0;
	    double Q_CUBE = Q*Q*Q;
	    double R = (9*a*b - 27*c - 2*a*a*a) / 54.0;
	    double R_SQR = R*R;
	    double D = Q_CUBE + R_SQR;
	
	    if (D < 0.0)
	        {
	        // Three unequal real roots.
	        nRoots = 3;
	        double theta = Math.acos (R / Math.sqrt (-Q_CUBE));
	        double SQRT_Q = Math.sqrt (-Q);
	        x1 = 2.0 * SQRT_Q * Math.cos (theta/3.0) - a_over_3;
	        x2 = 2.0 * SQRT_Q * Math.cos ((theta+TWO_PI)/3.0) - a_over_3;
	        x3 = 2.0 * SQRT_Q * Math.cos ((theta+FOUR_PI)/3.0) - a_over_3;
	        sortRoots();
	        }
	    else if (D > 0.0)
	        {
	        // One real root.
	        nRoots = 1;
	        double SQRT_D = Math.sqrt (D);
	        double S = Math.cbrt (R + SQRT_D);
	        double T = Math.cbrt (R - SQRT_D);
	        x1 = (S + T) - a_over_3;
	        x2 = (S + T) - a_over_3;
	        x3 = (S + T) - a_over_3;
	        }
	    else
	        {
	        // Three real roots, at least two equal.
	        nRoots = 3;
	        double CBRT_R = Math.cbrt (R);
	        x1 = 2*CBRT_R - a_over_3;
	        x2 = x3 = CBRT_R - a_over_3;
	        sortRoots();
	        }
		double[] results={x1,x2,x3};
	    return results;
    }
	
	private void sortRoots()
    {
    if (x1 < x2)
        {
        double tmp = x1; 
        x1 = x2;
        x2 = tmp;
        }
    if (x2 < x3)
        {
        double tmp = x2; 
        x2 = x3; 
        x3 = tmp;
        }
    if (x1 < x2)
        {
        double tmp = x1; 
        x1 = x2; 
        x2 = tmp;
        }
    }

	private String solver()
	{
		String res="",res1="",res2="";
		if(classification.equalsIgnoreCase("numeric"))
		{
			text=text.replaceAll("X|x", "*");
			answer=solveDirect(text);
		}
		else if(classification.equalsIgnoreCase("trignometric"))
		{
			answer=solveTrignometric(text);
		}
		else if(classification.equalsIgnoreCase("two_var"))
		{
			String[] lines;
			lines=text.split("\r\n|\n");
			if(lines.length>2)
			{
				callErrorDialogBox();
			}
			else
			{
				res="";
				res1="";
				res=getEquationToOneSide(lines[0],res);
				res1=getEquationToOneSide(lines[1],res1);
				answer="Solving the system answer is \n"+solveSystemOfEquations(res+res1);
				Log.v("Answer",answer);
			}
		}
		else if(classification.equalsIgnoreCase("three_var"))
		{
			String[] lines;
			lines=text.split("\r\n|\n|\r");
			if(lines.length>3)
			{
				callErrorDialogBox();
			}
			else
			{
				res="";
				res1="";
				res2="";
				res=getEquationToOneSide(lines[0],res);
				res1=getEquationToOneSide(lines[1],res1);
				res2=getEquationToOneSide(lines[2],res2);
				answer="Solving linear system answer is \n"+solveSystemOfEquations(res+res1+res2);
			}
		}
		else if(classification.equalsIgnoreCase("quad"))
		{
			text=text.toLowerCase();
			lines=text.split("\r\n|\n|\r");
			for(int i=0;i<lines.length;i++)
			{
				res=getEquationToOneSide(lines[i],res);
				double[] results=solveQuadratic(res);
				Log.v("Results", ""+results[0]+" "+results[1]);
				answer=answer+"Roots are \n"+String.valueOf(results[0])+" "+String.valueOf(results[1])+"\n";
				res="";
			}
		}
		else if(classification.equalsIgnoreCase("inverse")||classification.equalsIgnoreCase("transpose")
				||classification.equalsIgnoreCase("determinant")||classification.equalsIgnoreCase("cofactor"))
		{
			answer=solveMatrix(text,classification);
		}
		return answer;
	}
	
	private String solveTrignometric(String text)
	{
		return Trignometry.main(text);
	}
	
	private String solveMatrix(String text,String clf)
	{
		lines=text.split("\r\n|\n|\r");
		double [][] matrix=new double[lines.length][lines.length];
		for(int i=0;i<lines.length;i++)
		{
			System.out.println(lines[i].length());
			for(int j=0;j<lines.length;j++)
				matrix[i][j]=0;
		}
		double[][] result = new double[lines.length][lines.length];
		for(int i=0;i<lines.length;i++)
		{
			String[] digs=lines[i].split(" ");
			System.out.println(lines[i]+" ");
			for(int j=0;j<digs.length;j++)
			{
				matrix[i][j]=Double.valueOf(digs[j]);
				System.out.println(""+matrix[i][j]);
			}
		}
		if(clf.equalsIgnoreCase("inverse"))
		{
			Matrix inv_coeffs=new Matrix(matrix);
			inv_coeffs.setValues(matrix);
			try {
				inv_coeffs=MatrixMathematics.inverse(inv_coeffs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callErrorDialogBox();
			}
			result=inv_coeffs.getValues();
			answer="Inverse is \n";
		}
		else if(clf.equalsIgnoreCase("transpose"))
		{
			Matrix transpose=new Matrix(matrix);
			transpose.setValues(matrix);
			try {
				transpose=MatrixMathematics.transpose(transpose);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callErrorDialogBox();
			}
			result=transpose.getValues();
			answer="Transpose is \n";
		}
		else if(clf.equalsIgnoreCase("cofactor"))
		{
			Matrix cofactor=new Matrix(matrix);
			cofactor.setValues(matrix);
			try {
				cofactor=MatrixMathematics.cofactor(cofactor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callErrorDialogBox();
			}
			result=cofactor.getValues();
			answer="Cofactor is \n";
		}
		else if(clf.equalsIgnoreCase("determinant"))
		{
			Matrix det=new Matrix(matrix);
			double determinant=0;
			try {
				determinant=MatrixMathematics.determinant(det);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callErrorDialogBox();
			}
			answer="Determinant is \n"+String.valueOf(determinant);
			Log.v("ans",answer);
			return answer;
		}
		for(int i=0;i<result.length;i++)
		{
			for(int j=0;j<result[i].length;j++)
			{
				Log.v("Resultant",""+result[i][j]);
				answer=answer+result[i][j]+" ";
			}
			answer=answer+"\n";
		}
		return answer;
	}
	
	private String solveCube(String text) throws Exception
	{
		String[] lines=null;
		String res="",answer="";
		text=text.toLowerCase();
		lines=text.split("\r\n|\n|\r");
		for(int i=0;i<lines.length;i++)
		{
			res=getEquationToOneSide(lines[i],res);
			double[] results=solveCubic(res);
			answer=answer+"Roots are \n"+String.valueOf(results[0])+" "+String.valueOf(results[1])+" "+String.valueOf(results[2])+"\n";
			res="";
		}
		return answer;
	}
	
	private double fact(int n)
	{
		  double c, fact = 1;
	      if ( n < 0 )
	         System.out.println("Number should be non-negative.");
	      else
	      {
	         for ( c = 1 ; c <= n ; c++ )
	            fact = fact*c;
	      }
	      return fact;
	}
	private String factorial(String text)
	{
		String[] lines;
		String answer="";
		String exp="";
		text=text.toLowerCase();
		lines=text.split("\r\n|\n|\r");
		for(int i=0;i<lines.length;i++)
		{
			if(!containsDivide(lines[i])&&containsPm(lines[i]))
			{
				lines[i]=lines[i].replaceAll("[-]", "+-");
				String tokens[]=lines[i].split("[+]");
				exp=fct(tokens,tokens.length);
			}
			else if(containsDivide(lines[i]))
			{
				if(!containsPm(lines[i]))
				{
					exp=solveDivide(lines[i]);
				}
				else
				{
					lines[i]=lines[i].replaceAll("[-]", "+-");
					String tokens[]=lines[i].split("[+]");
					String tokens1[]=new String[tokens.length];
					for(int l=0;l<tokens1.length;l++)
					{
						tokens1[l]="w";
					}
					int k=0;
					for(int j=0;j<tokens.length;j++)
					{
						if(tokens[j].contains("/"))
						{
							exp=exp+solveDivide(tokens[j]);
						}
						else
						{
							tokens1[k++]=tokens[j];
						}
					}
					exp=exp+fct(tokens1,k);
				}
			}
			else if(!containsDivide(lines[i])&&!containsPm(lines[i]))
			{
				double y,f=1;
				lines[i]=lines[i].replaceAll("!", "*");
				String[] tok=lines[i].split("[*]");
				int a=Integer.valueOf(tok[0]);
				if(a>=0)
				{
					y=fact(a);
					f=f*y;
				}
				else
				{
					y=fact((-1)*a);
					f=(-1)*f*y;
				}
				for(int l=1;l<tok.length;l++)
				{
					y=fact(Integer.valueOf(tok[l]));
					f=f*y;
				}
				if(f>=0)
				{
					exp=exp+"+"+f;
				}
				else
				{
					exp=exp+f;
				}
			}
		}
		answer=answer+"Answer of factorial expression is \n"+Expression.solve(exp);
		return answer;
	}
	private String solveDivide(String line)
	{
		int flag=-1;
		int flag2 = -1;
		String exp1="",exp2="",exp="";
		String tokens[]=line.split("/");
		int count1=0,count2=0;
		for(int j=0;j<tokens.length;j++)
		{
			if(countFact(tokens[j])==1)
			{
				flag=0;
				count1++;
				tokens[j]=tokens[j].replaceAll("!", "");
				int k=Integer.valueOf(tokens[j]);
				
				if(k>=0)
				{
					double y=fact(k);
					if(count1==2&&count2==0)
						exp1=exp1+"/"+y;
					else
						exp1=exp1+y;
				}
			}
			else
			{
				count2++;
				double y,f=1;
				tokens[j]=tokens[j].replaceAll("!", "*");
				String[] tok=tokens[j].split("[*]");
				for(int l=0;l<tok.length;l++)
				{
					y=fact(Integer.valueOf(tok[l]));
					f=f*y;
				}
				if(f>=0)
				{
					if(count2==2&&count1==0)
					{
							exp2=exp2+"/"+f;
					}
					else
						exp2=exp2+f;
				}
			}
			if(flag==0&&flag2==-1)
				flag2=1;
			else if(flag2==-1)
				flag2=0;
		}
		if(exp1.contentEquals(""))
			exp=exp2;
		else if(exp2.contentEquals(""))
			exp=exp1;
		else
		{
			if(flag2==1)
				
				exp="("+exp1+")"+"/"+"("+exp2+")";
			else
				exp="("+exp2+")"+"/"+"("+exp1+")";
		}
		return exp;
	}
	private String fct(String[] tokens,int length)
	{
		String exp1="",exp2="",exp="";
		for(int j=0;j<length;j++)
		{
			if(countFact(tokens[j])==1)
			{
				tokens[j]=tokens[j].replaceAll("!", "");
				int k=Integer.valueOf(tokens[j]);
				if(k>=0)
				{
					double y=fact(k);
					exp1=exp1+"+"+y;
				}
				else
				{
					double y=fact(-1*k);
					exp1=exp1+"-"+y;
				}
			}
			else
			{
				double y,f=1;
				tokens[j]=tokens[j].replaceAll("!", "*");
				String[] tok=tokens[j].split("[*]");
				int a=Integer.valueOf(tok[0]);
				if(a>=0)
				{
					y=fact(a);
					f=f*y;
				}
				else
				{
					y=fact((-1)*a);
					f=(-1)*f*y;
				}
				for(int l=1;l<tok.length;l++)
				{
					y=fact(Integer.valueOf(tok[l]));
					f=f*y;
				}
				if(f>=0)
				{
					exp2=exp2+"+"+f;
				}
				else
				{
					exp2=exp2+f;
				}
			}
		}
		if(exp1.contentEquals(""))
			exp=exp2;
		else if(exp2.contentEquals(""))
			exp=exp1;
		else
			exp=exp1+exp2;
		
		return exp;
	}
	private boolean containsPm(String line)
	{
		for(int i=0;i<line.length();i++)
		{
			if(line.charAt(i)=='-'||line.charAt(i)=='+')
				return true;
		}
		return false;
	}
	private boolean containsDivide(String line)
	{
		for(int i=0;i<line.length();i++)
		{
			if(line.charAt(i)=='/')
				return true;
		}
		return false;
	}
	private int countFact(String txt)
	{
		int count=0;
		for(int i=0;i<txt.length();i++)
		{
			if(txt.charAt(i)=='!')
				count++;
		}
		return count;
	}
	
	private String solveDirect(String text)
	{
		lines=text.split("\n");
		for(int i=0;i<lines.length;i++)
		{
			Log.v("Lines",lines[i]);
			answer=answer+"Answer is "+String.valueOf(Expression.solve(lines[i]))+"\n";
		}
		return answer;
	}
	private double[] solveQuadratic(String line)
	{
		String a="",b="",d="";
		String quad=line.toLowerCase();
		quad=quad.replaceAll("[a-zA-Z]2", "Q");
		quad=quad.replaceAll("[a-z]", "x");
		System.out.println(quad);
		double constants[]=getRequiredConstants(quad);
		for(int i=0;i<constants.length;i++)
		{
			System.out.println(constants[i]);
		}
		double[][] coeffs=new double[2][2];
		String[] vars={"Q","x"};
		for(int i=0;i<2;i++)
		{
			coeffs[i]=Coefficients.extractMatrix(vars, quad);
		}
		a=String.valueOf(coeffs[0][0]);
		if(coeffs[0][1]>=0)
			b="+"+String.valueOf(coeffs[0][1]);
		else
			b=String.valueOf(coeffs[0][1]);
		if(constants[0]>=0)
			d="+"+constants[0];
		else
			d=""+constants[0];
		quad=a+"x^2"+b+"x"+d;
		System.out.println(quad);
		float A=Float.valueOf(a);
		float B=Float.valueOf(b);
		float C=Float.valueOf(d);
		
		 double root1 = (-B + Math.sqrt((B * B) - (4 * A * C))) / (2 * A);
	     double root2 = (-B - Math.sqrt((B * B) - (4 * A * C))) / (2 * A);
		double[] ans={root1,root2};
		Log.v("Roots",""+root1+" "+root2);
		return ans;
	}
	
	private int countUniqueVariables(int line)
	{
		int count = 0;
		String text1=text.replaceAll("[0-9-+*/=()\\n]","");
		text1=text1.toLowerCase();
		
		String a = "";
		int c=0;
		int process[] = new int[256];
		for(int j=0;j<256;j++)
			process[j]=0;
		for (int i1 = 0; i1 < text1.length(); i1++) {
	        if (!(text1.substring(0, i1).contains(text1.charAt(i1)+"")))
	            count++;
	    }
		
		if(count>line)
		{//removeUnwantedVariables
			for(int i=0;i<text1.length();i++)
			{
				c=0;
				c=text1.split(text1.charAt(i)+"").length-1;
				if(c<(line-1)&&process[text1.charAt(i)]==0)
				{
					a=a+text1.charAt(i);
					process[text1.charAt(i)]=1;	
				}
			}
			text=text.toLowerCase();
			text=text.replaceAll(a, "");
			String text2=text;
			lines=text2.split("\r\n|\r|\n");
			text1=text;
		}
		count=0;
		for (int i1 = 0; i1 < text1.length(); i1++) {
	        if (!(text1.substring(0, i1).contains(text1.charAt(i1)+"")))
	            count++;
	    }
		return count;
		
	}
	private void callErrorDialogBox()
	{
		ImageView image = (ImageView) findViewById(R.id.imageView2);
		image.setImageResource(R.drawable.error_image);
		t1.setTextColor(Color.BLACK);
		t1.setText("Some Error Occured");
	}

	private String getEquationToOneSide(String s,String res)
	{
		String[] eqs=s.split("=");
		if(eqs[1].charAt(0)!='+'||eqs[1].charAt(0)!='-') 
			eqs[1]="+"+eqs[1];
		eqs[1]=eqs[1].replaceAll("[-]", "@");
		eqs[1]=eqs[1].replaceAll("[+]", "-");
		eqs[1]=eqs[1].replaceAll("[@]", "+");
		eqs[0]=eqs[0]+eqs[1]+"=0";
		s=eqs[0];
		res=res+s+"\n";
		return res;
	}

	private String solveSystemOfEquations(String eqs)
	{
		String answer="";
		String res=eqs;
		String eqns[]=res.split("\r\n|\r|\n");
		String text=res.replaceAll("[0-9-+=\\n]","");
		double[][] coeffs=new double[eqns.length][eqns.length];
		int count=0;
		String text1="";
		for (int i1 = 0; i1 < text.length(); i1++) {
	        if (!(text.substring(0, i1).contains(text.charAt(i1)+"")))
	        {
	        	text1=text1+text.charAt(i1);
	        	count++;
	        }
		}
		text1=text1.toLowerCase();
		char[] a=text1.toCharArray();
		String[] vars=new String[a.length];
		for(int k=0;k<vars.length-1;k++)
			vars[k]="";
		for(int i=0;i<a.length;i++)
		{
			vars[i]=new String(a[i]+"");
		}
		for(int j=0;j<eqns.length;j++)
		{
			coeffs[j]=Coefficients.extractMatrix(vars, eqns[j]);
		}
		double []constants=getRequiredConstants(res);
		for(int i=0;i<constants.length;i++)
			constants[i]=-1.0*constants[i];
		
		double [][] tconsts=new double[constants.length][1]; 
		
		for(int i=0;i<constants.length;i++)
		tconsts[i][0]=constants[i];
		
		Matrix ConMat=new Matrix(tconsts);
		Matrix inv_coeffs=new Matrix(coeffs);
		inv_coeffs.setValues(coeffs);
		try {
			inv_coeffs=MatrixMathematics.inverse(inv_coeffs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Matrix Result=MatrixMathematics.multiply(inv_coeffs,ConMat);
		double [][] result=Result.getValues();
		for(int i=0;i<result.length;i++)
		{
			for(int j=0;j<result[i].length;j++)
			{
				answer=answer+"\n"+vars[i]+"="+round(result[i][j],2)+"\n";
			}
		}
		return answer;
	}
	
	private static double round(double value,int places)
	{
		BigDecimal bd=new BigDecimal(value);
		bd=bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	private double[] getRequiredConstants(String res)
	{
		String[] lines=res.split("\r\n|\r|\n");
		String constants="0";
		double[] constant=new double[lines.length];
		for(int i=0;i<lines.length;i++)
		{
			lines[i]=lines[i].replaceAll("-", "+-");
			lines[i]=lines[i].replaceAll("[+-]*[0-9]*.[a-zA-Z]", "");
			lines[i]=lines[i].replaceAll("[0-9]*.[a-zA-Z]", "");
			String eqs[]=lines[i].split("=");
			System.out.println(eqs[0]);
			String[] text=eqs[0].split("[+]");
			for(int j=0;j<text.length;j++)
			{
				if(text[j].matches("-.[0-9]*"))
					constants=constants+text[j];
				else if(text[j].matches("[-].[0-9]*\\.?[0-9]+"))
				{
					constants=constants+text[j];
				}
				else if(text[j].matches("[0-9]*\\.?[0-9]+"))
					constants=constants+"+"+text[j];
				else if(text[j].matches("[0-9]+"))
				{
					constants=constants+"+"+text[j];
				}
			}
			System.out.println(constants);
			constant[i]=Expression.solve(constants);
			constants="0";
		}
		return constant;
	}
	private String solveLogarithmic(String text)
	{
		String ans="";
		String lines[]=text.split("\r\n|\n|\r");
		for(int i=0;i<lines.length;i++)
		{
			String[] tokens=lines[i].split("[-+*/=()]");
			for(int j=0;j<tokens.length;j++)
			{
				if(tokens[j].contains("^"))
				{
					
					if(tokens[j].contains("log"))
					{
						lines[i]=lines[i].replaceAll("log"+tokens[j].split("log")[1].split("\\^")[0]+"\\^"+tokens[j].split("\\^")[1], "log"+"("+tokens[j].split("log")[1].split("\\^")[0]+")"+"^"+tokens[j].split("\\^")[1]);
						
					}
				}
				else
				{
					if(tokens[j].contains("log"))
						lines[i]=lines[i].replaceAll("log"+tokens[j].split("log")[1], "log"+"("+tokens[j].split("log")[1]+")");
				}
			}
		}
		for(int i=0;i<lines.length;i++)
		{
			ans=ans+lines[i];
		}
		return ans;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 menu.add("Share")
        .setOnMenuItemClickListener(this.ShareButtonClickListener)
        .setIcon(R.drawable.ic_action_share) // Set the menu icon
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	
		 menu.add("About")
		 .setOnMenuItemClickListener(this.AboutButtonClickListener)
		 .setIcon(R.drawable.ic_action_about) // Set the menu icon
		 .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		 return super.onCreateOptionsMenu(menu);
	}
	OnMenuItemClickListener ShareButtonClickListener = new OnMenuItemClickListener() {
	 	@Override
		public boolean onMenuItemClick(
				com.actionbarsherlock.view.MenuItem item) {
			// TODO Auto-generated method stub
			// Toast.makeText(MainActivity.this, "Share Button", Toast.LENGTH_SHORT)
            // .show();
	 		Intent shareIntent = new Intent(Intent.ACTION_SEND);
	 		  shareIntent.setType("text/plain");
	 		 shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.capture_math.action_bar");
	 		  startActivity(Intent.createChooser(shareIntent, "Share Capture Math"));
	 		return false;
		}
    };

    OnMenuItemClickListener AboutButtonClickListener = new OnMenuItemClickListener() {
		 
    	@Override
		public boolean onMenuItemClick(
				com.actionbarsherlock.view.MenuItem item) {
			// TODO Auto-generated method stub
			 final Dialog dialog=new Dialog(ProceedActivity.this);
			 dialog.setTitle("About");
			 
			 LinearLayout ll = new LinearLayout(ProceedActivity.this);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        ll.setBackgroundColor(Color.WHITE);
	        TextView tv = new TextView(ProceedActivity.this);
	        tv.setText("Developer: The Soft Heights\nCapture Math is based on OCR technology. It recognizes the text" +
			 		" of the expressions or equations of the images captured. This app supports the basic functionalities of matrix, equations," +
			 		" trignometry, logarithms and more. If you like this app then please give us your time to rate it. Or if you wish to give us any suggestions do provide" +
			 		" us feedback. We will try our best to improve your experience in any way we can.");
	              tv.setTextColor(Color.BLACK);
	        tv.setWidth(300);
	        tv.setPadding(4, 0, 4, 10);
	        ll.addView(tv);
	        dialog.setContentView(ll);        
	        dialog.show();        
	        return true;
		}
    };

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

	    case android.R.id.home:
	         // Do whatever you want, e.g. finish()
	    	finish();
	         break;

	    default:
	        return super.onOptionsItemSelected(item);
	    }
		return super.onOptionsItemSelected(item);
	}
		
}