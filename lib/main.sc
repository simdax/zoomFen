MainFen : ZoomView {

	*new{ arg p, b=150@150;
		^super.new(p, b).initMainFen
	}
	initMainFen{
		view.close;
		view=MoveGhostView(this, this.trueSize);
		matrix=Matrix(view);
		view.addAction({ arg self, x, y, mod;
			view.children.postln;
			this.put(Button, Rect(x, y, 50,50));
		}, \receiveDragHandler);
	}
	
}