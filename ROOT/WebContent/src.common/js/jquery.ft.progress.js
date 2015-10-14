;(function($){
	if(!$.fn.ftProgress){
		$.fn.ftProgress = function(options){
			options = options?options:{};
			methods = $.extend({},$.fn.ftProgress.methods);
			if(methods[options]){
				return methods[options].apply(this,Array.prototype.slice.call(arguments,1));
			}else if(!options||typeof options === 'object'){
				//合并css
				if(options.curCss)options.curCss = $.extend({},$.fn.ftProgress.defaults.curCss,options.curCss);
				if(options.css)options.css = $.extend({},$.fn.ftProgress.defaults.css,options.css);
				//合并options
				options = $.extend({},$.fn.ftProgress.defaults,options);
				return methods.init.call(this,options);
			}else{
				throw 'some err...';
			}
		}
		
		$.fn.ftProgress.defaults = $.extend({},$.fn.ft.defaults,{
			css :{display:'inline-block',background:'rgb(176,224,230)',textAlign:'center',position:'relative'},
			curCss : {position:'absolute',width:'0%',height:'100%',left:'0px',top:'0px',background:'-moz-linear-gradient(left,rgb(112,170,57) 0%, rgb(176,224,230) 100%)',background:' -webkit-gradient(linear,left,right,color-stop(0%,rgb(112,170,57)) , color-stop(100%,rgb(176,224,230) ) ) ' ,background:'-webkit-linear-gradient(left,rgb(112,170,57) 0%, rgb(176,224,230) 100%)'},
			showCss : {position:'relative',margin:'0px auto',zIndex:'50',width:'auto',height:'100%'}
		});
		
		
		$.fn.ftProgress.methods = $.extend({},$.fn.ft.methods,{
			init : function(options){
				for(var i = 0;i<this.length;i++){
					var dom = $(this).get(i);
					if(dom.stinit)break;
					//init
					$(dom).addClass('ft-progress');
					$(dom).css(options.css);
					var $cur = $('<div></div>');
					$cur.css(options.curCss);
					$cur.addClass('ft-progress-cur');
					$cur.appendTo($(dom));
					var $show = $('<div></div>');
					$show.text('0%');
					$show.addClass('ft-progress-show');
					$show.css(options.showCss);
					$show.appendTo($(dom));
					dom.stinit = true;
				}
			},
			value : function(options){
				if(options){
					$(this).find('.ft-progress-cur').css('width',options+'%');
					$(this).find('.ft-progress-show').text(options+'%');
				}else{
					return $(this).find('.ft-progress-show').text().replace('%','');
				}
			}
		});
	}
})(jQuery);