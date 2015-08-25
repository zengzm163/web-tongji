(function() {
	var h = {},
		mt = {},
		c = {
			id: "cc7c68ca6e0732df36608aed8e72564c",
			dm: ["jointforce.com"],
			js: "tongji.baidu.com/hm-web/js/",
			etrk: [],
			icon: '',
			ctrk: true,
			align: 1,
			nv: -1,
			vdur: 1800000,
			age: 31536000000,
			rec: 0,
			rp: [],
			trust: 0,
			vcard: 0,
			qiao: 0,
			lxb: 0,
			conv: 0,
			comm: 0,
			apps: ''
		};
	var p = !0,
		q = null,
		r = !1;
	mt.h = {};
	mt.h.xa = /msie (\d+\.\d+)/i.test(navigator.userAgent);
	mt.h.cookieEnabled = navigator.cookieEnabled;
	mt.h.javaEnabled = navigator.javaEnabled();
	mt.h.language = navigator.language || navigator.browserLanguage || navigator.systemLanguage || navigator.userLanguage || "";
	mt.h.Aa = (window.screen.width || 0) + "x" + (window.screen.height || 0);
	mt.h.colorDepth = window.screen.colorDepth || 0;
	mt.cookie = {};
	mt.cookie.set = function(a, b, f) {
		var d;
		f.G && (d = new Date, d.setTime(d.getTime() + f.G));
		document.cookie = a + "=" + b + (f.domain ? "; domain=" + f.domain : "") + (f.path ? "; path=" + f.path : "") + (d ? "; expires=" + d.toGMTString() : "") + (f.Pa ? "; secure" : "")
	};
	mt.cookie.get = function(a) {
		return (a = RegExp("(^| )" + a + "=([^;]*)(;|$)").exec(document.cookie)) ? a[2] : q
	};
	mt.p = {};
	mt.p.Ia = function(a) {
		return document.getElementById(a)
	};
	mt.p.ka = function(a) {
		var b;
		for (b = "A";
		(a = a.parentNode) && 1 == a.nodeType;) if (a.tagName == b) return a;
		return q
	};
	(mt.p.ya = function() {
		function a() {
			if (!a.z) {
				a.z = p;
				for (var b = 0, f = d.length; b < f; b++) d[b]()
			}
		}
		function b() {
			try {
				document.documentElement.doScroll("left")
			} catch (d) {
				setTimeout(b, 1);
				return
			}
			a()
		}
		var f = r,
			d = [],
			k;
		document.addEventListener ? k = function() {
			document.removeEventListener("DOMContentLoaded", k, r);
			a()
		} : document.attachEvent && (k = function() {
			"complete" === document.readyState && (document.detachEvent("onreadystatechange", k), a())
		});
		(function() {
			if (!f) if (f = p, "complete" === document.readyState) a.z = p;
			else if (document.addEventListener) document.addEventListener("DOMContentLoaded", k, r), window.addEventListener("load", a, r);
			else if (document.attachEvent) {
				document.attachEvent("onreadystatechange", k);
				window.attachEvent("onload", a);
				var d = r;
				try {
					d = window.frameElement == q
				} catch (n) {}
				document.documentElement.doScroll && d && b()
			}
		})();
		return function(b) {
			a.z ? b() : d.push(b)
		}
	}()).z = r;
	mt.event = {};
	mt.event.e = function(a, b, f) {
		a.attachEvent ? a.attachEvent("on" + b, function(d) {
			f.call(a, d)
		}) : a.addEventListener && a.addEventListener(b, f, r)
	};
	mt.event.preventDefault = function(a) {
		a.preventDefault ? a.preventDefault() : a.returnValue = r
	};
	mt.l = {};
	mt.l.parse = function() {
		return (new Function('return (" + source + ")'))()
	};
	mt.l.stringify = function() {
		function a(a) {
			/["\\-]/.test(a) && (a = a.replace(/["\\-]/g, function(a) {
				var b = f[a];
				if (b) return b;
				b = a.charCodeAt();
				return "\\u00" + Math.floor(b / 16).toString(16) + (b % 16).toString(16)
			}));
			return '"' + a + '"'
		}
		function b(a) {
			return 10 > a ? "0" + a : a
		}
		var f = {
			"\b": "\\b",
			"\t": "\\t",
			"\n": "\\n",
			"\f": "\\f",
			"\r": "\\r",
			'"': '\\"',
			"\\": "\\\\"
		};
		return function(d) {
			switch (typeof d) {
			case "undefined":
				return "undefined";
			case "number":
				return isFinite(d) ? String(d) : "null";
			case "string":
				return a(d);
			case "boolean":
				return String(d);
			default:
				if (d === q) return "null";
				if (d instanceof Array) {
					var f = ["["],
						l = d.length,
						n, e, g;
					for (e = 0; e < l; e++) switch (g = d[e], typeof g) {
					case "undefined":
					case "function":
					case "unknown":
						break;
					default:
						n && f.push(","), f.push(mt.l.stringify(g)), n = 1
					}
					f.push("]");
					return f.join("")
				}
				if (d instanceof Date) return '"' + d.getFullYear() + "-" + b(d.getMonth() + 1) + "-" + b(d.getDate()) + "T" + b(d.getHours()) + ":" + b(d.getMinutes()) + ":" + b(d.getSeconds()) + '"';
				n = ["{"];
				e = mt.l.stringify;
				for (l in d) if (Object.prototype.hasOwnProperty.call(d, l)) switch (g = d[l], typeof g) {
				case "undefined":
				case "unknown":
				case "function":
					break;
				default:
					f && n.push(","), f = 1, n.push(e(l) + ":" + e(g))
				}
				n.push("}");
				return n.join("")
			}
		}
	}();
	mt.lang = {};
	mt.lang.d = function(a, b) {
		return "[object " + b + "]" === {}.toString.call(a)
	};
	mt.lang.Ma = function(a) {
		return mt.lang.d(a, "Number") && isFinite(a)
	};
	mt.lang.Oa = function(a) {
		return mt.lang.d(a, "String")
	};
	mt.localStorage = {};
	mt.localStorage.D = function() {
		if (!mt.localStorage.f) try {
			mt.localStorage.f = document.createElement("input"), mt.localStorage.f.type = "hidden", mt.localStorage.f.style.display = "none", mt.localStorage.f.addBehavior("#default#userData"), document.getElementsByTagName("head")[0].appendChild(mt.localStorage.f)
		} catch (a) {
			return r
		}
		return p
	};
	mt.localStorage.set = function(a, b, f) {
		var d = new Date;
		d.setTime(d.getTime() + f || 31536E6);
		try {
			window.localStorage ? (b = d.getTime() + "|" + b, window.localStorage.setItem(a, b)) : mt.localStorage.D() && (mt.localStorage.f.expires = d.toUTCString(), mt.localStorage.f.load(document.location.hostname), mt.localStorage.f.setAttribute(a, b), mt.localStorage.f.save(document.location.hostname))
		} catch (k) {}
	};
	mt.localStorage.get = function(a) {
		if (window.localStorage) {
			if (a = window.localStorage.getItem(a)) {
				var b = a.indexOf("|"),
					f = a.substring(0, b) - 0;
				if (f && f > (new Date).getTime()) return a.substring(b + 1)
			}
		} else if (mt.localStorage.D()) try {
			return mt.localStorage.f.load(document.location.hostname), mt.localStorage.f.getAttribute(a)
		} catch (d) {}
		return q
	};
	mt.localStorage.remove = function(a) {
		if (window.localStorage) window.localStorage.removeItem(a);
		else if (mt.localStorage.D()) try {
			mt.localStorage.f.load(document.location.hostname), mt.localStorage.f.removeAttribute(a), mt.localStorage.f.save(document.location.hostname)
		} catch (b) {}
	};
	mt.sessionStorage = {};
	mt.sessionStorage.set = function(a, b) {
		if (window.sessionStorage) try {
			window.sessionStorage.setItem(a, b)
		} catch (f) {}
	};
	mt.sessionStorage.get = function(a) {
		return window.sessionStorage ? window.sessionStorage.getItem(a) : q
	};
	mt.sessionStorage.remove = function(a) {
		window.sessionStorage && window.sessionStorage.removeItem(a)
	};
	mt.V = {};
	mt.V.log = function(a, b) {
		var f = new Image,
			d = "mini_tangram_log_" + Math.floor(2147483648 * Math.random()).toString(36);
		window[d] = f;
		f.onload = f.onerror = f.onabort = function() {
			f.onload = f.onerror = f.onabort = q;
			f = window[d] = q;
			b && b(a)
		};
		f.src = a
	};
	mt.N = {};
	mt.N.qa = function() {
		var a = "";
		if (navigator.plugins && navigator.mimeTypes.length) {
			var b = navigator.plugins["Shockwave Flash"];
			b && b.description && (a = b.description.replace(/^.*\s+(\S+)\s+\S+$/, "$1"))
		} else if (window.ActiveXObject) try {
			if (b = new ActiveXObject("ShockwaveFlash.ShockwaveFlash"))(a = b.GetVariable("$version")) && (a = a.replace(/^.*\s+(\d+),(\d+).*$/, "$1.$2"))
		} catch (f) {}
		return a
	};
	mt.N.Ga = function(a, b, f, d, k) {
		return '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="' + a + '" width="' + f + '" height="' + d + '"><param name="movie" value="' + b + '" /><param name="flashvars" value="' + (k || "") + '" /><param name="allowscriptaccess" value="always" /><embed type="application/x-shockwave-flash" name="' + a + '" width="' + f + '" height="' + d + '" src="' + b + '" flashvars="' + (k || "") + '" allowscriptaccess="always" /></object>'
	};
	mt.url = {};
	mt.url.i = function(a, b) {
		var f = a.match(RegExp("(^|&|\\?|#)(" + b + ")=([^&#]*)(&|$|#)", ""));
		return f ? f[3] : q
	};
	mt.url.Ka = function(a) {
		return (a = a.match(/^(https?:)\/\//)) ? a[1] : q
	};
	mt.url.na = function(a) {
		return (a = a.match(/^(https?:\/\/)?([^\/\?#]*)/)) ? a[2].replace(/.*@/, "") : q
	};
	mt.url.Q = function(a) {
		return (a = mt.url.na(a)) ? a.replace(/:\d+$/, "") : a
	};
	mt.url.Ja = function(a) {
		return (a = a.match(/^(https?:\/\/)?[^\/]*(.*)/)) ? a[2].replace(/[\?#].*/, "").replace(/^$/, "/") : q
	};
	h.o = {
		La: "http://tongji.baidu.com/hm-web/welcome/ico",
		L: "localhost:8080/tongji/vistiLog/record",
		Z: "baidu.com",
		ta: "hmmd",
		ua: "hmpl",
		sa: "hmkw",
		ra: "hmci",
		va: "hmsr",
		m: 0,
		j: Math.round(+new Date / 1E3),
		protocol: "https:" == document.location.protocol ? "https:" : "http:",
		Na: 0,
		da: 6E5,
		ea: 10,
		O: 1024,
		ca: 1,
		A: 2147483647,
		W: "cc cf ci ck cl cm cp cw ds ep et fl ja ln lo lt nv rnd si st su v cv lv api tt u".split(" ")
	};
	(function() {
		var a = {
			k: {},
			e: function(a, f) {
				this.k[a] = this.k[a] || [];
				this.k[a].push(f)
			},
			r: function(a, f) {
				this.k[a] = this.k[a] || [];
				for (var d = this.k[a].length, k = 0; k < d; k++) this.k[a][k](f)
			}
		};
		return h.s = a
	})();
	(function() {
		function a(a, d) {
			var k = document.createElement("script");
			k.charset = "utf-8";
			b.d(d, "Function") && (k.readyState ? k.onreadystatechange = function() {
				if ("loaded" === k.readyState || "complete" === k.readyState) k.onreadystatechange = q, d()
			} : k.onload = function() {
				d()
			});
			k.src = a;
			var l = document.getElementsByTagName("script")[0];
			l.parentNode.insertBefore(k, l)
		}
		var b = mt.lang;
		return h.load = a
	})();
	(function() {
		var a = mt.p,
			b = mt.event,
			f = mt.h,
			d = h.o,
			k = [],
			l = {
				X: function() {
					c.ctrk && (b.e(document, "mouseup", l.ba()), b.e(window, "unload", function() {
						l.B()
					}), setInterval(function() {
						l.B()
					}, d.da))
				},
				ba: function() {
					return function(a) {
						a = l.la(a);
						if ("" !== a) {
							var b = (d.protocol + "//" + d.L + "?" + h.b.U().replace(/ep=[^&]*/, "ep=" + encodeURIComponent("[" + a + "]"))).length;
							b + (d.A + "").length > d.O || (b + encodeURIComponent(k.join(",") + (k.length ? "," : "")).length + (d.A + "").length > d.O && l.B(), k.push(a), (k.length >= d.ea || /t:a/.test(a)) && l.B())
						}
					}
				},
				la: function(b) {
					if (0 === d.ca) {
						var e = b.target || b.srcElement,
							g = e.tagName.toLowerCase();
						if ("embed" == g || "object" == g) return ""
					}
					f.xa ? (e = Math.max(document.documentElement.scrollTop, document.body.scrollTop), g = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft), g = b.clientX + g, e = b.clientY + e) : (g = b.pageX, e = b.pageY);
					var m = window.innerWidth || document.documentElement.clientWidth || document.body.offsetWidth;
					switch (c.align) {
					case 1:
						g -= m / 2;
						break;
					case 2:
						g -= m
					}
					g = "{x:" + g + ",y:" + e + ",";
					e = b.target || b.srcElement;
					return g = (b = "a" == e.tagName.toLowerCase() ? e : a.ka(e)) ? g + ("t:a,u:" + encodeURIComponent(b.href) + "}") : g + "t:b}"
				},
				B: function() {
					0 !== k.length && (h.b.a.et = 2, h.b.a.ep = "[" + k.join(",") + "]", h.b.g(), k = [])
				}
			};
		h.s.e("pv-b", l.X);
		return l
	})();
	(function() {
		function a() {
			return function() {
				h.b.a.nv = 0;
				h.b.a.st = 4;
				h.b.a.et = 3;
				h.b.a.ep = h.F.oa() + "," + h.F.ma();
				h.b.g()
			}
		}
		function b() {
			clearTimeout(x);
			var a;
			w && (a = "visible" == document[w]);
			y && (a = !document[y]);
			e = "undefined" == typeof a ? p : a;
			if ((!n || !g) && e && m) u = p, t = +new Date;
			else if (n && g && (!e || !m)) u = r, s += +new Date - t;
			n = e;
			g = m;
			x = setTimeout(b, 100)
		}
		function f(a) {
			var g = document,
				t = "";
			if (a in g) t = a;
			else for (var s = ["webkit", "ms", "moz", "o"], b = 0; b < s.length; b++) {
				var d = s[b] + a.charAt(0).toUpperCase() + a.slice(1);
				if (d in g) {
					t = d;
					break
				}
			}
			return t
		}
		function d(a) {
			if (!("focus" == a.type || "blur" == a.type) || !(a.target && a.target != window)) m = "focus" == a.type || "focusin" == a.type ? p : r, b()
		}
		var k = mt.event,
			l = h.s,
			n = p,
			e = p,
			g = p,
			m = p,
			v = +new Date,
			t = v,
			s = 0,
			u = p,
			w = f("visibilityState"),
			y = f("hidden"),
			x;
		b();
		(function() {
			var a = w.replace(/[vV]isibilityState/, "visibilitychange");
			k.e(document, a, b);
			k.e(window, "pageshow", b);
			k.e(window, "pagehide", b);
			"object" == typeof document.onfocusin ? (k.e(document, "focusin", d), k.e(document, "focusout", d)) : (k.e(window, "focus", d), k.e(window, "blur", d))
		})();
		h.F = {
			oa: function() {
				return +new Date - v
			},
			ma: function() {
				return u ? +new Date - t + s : s
			}
		};
		l.e("pv-b", function() {
			k.e(window, "unload", a())
		});
		return h.F
	})();
	(function() {
		var a = mt.lang,
			b = h.o,
			f = h.load,
			d = {
				wa: function(d) {
					if ((void 0 === window._dxt || a.d(window._dxt, "Array")) && "undefined" !== typeof h.b) {
						var l = h.b.H();
						f([b.protocol, "//datax.baidu.com/x.js?si=", c.id, "&dm=", encodeURIComponent(l)].join(""), d)
					}
				},
				Fa: function(b) {
					if (a.d(b, "String") || a.d(b, "Number")) window._dxt = window._dxt || [], window._dxt.push(["_setUserId", b])
				}
			};
		return h.fa = d
	})();
	(function() {
		function a(g) {
			for (var b in g) if ({}.hasOwnProperty.call(g, b)) {
				var d = g[b];
				f.d(d, "Object") || f.d(d, "Array") ? a(d) : g[b] = String(d)
			}
		}
		function b(a) {
			return a.replace ? a.replace(/'/g, "'0").replace(/\*/g, "'1").replace(/!/g, "'2") : a
		}
		var f = mt.lang,
			d = mt.l,
			k = h.o,
			l = h.s,
			n = h.fa,
			e = {
				R: q,
				q: [],
				C: 0,
				S: r,
				init: function() {
					e.c = 0;
					e.R = {
						push: function() {
							e.K.apply(e, arguments)
						}
					};
					l.e("pv-b", function() {
						e.ga();
						e.ha()
					});
					l.e("pv-d", e.ia);
					l.e("stag-b", function() {
						h.b.a.api = e.c || e.C ? e.c + "_" + e.C : ""
					});
					l.e("stag-d", function() {
						h.b.a.api = 0;
						e.c = 0;
						e.C = 0
					})
				},
				ga: function() {
					var a = window._hmt;
					if (a && a.length) for (var b = 0; b < a.length; b++) {
						var d = a[b];
						switch (d[0]) {
						case "_setAccount":
							1 < d.length && /^[0-9a-z]{32}$/.test(d[1]) && (e.c |= 1, window._bdhm_account = d[1]);
							break;
						case "_setAutoPageview":
							if (1 < d.length && (d = d[1], r === d || p === d)) e.c |= 2, window._bdhm_autoPageview = d
						}
					}
				},
				ha: function() {
					if ("undefined" === typeof window._bdhm_account || window._bdhm_account === c.id) {
						window._bdhm_account = c.id;
						var a = window._hmt;
						if (a && a.length) for (var b = 0, d = a.length; b < d; b++) f.d(a[b], "Array") && "_trackEvent" !== a[b][0] && "_trackRTEvent" !== a[b][0] ? e.K(a[b]) : e.q.push(a[b]);
						window._hmt = e.R
					}
				},
				ia: function() {
					if (0 < e.q.length) for (var a = 0, b = e.q.length; a < b; a++) e.K(e.q[a]);
					e.q = q
				},
				K: function(a) {
					if (f.d(a, "Array")) {
						var b = a[0];
						if (e.hasOwnProperty(b) && f.d(e[b], "Function")) e[b](a)
					}
				},
				_trackPageview: function(a) {
					if (1 < a.length && a[1].charAt && "/" == a[1].charAt(0)) {
						e.c |= 4;
						h.b.a.et = 0;
						h.b.a.ep = "";
						h.b.I ? (h.b.a.nv = 0, h.b.a.st = 4) : h.b.I = p;
						var b = h.b.a.u,
							d = h.b.a.su;
						h.b.a.u = k.protocol + "//" + document.location.host + a[1];
						e.S || (h.b.a.su = document.location.href);
						h.b.g();
						h.b.a.u = b;
						h.b.a.su = d
					}
				},
				_trackEvent: function(a) {
					2 < a.length && (e.c |= 8, h.b.a.nv = 0, h.b.a.st = 4, h.b.a.et = 4, h.b.a.ep = b(a[1]) + "*" + b(a[2]) + (a[3] ? "*" + b(a[3]) : "") + (a[4] ? "*" + b(a[4]) : ""), h.b.g())
				},
				_setCustomVar: function(a) {
					if (!(4 > a.length)) {
						var d = a[1],
							f = a[4] || 3;
						if (0 < d && 6 > d && 0 < f && 4 > f) {
							e.C++;
							for (var t = (h.b.a.cv || "*").split("!"), s = t.length; s < d - 1; s++) t.push("*");
							t[d - 1] = f + "*" + b(a[2]) + "*" + b(a[3]);
							h.b.a.cv = t.join("!");
							a = h.b.a.cv.replace(/[^1](\*[^!]*){2}/g, "*").replace(/((^|!)\*)+$/g, "");
							"" !== a ? h.b.setData("Hm_cv_" + c.id, encodeURIComponent(a), c.age) : h.b.za("Hm_cv_" + c.id)
						}
					}
				},
				_setReferrerOverride: function(a) {
					1 < a.length && (h.b.a.su = a[1].charAt && "/" == a[1].charAt(0) ? k.protocol + "//" + window.location.host + a[1] : a[1], e.S = p)
				},
				_trackOrder: function(b) {
					b = b[1];
					f.d(b, "Object") && (a(b), e.c |= 16, h.b.a.nv = 0, h.b.a.st = 4, h.b.a.et = 94, h.b.a.ep = d.stringify(b), h.b.g())
				},
				_trackMobConv: function(a) {
					if (a = {
						webim: 1,
						tel: 2,
						map: 3,
						sms: 4,
						callback: 5,
						share: 6
					}[a[1]]) e.c |= 32, h.b.a.et = 93, h.b.a.ep = a, h.b.g()
				},
				_trackRTPageview: function(b) {
					b = b[1];
					f.d(b, "Object") && (a(b), b = d.stringify(b), 512 >= encodeURIComponent(b).length && (e.c |= 64, h.b.a.rt = b))
				},
				_trackRTEvent: function(b) {
					b = b[1];
					if (f.d(b, "Object")) {
						a(b);
						b = encodeURIComponent(d.stringify(b));
						var m = function(a) {
								var b = h.b.a.rt;
								e.c |= 128;
								h.b.a.et = 90;
								h.b.a.rt = a;
								h.b.g();
								h.b.a.rt = b
							},
							l = b.length;
						if (900 >= l) m.call(this, b);
						else for (var l = Math.ceil(l / 900), t = "block|" + Math.round(Math.random() * k.A).toString(16) + "|" + l + "|", s = [], u = 0; u < l; u++) s.push(u), s.push(b.substring(900 * u, 900 * u + 900)), m.call(this, t + s.join("|")), s = []
					}
				},
				_setUserId: function(a) {
					a = a[1];
					n.wa();
					n.Fa(a)
				}
			};
		e.init();
		h.$ = e;
		return h.$
	})();
	(function() {
		function a() {
			"undefined" == typeof window["_bdhm_loaded_" + c.id] && (window["_bdhm_loaded_" + c.id] = p, this.a = {}, this.I = r, this.init())
		}
		var b = mt.url,
			f = mt.V,
			d = mt.N,
			k = mt.lang,
			l = mt.cookie,
			n = mt.h,
			e = mt.localStorage,
			g = mt.sessionStorage,
			m = h.o,
			v = h.s;
		a.prototype = {
			J: function(a, b) {
				a = "." + a.replace(/:\d+/, "");
				b = "." + b.replace(/:\d+/, "");
				var d = a.indexOf(b);
				return -1 < d && d + b.length == a.length
			},
			T: function(a, b) {
				a = a.replace(/^https?:\/\//, "");
				return 0 === a.indexOf(b)
			},
			w: function(a) {
				for (var d = 0; d < c.dm.length; d++) if (-1 < c.dm[d].indexOf("/")) {
					if (this.T(a, c.dm[d])) return p
				} else {
					var f = b.Q(a);
					if (f && this.J(f, c.dm[d])) return p
				}
				return r
			},
			H: function() {
				for (var a = document.location.hostname, b = 0, d = c.dm.length; b < d; b++) if (this.J(a, c.dm[b])) return c.dm[b].replace(/(:\d+)?[\/\?#].*/, "");
				return a
			},
			P: function() {
				for (var a = 0, b = c.dm.length; a < b; a++) {
					var d = c.dm[a];
					if (-1 < d.indexOf("/") && this.T(document.location.href, d)) return d.replace(/^[^\/]+(\/.*)/, "$1") + "/"
				}
				return "/"
			},
			pa: function() {
				if (!document.referrer) return m.j - m.m > c.vdur ? 1 : 4;
				var a = r;
				this.w(document.referrer) && this.w(document.location.href) ? a = p : (a = b.Q(document.referrer), a = this.J(a || "", document.location.hostname));
				return a ? m.j - m.m > c.vdur ? 1 : 4 : 3
			},
			getData: function(a) {
				try {
					return l.get(a) || g.get(a) || e.get(a)
				} catch (b) {}
			},
			setData: function(a, b, d) {
				try {
					l.set(a, b, {
						domain: this.H(),
						path: this.P(),
						G: d
					}), d ? e.set(a, b, d) : g.set(a, b)
				} catch (f) {}
			},
			za: function(a) {
				try {
					l.set(a, "", {
						domain: this.H(),
						path: this.P(),
						G: -1
					}), g.remove(a), e.remove(a)
				} catch (b) {}
			},
			Da: function() {
				var a, b, d, f, e;
				m.m = this.getData("Hm_lpvt_" + c.id) || 0;
				13 == m.m.length && (m.m = Math.round(m.m / 1E3));
				b = this.pa();
				a = 4 != b ? 1 : 0;
				if (d = this.getData("Hm_lvt_" + c.id)) {
					f = d.split(",");
					for (e = f.length - 1; 0 <= e; e--) 13 == f[e].length && (f[e] = "" + Math.round(f[e] / 1E3));
					for (; 2592E3 < m.j - f[0];) f.shift();
					e = 4 > f.length ? 2 : 3;
					for (1 === a && f.push(m.j); 4 < f.length;) f.shift();
					d = f.join(",");
					f = f[f.length - 1]
				} else d = m.j, f = "", e = 1;
				this.setData("Hm_lvt_" + c.id, d, c.age);
				this.setData("Hm_lpvt_" + c.id, m.j);
				d = m.j == this.getData("Hm_lpvt_" + c.id) ? "1" : "0";
				if (0 === c.nv && this.w(document.location.href) && ("" === document.referrer || this.w(document.referrer))) a = 0, b = 4;
				this.a.nv = a;
				this.a.st = b;
				this.a.cc = d;
				this.a.lt = f;
				this.a.lv = e
			},
			U: function() {
				for (var a = [], b = 0, d = m.W.length; b < d; b++) {
					var f = m.W[b],
						e = this.a[f];
					"undefined" != typeof e && "" !== e && a.push(f + "=" + encodeURIComponent(e))
				}
				b = this.a.et;
				this.a.rt && (0 === b ? a.push("rt=" + encodeURIComponent(this.a.rt)) : 90 === b && a.push("rt=" + this.a.rt));
				return a.join("&")
			},
			Ea: function() {
				this.Da();
				this.a.si = c.id;
				this.a.su = document.referrer;
				this.a.ds = n.Aa;
				this.a.cl = n.colorDepth + "-bit";
				this.a.ln = n.language;
				this.a.ja = n.javaEnabled ? 1 : 0;
				this.a.ck = n.cookieEnabled ? 1 : 0;
				this.a.lo = "number" == typeof _bdhm_top ? 1 : 0;
				this.a.fl = d.qa();
				this.a.v = "1.0.94";
				this.a.cv = decodeURIComponent(this.getData("Hm_cv_" + c.id) || "");
				1 == this.a.nv && (this.a.tt = document.title || "");
				var a = document.location.href;
				this.a.cm = b.i(a, m.ta) || "";
				this.a.cp = b.i(a, m.ua) || "";
				this.a.cw = b.i(a, m.sa) || "";
				this.a.ci = b.i(a, m.ra) || "";
				this.a.cf = b.i(a, m.va) || ""
			},
			init: function() {
				try {
					this.Ea(), 0 === this.a.nv ? this.Ca() : this.M(".*"), h.b = this, this.aa(), v.r("pv-b"), this.Ba()
				} catch (a) {
					var b = [];
					b.push("si=" + c.id);
					b.push("n=" + encodeURIComponent(a.name));
					b.push("m=" + encodeURIComponent(a.message));
					b.push("r=" + encodeURIComponent(document.referrer));
					f.log(m.protocol + "//" + m.L + "?" + b.join("&"))
				}
			},
			Ba: function() {
				function a() {
					v.r("pv-d")
				}
				"undefined" === typeof window._bdhm_autoPageview || window._bdhm_autoPageview === p ? (this.I = p, this.a.et = 0, this.a.ep = "", this.g(a)) : a()
			},
			g: function(a) {
				var b = this;
				b.a.rnd = Math.round(Math.random() * m.A);
				v.r("stag-b");
				var d = m.protocol + "//" + m.L + "?" + b.U();
				v.r("stag-d");
				b.Y(d);
				f.log(d, function(d) {
					b.M(d);
					k.d(a, "Function") && a.call(b)
				})
			},
			aa: function() {
				var a = document.location.hash.substring(1),
					d = RegExp(c.id),
					f = -1 < document.referrer.indexOf(m.Z) ? p : r,
					e = b.i(a, "jn"),
					k = /^heatlink$|^select$/.test(e);
				a && (d.test(a) && f && k) && (a = document.createElement("script"), a.setAttribute("type", "text/javascript"), a.setAttribute("charset", "utf-8"), a.setAttribute("src", m.protocol + "//" + c.js + e + ".js?" + this.a.rnd), e = document.getElementsByTagName("script")[0], e.parentNode.insertBefore(a, e))
			},
			Y: function(a) {
				var b = g.get("Hm_unsent_" + c.id) || "",
					d = this.a.u ? "" : "&u=" + encodeURIComponent(document.location.href),
					b = encodeURIComponent(a.replace(/^https?:\/\//, "") + d) + (b ? "," + b : "");
				g.set("Hm_unsent_" + c.id, b)
			},
			M: function(a) {
				var b = g.get("Hm_unsent_" + c.id) || "";
				b && ((b = b.replace(RegExp(encodeURIComponent(a.replace(/^https?:\/\//, "")).replace(/([\*\(\)])/g, "\\$1") + "(%26u%3D[^,]*)?,?", "g"), "").replace(/,$/, "")) ? g.set("Hm_unsent_" + c.id, b) : g.remove("Hm_unsent_" + c.id))
			},
			Ca: function() {
				var a = this,
					b = g.get("Hm_unsent_" + c.id);
				if (b) for (var b = b.split(","), d = function(b) {
						f.log(m.protocol + "//" + decodeURIComponent(b).replace(/^https?:\/\//, ""), function(b) {
							a.M(b)
						})
					}, e = 0, k = b.length; e < k; e++) d(b[e])
			}
		};
		return new a
	})();
	(function() {
		var a = mt.p,
			b = mt.event,
			f = mt.url,
			d = mt.l;
		try {
			if (window.performance && performance.timing && "undefined" !== typeof h.b) {
				var k = +new Date,
					l = function(a) {
						var b = performance.timing,
							d = b[a + "Start"] ? b[a + "Start"] : 0;
						a = b[a + "End"] ? b[a + "End"] : 0;
						return {
							start: d,
							end: a,
							value: 0 < a - d ? a - d : 0
						}
					},
					n = q;
				a.ya(function() {
					n = +new Date
				});
				var e = function() {
						var a, b, e;
						e = l("navigation");
						b = l("request");
						e = {
							netAll: b.start - e.start,
							netDns: l("domainLookup").value,
							netTcp: l("connect").value,
							srv: l("response").start - b.start,
							dom: performance.timing.domInteractive - performance.timing.fetchStart,
							loadEvent: l("loadEvent").end - e.start
						};
						a = document.referrer;
						var g = q;
						b = q;
						if ("www.baidu.com" === (a.match(/^(http[s]?:\/\/)?([^\/]+)(.*)/) || [])[2]) g = f.i(a, "qid"), b = f.i(a, "click_t");
						a = g;
						e.qid = a != q ? a : "";
						b != q ? (e.bdDom = n ? n - b : 0, e.bdRun = k - b, e.bdDef = l("navigation").start - b) : (e.bdDom = 0, e.bdRun = 0, e.bdDef = 0);
						h.b.a.et = 87;
						h.b.a.ep = d.stringify(e);
						h.b.g()
					};
				b.e(window, "load", function() {
					setTimeout(e, 500)
				})
			}
		} catch (g) {}
	})();
	(function() {
		var a = h.o,
			b = {
				init: function() {
					try {
						if ("http:" === a.protocol) {
							var b = document.createElement("IFRAME");
							b.setAttribute("src", "http://boscdn.bpc.baidu.com/v1/holmes-moplus/mp-cdn.html");
							b.style.display = "none";
							b.style.width = "1";
							b.style.height = "1";
							b.Ha = "0";
							document.body.appendChild(b)
						}
					} catch (f) {}
				}
			},
			f = navigator.userAgent.toLowerCase(); - 1 < f.indexOf("android") && -1 === f.indexOf("micromessenger") && b.init()
	})();
	(function() {
		var a = mt.lang,
			b = mt.event,
			f = mt.l;
		if (c.comm && "undefined" !== typeof h.b) {
			var d = function(a) {
					if (a.item) {
						for (var b = a.length, d = Array(b); b--;) d[b] = a[b];
						return d
					}
					return [].slice.call(a)
				},
				k = /.*\/swt(\/)?([\?|#].*)?$/i,
				l = {
					click: function() {
						for (var a = [], b = d(document.getElementsByTagName("a")), b = [].concat.apply(b, d(document.getElementsByTagName("area"))), b = [].concat.apply(b, d(document.getElementsByTagName("img"))), e = /openZoosUrl\(|swt/, f = /\/LR\/Chatpre\.aspx/, g = 0, l = b.length; g < l; g++) {
							var m = b[g],
								n = m.getAttribute("onclick"),
								m = m.getAttribute("href");
							(e.test(n) || f.test(m) || k.test(m)) && a.push(b[g])
						}
						return a
					}
				},
				n = function(a, b) {
					for (var d in a) if (a.hasOwnProperty(d) && b.call(a, d, a[d]) === r) return r
				},
				e = function(b, d) {
					var e = {
						n: "swt",
						t: "clk"
					};
					e.v = b;
					if (d) {
						var g = d.getAttribute("href"),
							l = d.getAttribute("onclick") ? "" + d.getAttribute("onclick") : q;
						k.test(g) ? (e.sn = "mediate", e.snv = g) : a.d(l, "String") && (-1 === l.indexOf("openZoosUrl") && -1 !== l.indexOf("swt")) && (g = d.getAttribute("id") || "", e.sn = "wrap", e.snv = l, e.id = g)
					}
					h.b.a.et = 86;
					h.b.a.ep = f.stringify(e);
					h.b.g();
					for (e = +new Date; 500 >= +new Date - e;);
				},
				g, m = "/zoosnet" + (/\/$/.test("/zoosnet") ? "" : "/"),
				v = function(b, d) {
					if (g === d) return e(m + b, d), r;
					if (a.d(d, "Array") || a.d(d, "NodeList")) for (var f = 0, k = d.length; f < k; f++) if (g === d[f]) return e(m + b + "/" + (f + 1), d[f]), r
				};
			b.e(document, "click", function(b) {
				b = b || window.event;
				g = b.target || b.srcElement;
				var d = {};
				for (n(l, function(b, e) {
					d[b] = a.d(e, "Function") ? e() : document.getElementById(e)
				}); g && g !== document && n(d, v) !== r;) g = g.parentNode
			})
		}
	})();
})();