

Matrix {
	var <>a, //window 
	<>b, // coord
	>c; // tmp
	*new	{ arg  win;
		^super.new.init(win)
	}
	init{ arg win;
		a=win.asView;
		this.push;
		c=b;
	}
	push{
		b=a.children.collect(_.bounds);
	}
	pop{
		b=c
	}
	popHard{
		a.children.do{|v, i| v.bounds=c[i]}
	}
	/// interface
	zoomX{ arg  zoom;
		a.children.do{ |x, i|
			x.bounds_(
				b[i].bounds
				.extent_(
					(b[i].extent.x*zoom)
					@ 	b[i].extent.y
				)
				.origin_(
					(b[i].origin.x * zoom)
					@ b[i].origin.y
				)
			)
		};
	}
	zoomY{ arg  zoom;
		a.children.do{ |x, i|
			x.bounds_(
				b[i].bounds
				.extent_(
					b[i].extent.x @ (b[i].extent.y*zoom)
				)
				.origin_(
					b[i].origin.x @	(b[i].origin.y * zoom)
				)
			)
		};
	}
	globZoom{ arg  zoom;
		a.children.do{ |x, i|
			x.bounds_(
				b[i].bounds
				.extent_(
					b[i].extent*zoom
				)
				.origin_(
					b[i].origin * zoom
				)
			)
		};
	}
	translateX{ arg  zoom;
		a.children.do{ |x, i|
			x.bounds_(
				b[i].bounds.origin_(
					(b[i].origin.x+zoom)
					@ 	(b[i].origin.y)
				))
		}
	}
	translateY{ arg  zoom;
		a.children.do{ |x, i|
			x.bounds_(
				b[i].bounds.origin_(
					(b[i].origin.x)
					@ 	(b[i].origin.y+zoom)
				))
		}
	}
}



