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
		
		loadCss: function(id, path) {
			document.write('<link id="' + id + '" rel="stylesheet" type="text/css" href="' + this.rootPath + path + this.cacheBuster() + '">');
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
			window.unload = null;
			Ems.shutdown();
		},
		hookWindowBeforeUnload: function() {
			//window.onunload
			var head = document.getElementsByTagName('body')[0];
			head.onbeforeunload = this.shutdown;
		}
    };
	
	Bootstrap.loadCss('css-ext', 'lib/ext/resources/css/ext-all.css');
//	Bootstrap.loadCss('css-ext', 'lib/ext/resources/css/ext-all-access.css');
//	Bootstrap.loadCss('css-ext', 'lib/ext/resources/css/ext-all-gray.css');
	Bootstrap.loadCss('css-ems', 'src/ems/core/resources/css/ems-all.css');
	
	var jsPaths = [];
	if (PRODUCT_MODE == true) {
		jsPaths = [
			'lib/ext/ext-all.js'
		];
	} else {
		jsPaths = [
			'lib/ext/ext-debug.js'
	//		'lib/ext/ext.js',
	//		'lib/ext/classes.js',
	//		'lib/ext/ext-all-dev.js',
	//		'lib/ext/ext-all.js',
	//		'src/ems/core/util/CheckLeaksUtil.js',
	//		'deploy/lib/ext/ext-all-min.js',
	//		,'build/build/src/ems/core/Ems.js'
		];
	}
	jsPaths.push('src/ems/core/Ems.js');
	
	for (var i = 0; i < jsPaths.length; i++) {
		Bootstrap.loadScript(jsPaths[i]);
	}
})();