package org.firstinspires.ftc.teamcode;
import java.lang.RuntimeException;
public class ServoController
{
	private double position;
	private final boolean isCR;
	private double v;
	private double a;
	private boolean reverse;
	private int posIndex;
	private double sMod;
	private double [] positions= {1,0};
	ServoController ()
	{
		throw new RuntimeException ("must be set to either CR or Non-CR mode in the constructor");	
	}
	ServoController (boolean s)
	{
		this (s,0,0,0,0,false,new double [] {-1});
	}
	ServoController (double speed, double acc, double sM)
	{
		this (speed,acc,sM,-1,false);
	}
	ServoController (double speed, double acc, double sM, boolean b)
	{
		this (true,speed,acc,sM,-1,b,new double [] {-1});
	}
	ServoController (double position,double speed,double acc,double sM)
	{
		this (speed,acc,sM,position,false);
	}
	ServoController (double position,double speed,double acc,double sM, boolean b)
	{
		this (speed,acc,sM,position,b,new double [] {-1});
	}
	ServoController (double position,double speed,double acc,double sM, boolean b,  double [] pos)
	{
		this (false,speed,acc,sM,position,b,pos);
	}
	ServoController (boolean s, double speed, double acc, double sM, double pos, boolean r, double [] poss)
	{
		isCR=s;
		setSpeed(speed);
		setAcceleration(acc);
		setSpeedModifier(sM);
		setPosition(pos);
		reverse=r;
		if (!isCR)
		{
			positions=poss;
			posIndex=0;
		}
		else
		{
			posIndex=-1;
		}
	}
	public double [] getPositions ()
	{
		return positions;
	}
	public void setPostions (double [] pos)
	{
		if (!isCR)
		{
			positions=pos;
		}
		else
		{
			throw new RuntimeException ("this is set to CR mode");	
		}
	}
	public double getData ()
	{
		if (!isCR)
		{
			return position;
		}
		return getRealSpeed();
	}
	public double moveRight ()
	{
		if (isCR)
		{
			decelerate();
			return getRealSpeed();
		}
		position+=getRealSpeed();
		position%=1;
		return position;
	}
	public void setSpeed (double s)
	{
		if (s<1 && s>-1)
		{
			v=s;
		}
		else
		{
			throw new RuntimeException ("input out of bounds");	
		}
	}
	public void setAcceleration (double ac)
	{
		if (isCR)
		{
			if (ac<1 && ac>-1)
			{
				a=ac;
			}
			else
			{
				throw new RuntimeException ("input out of bounds");	
			}
		}
		else
		{
			throw new RuntimeException ("this is set to non-CR mode");	
		}
	}
	public double getSpeed ()
	{
		return v;
	}
	public double getAcceleration ()
	{
		return a;
	}
	public void setPosition(double s)
	{
		if (!isCR)
		{
			if (s<1 && s>-1)
			{
				position=s;
			}
			else
			{
				throw new RuntimeException ("input out of bounds");	
			}
		}
		else
		{
			throw new RuntimeException ("this is set to CR mode");	
		}
	}
	private double getRealSpeed ()
	{
		return v*sMod* (reverse ? -1 : 1);
	}
	public void accelerate ()
	{
		v+=a;
		v%=1;
	}
	public void decelerate ()
	{
		v-=a;
		v%=1;
	}
	public double getPosition ()
	{
		return position;
	}
	public double getSpeedModifier ()
	{
		return sMod;
	}
	public void setSpeedModifier (double s)
	{
		if (s<1 && s>-1)
		{
			sMod=s;
		}
		else
		{
			throw new RuntimeException ("input out of bounds");	
		}
	}
	//not sure if this is left or right
	public double moveLeft ()
	{
		if (isCR)
		{
			accelerate();
			return v;
		}
		position-=getRealSpeed();
		position%=1;
		return position;
	}
	public double changePosition (int index)
	{
		if (!isCR)
		{
			if (index>=0 && index<positions.length)
			{
				setPosition(positions[index]);
				return position;
			}
			else
			{
				throw new RuntimeException ("index out of bounds");			
			}

		}
		else
		{
			throw new RuntimeException ("this is set to CR mode");	
		}
	}
	public double changePositionNext()
	{
		if (!isCR)
		{
			posIndex++;
			posIndex%=positions.length;
			setPosition(positions[posIndex]);
			return position;
		}
		else
		{
			throw new RuntimeException ("this is set to CR mode");	
		}
	}
	public double changePositionPrevious ()
	{
		if (!isCR)
		{
			posIndex--;
			if (posIndex<0)
			{
				posIndex=positions.length-1;
			}
			posIndex%=positions.length;
			setPosition(positions[posIndex]);
			return position;

		}
		else
		{
			throw new RuntimeException ("this is set to CR mode");	
		}
	}
}