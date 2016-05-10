/*
RangedView().front
*/

RangedView : View{

	var <rangeH, <rangeV;
	var tailleRanger=20;
	var <view;
	var <zoom , <translate;
	var <>action;
	
	*new{ arg p, b=150@150;
		^super.new(p, b).init2
	}
	init2{
		view=View(this, this.trueSize).background_(Color.rand);
		#rangeH, rangeV=
		(	[0, 0]+++
			[
				`Rect(0, this.bounds.height-tailleRanger, this.bounds.width-tailleRanger, tailleRanger),
				`Rect(this.bounds.width-tailleRanger,0, tailleRanger, this.bounds.height-tailleRanger)
			]
			+++ [\horizontal, \vertical]
		)
		.collect{ arg args, i;
			RangeSlider(this, args[1].value)
			.lo_(0)
			.hi_(1)
			.orientation_(args[2])
		};
		this.onResize_{
			rangeH.bounds_(
				Rect(0, this.bounds.height-tailleRanger, this.bounds.width, tailleRanger)
			);
			rangeV.bounds_(
				Rect(this.bounds.width-tailleRanger, 0, tailleRanger, this.bounds.height-tailleRanger)
			);
		};
	}
	trueSize{
		var b=this.bounds;
		^b
		.width_(b.width-tailleRanger)
		.height_(b.height-tailleRanger)
	}
	view_{ arg v;
		view.close;
		view=v.new(this, this.trueSize);
	}
}
