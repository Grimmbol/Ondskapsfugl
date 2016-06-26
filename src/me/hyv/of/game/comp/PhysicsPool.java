package me.hyv.of.game.comp;

import java.util.ArrayList;
import java.util.Collection;

import me.hyv.of.engine.Time;
import me.hyv.of.scene.Component;
import me.hyv.of.shape.ColliderHelper;
import me.hyv.of.shape.HavardCollision;

public class PhysicsPool extends Component {
	private ArrayList<CollitionComponent> components = new ArrayList<>();
	
	public PhysicsPool() {
		
	}
	
	public void registerObject(CollitionComponent cmp) {
		if(!components.contains(cmp))
			components.add(cmp);
	}
	
	public void removeComponent(CollitionComponent cmp) {
		components.remove(cmp);
	}
	
	public Collection<CollitionComponent> getComponents() {
		return components;
	}

	@Override
	public void update() {
		for(int i = 0; i < components.size(); i++) {
			CollitionComponent c1 = components.get(i);
			if(!c1.isSolid())
				continue;
			for(int j = i+1; j < components.size(); j++) {
				CollitionComponent c2 = components.get(j);
				
			}
		}
	}

	@Override
	public void render() {
		
	}
}
