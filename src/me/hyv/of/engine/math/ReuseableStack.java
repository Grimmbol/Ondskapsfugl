package me.hyv.of.engine.math;

import java.util.Stack;

public class ReuseableStack<T> {
	private Stack<T> source;
	private ObjectProvider<T> objectProvider;
	
	public ReuseableStack(ObjectProvider<T> objectProvider) {
		source = new Stack<>();
		this.objectProvider = objectProvider;
	}
	
	public T pop() {
		if(source.isEmpty())
			return objectProvider.newInstance();
		return source.pop();
	}
	
	public void push(T object) {
		source.push(object);
	}
	
	public interface ObjectProvider<T> {
        T newInstance();
    }
}
