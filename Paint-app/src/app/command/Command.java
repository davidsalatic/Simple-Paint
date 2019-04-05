package app.command;

import geometry.Shape;

public interface Command {
	void execute();
	void unexecute();
	Shape getShape();
	String toString();
}
