;(function($){
	//扩展Jquery
	if(!$.fn.bordWidth){
		$.fn.bordWidth = function(){
			return $(this).outerWidth() - $(this).innerWidth();  
		}
	}
	if(!$.fn.paddWidth){
		$.fn.paddWidth = function(){
			return $(this).innerWidth() - $(this).width(); 
		}
	}
	if(!$.fn.margWidth){
		$.fn.margWidth = function(){
			return $(this).outerWidth(true) - $(this).outerWidth();
		}
	}
	if(!$.fn.bordHeight){
		$.fn.bordHeight = function(){
			return $(this).outerHeight() - $(this).innerHeight();  
		}
	}
	if(!$.fn.paddHeight){
		$.fn.paddHeight = function(){
			return $(this).innerHeight() - $(this).height(); 
		}
	}
	if(!$.fn.margHeight){
		$.fn.margHeight = function(){
			return $(this).outerHeight(true) - $(this).outerHeight();  
		}
	}
	if(!$.fn.moveWindowTo){
		$.fn.moveWindowTo = function(options){
			if(options==='topLeft'){
				$(this).css({left:"50px",top:'50px'});
			}else if(options==='topRight'){
				var ownerWidth = $(this).outerWidth(true);
				$(this).css({left:($.fn.ft.baseSize.x-ownerWidth-50)+"px",top:'50px'});
			}else if(options==='bottomLeft'){
				var ownerHeight = $(this).outerHeight(true);
				$(this).css({left:"50px",top:($.fn.ft.baseSize.y-ownerHeight-50)+'px'});
			}else if(options==='bottomRight'){
				var ownerWidth = $(this).outerWidth(true);
				var ownerHeight = $(this).outerHeight(true);
				$(this).css({left:($.fn.ft.baseSize.x-ownerWidth-50)+"px",top:($.fn.ft.baseSize.y-ownerHeight-50)+'px'});
			}else if(typeof options === 'object'){
				$(this).css({left:options.x+"px",top:options.y+'px'});
			}else{
				var ownerWidth = $(this).outerWidth(true);
				var ownerHeight = $(this).outerHeight(true);
				$(this).css({left:(($.fn.ft.baseSize.x-ownerWidth)/2)+"px",top:(($.fn.ft.baseSize.y-ownerHeight)/2)+'px'});
			}
		}
	}
	
	//定义ft插件默认参数
	if(!$.fn.ft){
		$.fn.ft = {};
	}
	if(!$.fn.ft.defaults){
		$.fn.ft.defaults = {};
	}
	if(!$.fn.ft.methods){
		$.fn.ft.methods = { init : function(){}};
	}
	if(!$.fn.ft.baseSize){
		$.fn.ft.baseSize = {x:$(window).width(),y:$(window).height()};
		$(window).resize(function(){
			$.fn.ft.baseSize.x = $(window).width();
			$.fn.ft.baseSize.y =$(window).height();
		});
	}
	if(!$.fn.ft.range){
		$.fn.ft.range = undefined;
	}
	if(!$.flushRange){
		$.flushRange = function(){
			 var selection= window.getSelection ? window.getSelection() : document.selection;
	         var range= selection.createRange ? selection.createRange() : selection.getRangeAt(0)
	         $.fn.ft.range = range;
		};
		$.fn.flushRange = function(){
			$(this).bind({
                mouseup:$.flushRange,
                keyup:$.flushRange,
                change:$.flushRange
            });
		};
		
	}
})(jQuery);