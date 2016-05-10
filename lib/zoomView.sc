/*

a=ZoomView(nil, 200@200).front;
a.put(Button, Rect(30,50,10,30));
a.put(Button, Rect(50,50,10,30));
a.put(Button, Rect(0,50,10,30));
a.put(Button, Rect(100,50,10,30));
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
	}
	put{
		arg v, bounds;
		v.new(view, bounds.asRect);
		this.refreshMatrix
	}
	refreshMatrix{
		matrix.popHard;
		matrix.init(view);
		this.rangeH.doAction;
		this.rangeV.doAction;
	}
}