// ----------------------
// Author: Chiknin
// ----------------------

//if (!window.console) {
//	window.console = {
//		log: function() {}
//	};
//}

(function() {
	
	PRODUCT_MODE = false;
	
	Bootstrap = {
		
        rootPath: '',

        cacheBuster: function() {
            return ((PRODUCT_MODE == false) ? ('?' + (new Date()).getTime()) : '');
        },
		
		loadCss: function(path) {
			document.write('<link rel="stylesheet" type="text/css" href="' + this.rootPath + path + this.cacheBuster() + '">');
		},
		
        loadScript: function(path) {
            document.write('<script type="text/javascript" src="' + this.rootPath + path + this.cacheBuster() + '"></script>');
        },
		
		startup: function(onStarted) {
			var me = this;
			Ems.startup({
				onStarted: function() {
					onStarted && onStarted.apply(this);
					window.unload = me.shutdown;
//					Bootstrap.hookWindowBeforeUnload();
				}
			});
		},
		
		shutdown: function() {
			Ems.shutdown();
		},
		hookWindowBeforeUnload: function() {
			//window.onunload
			var head = document.getElementsByTagName('body')[0];
			head.onbeforeunload = this.shutdown;
		}
    };
	
	var 
	cssPaths = [
		'lib/ext/resources/css/ext-all.css'
	],
	jsPaths = [
		'lib/ext/ext-debug.js',
//		'lib/ext/ext.js',
//		'lib/ext/classes.js',
//		'lib/ext/ext-all-dev.js',
//		'lib/ext/ext-all.js',
		
//		'src/ems/core/util/CheckLeaksUtil.js',

		'src/ems/core/Ems.js'
//		'build/build/src/ems/core/Ems.js'
	];
	
	for (var i = 0; i < cssPaths.length; i++) {
		Bootstrap.loadCss(cssPaths[i]);
	}
	for (var i = 0; i < jsPaths.length; i++) {
		Bootstrap.loadScript(jsPaths[i]);
	}
})();