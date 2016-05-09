MainFen : ZoomView {

	*new{ arg p, b=150@150;
		^super.new(p, b).initMainFen
	}
	initMainFen{
		view.close;
		view=GhostView(this, this.trueSize);
		matrix=Matrix(view);
		view.receiveDragHandler_{ arg self, x, y, mod;
			[x, y].postln;
			view.drawFunc_{};
			view.refresh;
			this.add(Button, Rect(x, y, 50,50));
		};
	}
	
}