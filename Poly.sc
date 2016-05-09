Poly {
	*new{
		arg decoupe=10; 
		^([0]++((2pi)/decoupe ! (decoupe-1)).integrate).collect{ arg x, i;
			Point(cos(x), sin(x));
		}
	}
}

PolygoneView : UserView{
	var <decoupe=10, <ind;
	*new{ arg w, b;
		if(b.class==Point){b.y=b.x};
		^super.new(w, b).init
	}
	decoupe_{ arg a;
		decoupe=a;
		this.ind=(..decoupe-1);
	}
	ind_{ arg a;
		ind=a.asSet.as(Array).sort;
		this.refresh;
	}
	init{
		this.drawFunc_{this.draw};
		this.ind=(..decoupe-1);
	}
	draw{
		var z=Poly(decoupe) * this.bounds.extent.x/2; 
		Pen.translate(*(this.bounds.extent/2).asArray);
		Pen.rotate(-0.5pi);
		(ind+++ind.rotate(-1))
		.do{ arg a;
			Pen.line(z[a[0]], z[a[1]]);
			Pen.fillStroke;
		};
	}
}
