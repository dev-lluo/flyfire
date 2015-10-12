;(function($){
	if(!$.ft.ftTemplate){
		$.ft.ftTemplate = function(options){
			options = options?options:{};
			methods = $.extend({},$.fn.ftTemplate.methods);
			if(methods[options]){
				return methods[options].apply(this,Array.prototype.slice.call(arguments,1));
			}else if(!options||typeof options === 'object'){
				//合并css
				if(options.css)options.css = $.extend({},$.fn.ftTemplate.defaults.css,options.css);
				//合并options
				options = $.extend({},$.fn.ftTemplate.defaults,options);
				return methods.init.call(this,options);
			}else{
				throw 'some err...';
			}
		}
		
		$.fn.ftTemplate.defaults = $.extend({},$.fn.ft.defaults,{

		});
		
		
		$.fn.ftTemplate.methods = $.extend({},$.fn.ft.methods,{
			init : function(options){
				for(var i = 0;i<this.length;i++){
					var dom = $(this).get(i);
					if(dom.stinit)break;
					//init
					dom.stinit = true;
				}
			}
		});
	}
})(jQuery);