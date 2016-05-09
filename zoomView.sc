/*

a=ZoomView(nil, 200@200).front;
a.add(Button, Rect(30,50,10,30));
a.add(Button, Rect(50,50,10,30));
a.add(Button, Rect(0,50,10,30));
*/

ZoomView : RangedView{

	var matrix;
	
	*new{ arg p, b;
		^super.new(p, b).goMat
	}
	goMat{
		var action;
		matrix ?? {matrix=Matrix(view)};
		action={ 
			var originV=(1-rangeV.hi);
			var originH=(rangeH.lo);
			var zoomV=(rangeV.range+originV).reciprocal;
			var zoomH=(rangeH.range+originH).reciprocal;
			matrix.zoomY(zoomV);
			matrix.push;
			matrix.translateY(view.bounds.height*originV*zoomV.neg);
			matrix.push;
			matrix.zoomX(zoomH);
			matrix.push;
			matrix.translateX(view.bounds.width*originH*zoomH.neg);
			matrix.pop
		};
		rangeV.action=action; rangeH.action=action;
		// rangeV.action_{ arg self;
		// 	var origin=(1-self.hi);
		// 	var zoom=(self.range+origin).reciprocal;
		// 	matrix.zoomY(zoom);
		// 	matrix.push;
		// 	matrix.translateY(view.bounds.height*origin*zoom.neg);
		// 	matrix.pop
		// };
		// rangeH.action_{ arg self;
		// 	var origin=(self.lo);
		// 	var zoom=(self.range+origin).reciprocal;
		// 	matrix.zoomX(zoom);
		// 	matrix.push;
		// 	matrix.translateX(view.bounds.width*origin*zoom.neg);
		// 	matrix.pop
		// }
	}
	add{ arg v, bounds;
		v.new(view, bounds.asRect);
		matrix.a=view; matrix.a.children.postln;
		matrix.b=matrix.b.add(bounds);
		matrix.c=matrix.b;
		this.rangeH.doAction;
		this.rangeV.doAction;
	}
}