package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

public class ItemGen extends Item{

	public ItemGen(String id, String description) {
		super(id, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canBeUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		// TODO Auto-generated method stub
		return false;
	}

}
