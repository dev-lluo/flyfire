;(function($){
	if(!$.fn.ftTextarea){
		$.fn.ftTextarea = function(options){
			options = options?options:{};
			methods = $.extend({},$.fn.ftTextarea.methods);
			if(methods[options]){
				return methods[options].apply(this,Array.prototype.slice.call(arguments,1));
			}else if(!options||typeof options === 'object'){
				//合并css
				if(options.toolbarInnerCss)options.toolbarInnerCss = $.extend({},$.fn.ftTextarea.defaults.toolbarInnerCss,options.toolbarInnerCss);
				if(options.editorareaCss)options.editorareaCss = $.extend({},$.fn.ftTextarea.defaults.editorareaCss,options.editorareaCss);
				if(options.toolbarCss)options.toolbarCss = $.extend({},$.fn.ftTextarea.defaults.toolbarCss,options.toolbarCss);
				if(options.css)options.css = $.extend({},$.fn.ftTextarea.defaults.css,options.css);
				//合并options
				options = $.extend({},$.fn.ftTextarea.defaults,options);
				return methods.init.call(this,options);
			}else{
				throw 'some err...';
			}
		}
		
		$.fn.ftTextarea.defaults = $.extend({},$.fn.ft.defaults,{
			contenteditable : true,
			iconList :[
			           {name:'smile',icon:'&#xf0133;'},
			           {name:'rmb',icon:'&#xf0280;'},
			           {name:'love',icon:'&#xf0145;'},
			           {name:'stop',icon:'&#xf018c;'},
			           {name:'cry',icon:'&#xf014e;'},
			           {name:'cool',icon:'&#xf011e;'},
			           {name:'drink',icon:'&#xf0089;'},
			           {name:'fly',icon:'&#xf003d;'}
			           ],
			toolBar : [
			           {name:'icon',icon:'&#xf0133;',css:{width:'50px',height:'100%',float:'left'},event: function(source){
			        	  var $source =  $(source);
			        	  var baseP = $source.find('li.icon').offset();
			        	  baseP.top = baseP.top+$source.find('li.icon').outerHeight(true);
			        	  $source.find('ul.iconlist').css({left:baseP.left+'px',top:baseP.top,display:'block'});
			           }},
			           {name:'img',icon:'&#xf0119;',css:{width:'50px',height:'100%',float:'left'},event: function(source){}},
			           {name:'info',icon:'&#xf0142;',css:{width:'50px',height:'100%',float:'left'},event: function(source){}}
			           ],
			css : {
				width:'96%',
				height:'96%',
				padding:'1%',
				margin:'1%',
				backgroundColor:'#e7ecee',
				borderRadius:'5px'
			},
			iconListCss :{
				margin:'0px',
				padding:'5px',
				fontSize:'20px',
				border:'5px solid rgba(231, 236, 238,0.5)',
				borderRadius:'3px',
				position:'fixed',
				display:'none',
				backgroundColor:'white'
			},
			iconItemCss :{
				margin : '0px',
				padding :'2px',
				float:'left',
				width:'30px',
				lineHeight:'30px',
				height:'30px',
				cursor:'pointer'
			},
			toolbarCss : {
				width:"100%",
				height:"50px"
			},
			toolbarInnerCss : {
				height:'50px',
				fontSize: '35px',
				cursor:'pointer',
				lineHeight:'35px',
				textAlign:'center'
			},
			editorareaCss : {
				width:'98%',
				padding:'1%',
				backgroundColor:'white',
				cursor:'text',
				fontSize:'20px',
				letterSpacing: '1px'
			}
		});
		
		
		$.fn.ftTextarea.methods = $.extend({},$.fn.ft.methods,{
			init : function(options){
				for(var i = 0;i<this.length;i++){
					var dom = $(this).get(i);
					if(dom.stinit)break;
					//init
					$(dom).css(options.css);
					$(dom).addClass('ft-textarea');
					
					var $toolbar = $('<div class="text-editor-toolbar"></div>');
					$toolbar.css(options.toolbarCss);
					$toolbar.appendTo($(dom));
					
					var $toolbarInner = $('<ul></ul>');
					$toolbarInner.css(options.toolbarInnerCss);
					$toolbarInner.appendTo($toolbar);
					for(var i = 0;i<options.toolBar.length;i++){
						var bar = options.toolBar[i];
						var $li = $('<li></li>');
						$li.addClass(bar.name);
						$li.addClass('iconfont');
						$li.html(bar.icon);
						$li.css(bar.css);
						$li.get(0).etemp = bar.event;
						$li.click(function(){
							this.etemp(dom);
						});
						$li.appendTo($toolbarInner);
					}
					
					var $editorarea = $('<div class="text-editor-area iconfont"></div>');
					$editorarea.css(options.editorareaCss);
					$editorarea.attr('contenteditable',options.contenteditable);
					$editorarea.flushRange();
					$editorarea.appendTo($(dom));
					var realHeight = $(dom).height();
					var toolbarHeight = $toolbar.outerHeight(true);
					var editorareaHeight = realHeight-toolbarHeight;
					var bordH = $editorarea.bordHeight();  
					var paddH = $editorarea.paddHeight();
					var margH = $editorarea.margHeight(); 
					editorareaHeight = editorareaHeight - bordH - paddH - margH;
					$editorarea.css({height:editorareaHeight+'px'});
					
					
					
					var $iconlist = $('<ul class="iconlist"></ul>');
					var $iconitem = $('<li class="iconitem"></li>');
					$iconitem.css(options.iconItemCss);
					$iconitem.click(function(){
						$(this).parents('.ft-textarea').ftTextarea('insert',$(this).html());
						$iconlist.css({display:'none'});
						$iconlist.unbind('mouseleave');
					});
					$iconlist.css(options.iconListCss);
					$iconlist.appendTo($(dom));
					$iconlist.css({width:$iconitem.outerWidth()*8,height:$editorarea.height()/4*3});
					$iconlist.bind('mouseover',function(){
						$(this).bind('mouseleave',function(){
							$iconlist.css({display:'none'});
							$iconlist.unbind('mouseleave');
						});
					});
					for(var i = 0;i<options.iconList.length;i++){
						var iconItem = options.iconList[i];
						var _$iconitem = $iconitem.clone(true);
						_$iconitem.html(iconItem.icon);
						_$iconitem.addClass('iconfont');
						_$iconitem.attr(iconItem.name);
						_$iconitem.appendTo($iconlist);
					}
					
					dom.stinit = true;
					$(dom).mouseover(function(){
						$(this).find('div.text-editor-area').change();
					});
				}
			},
			insert : function(options){
				 if (!window.getSelection){
		                $(this).find('.text-editor-area').focus();
		                var selection= window.getSelection ? window.getSelection() : document.selection;
		                var range= selection.createRange ? selection.createRange() : selection.getRangeAt(0);
		                range.pasteHTML(options);
		                range.collapse(false);
		                range.select();
		            }else{
		            	$(this).find('.text-editor-area').focus();
		                var selection= window.getSelection ? window.getSelection() : document.selection;
		                if(!$.fn.ft.range) $.fn.ft.range = selection.createRange ? selection.createRange() : selection.getRangeAt(0);;
		                selection.addRange($.fn.ft.range);
		                var range = $.fn.ft.range;
		                range.collapse(false);
		                var hasR = range.createContextualFragment(options);
		                var hasR_lastChild = hasR.lastChild;
		                while (hasR_lastChild && hasR_lastChild.nodeName.toLowerCase() == "br" && hasR_lastChild.previousSibling && hasR_lastChild.previousSibling.nodeName.toLowerCase() == "br") {
		                    var e = hasR_lastChild;
		                    hasR_lastChild = hasR_lastChild.previousSibling;
		                    hasR.removeChild(e);
		                }
		                range.insertNode(hasR);
		                if (hasR_lastChild) {
		                    range.setEndAfter(hasR_lastChild);
		                    range.setStartAfter(hasR_lastChild);
		                }
		                selection.removeAllRanges();
		                selection.addRange(range);
		            }
			}
		});
	}
})(jQuery);