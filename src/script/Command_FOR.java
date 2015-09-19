package script;

import wisen_simulation.SimLog;
import device.SensorNode;

public class Command_FOR extends Command {
	
	protected String arg1 ;
	protected String arg2 ;
	
	protected String sStep ;
	protected double step ;
	
	protected String left = "";
	protected String right ;
	
	protected boolean trueCondition = true;
	
	protected boolean first = true ;
	protected boolean exist = false ;
	
	protected int index;
	
	protected Command_FOR parent = null;
	
	public boolean isCondition() {
		return trueCondition;
	}

	public void setCondition(boolean isCondition) {
		this.trueCondition = isCondition;
	}
	
	public Command_FOR getParent() {
		return parent;
	}

	public void setParent(Command_FOR parent) {
		this.parent = parent;
	}

	public Command_FOR(SensorNode sensor, String arg1, String arg2, String arg3, String arg4) {
		// for i 0 10 1
		this.sensor = sensor ; 	
		
		trueCondition = true;
		first = true ;
		exist = false ;
				
		this.arg1 = arg1 ;
		this.arg2 = arg2 ;
		left = "$"+arg1;	
		right = arg3;
		sStep = arg4;
		
	}
	
	public String getRight() {
		return right;
	}
	
	public String getLeft() {
		return left;
	}
	
	public double getStep() {
		return step ;
	}

	@Override
	public int execute() {
		SimLog.add("S" + sensor.getId() + " FOR ");
		if (first) {
			step = Double.valueOf(sensor.getScript().getVariableValue(sStep));
			first = false ;
			exist = sensor.getScript().variableExist(arg1);
			String arg = sensor.getScript().getVariableValue(arg2);
			sensor.getScript().addVariable(arg1, arg);
		}
		index = sensor.getScript().getIndex();
		return 0;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void removeVar() {
		sensor.getScript().removeVar(arg1);
	}
	
	public void init() {
		first = true ;		
		if(!exist) {
			String arg = sensor.getScript().getVariableValue(arg2);
			sensor.getScript().addVariable(arg1, arg);
			sensor.getScript().removeVar(arg1);
		}
	}
		
	@Override
	public String toString() {
		return "FOR";
	}
}
