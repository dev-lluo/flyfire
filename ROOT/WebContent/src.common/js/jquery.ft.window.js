;(function($){
	if(!$.fn.ft.windowCtrl){
		$.fn.ft.windowCtrl = {
				AZIndex : 100,
				UAZIndex :50,
				activedWin : undefined,
				activeWin : function(source){
					if(!$.fn.ft.windowCtrl.activedWin){
						$(source).css({zIndex:$.fn.ft.windowCtrl.AZIndex});
					}else{
						$($.fn.ft.windowCtrl.activedWin).css({zIndex:$.fn.ft.windowCtrl.UAZIndex});
						$(source).css({zIndex:$.fn.ft.windowCtrl.AZIndex});
					}
					$.fn.ft.windowCtrl.activedWin = source;
				}
		};
	}
	//模式化
	if(!$.windowModel){
		$.windowModel = function(options){
			if(options){
				if($('body').find('.ft-window-model').length==0){
					var $windowModel  = $('<div></div>');
					var modelCss = $.extend({},$.fn.ft.windowModel.defaults.css,{width:$.fn.ft.baseSize.x+'px',height:$.fn.ft.baseSize.y+'px'});
					$windowModel.css(modelCss);
					$windowModel.appendTo($('body'));
				}
			}else{
				if($('body').find('.ft-window-model').length>0){
					$('body').find('.ft-window-model').remove();
				}
			}
		}
		$.fn.ft.windowModel = $.windowModel;
		$.fn.ft.windowModel.defaults = {
				css : {backgroundColor:'rgba(0,0,0,0.5)',position:'fixed',left:'0px',top:'0px'}
		};
	}
	
	if(!$.fn.ftWindow){
		$.fn.ftWindow = function(options){
			options = options?options:{};
			methods = $.extend({},$.fn.ftWindow.methods);
			if(methods[options]){
				return methods[options].apply(this,Array.prototype.slice.call(arguments,1));
			}else if(!options||typeof options === 'object'){
				//合并css
				if(options.ctrlBar)options.ctrlBar = $.extend({},$.fn.ftWindow.defaults.ctrlBar,options.ctrlBar);
				if(options.titleCss)options.titleCss = $.extend({},$.fn.ftWindow.defaults.titleCss,options.titleCss);
				if(options.contentCss)options.contentCss = $.extend({},$.fn.ftWindow.defaults.contentCss,options.contentCss);
				if(options.css)options.css = $.extend({},$.fn.ftWindow.defaults.css,options.css);
				//合并options
				options = $.extend({},$.fn.ftWindow.defaults,options);
				return methods.init.call(this,options);
			}else{
				throw 'some err...';
			}
		};
		$.fn.ftWindow.defaults = $.extend({},$.fn.ft.defaults,{
			title : 'FT窗体',
			icon : '&#xf01bb;',
			ctrlBar : {enable:true,css:{float:'right',cursor:'default'},bars:[
			             {code:'min',icon:'&#xf01b1;',eventNeedToggle:true,event:[function(source){
			            	 var $source = $(source);
			            	 var $content = $source.find('.ft-window-content');
			            	 $content.css({height:'0px',overflow:'hidden'});
			            	 $source.css({height:($source.height()-$content.get(0).htemp)+'px',overflow:'hidden'});
			            	 $(source).moveWindowTo('bottomLeft');
			             },function(source){
			            	 var $source = $(source);
			            	 var $content = $source.find('.ft-window-content');
			            	 $content.css({height:$content.get(0).htemp+'px',overflow:'hidden'});
			            	 $source.css({height:($source.height()+$content.get(0).htemp)+'px',overflow:'hidden'});
			            	 $(source).moveWindowTo();
			             }]},
			             {code:'cls',icon:'&#xf00b3;',eventNeedToggle:false,event:function(source){
			            	 var $source = $(source);
			            	 $source.get(0).stinit = undefined;
			            	 $source.html('');
			            	 $source.attr('style','');
			             }}
			          ]},
			titleCss : {borderTopLeftRadius:'5px',borderTopRightRadius:'5px',width:'96%',paddingLeft:'2%',paddingRight:'2%',height:'35px',paddingTop:'3px',paddingBottom:'2px',color:'black',backgroundColor:'gray',cursor:'move',fontSize:'20px',lineHeight:'35px'},
			contentCss : {borderBottomLeftRadius:'5px',borderBottomRightRadius:'5px',width:'96%',paddingLeft:'2%',paddingRight:'2%',paddingTop:'3px',paddingBottom:'2px',color:'black',backgroundColor:'rgba(255, 251, 240,0.6)',cursor:'default',fontSize:'20px',lineHeight:'35px'},
			css : {border:'1px solid gray',overflow:'hidden',borderRadius:'5px',boxShadow:'1px 1px 3px #292929',position:'absolute',width:'350px',height:'200px'},
			movenable : true,
			withModel : false,
			dataMode : 'local',  //local or server
			url : '',  
			content:'无内容'
		});
		$.fn.ftWindow.methods = $.extend({},$.fn.ft.methods,{
			init : function(options){
				/*
				遍历选择元素；给已经初始化的dom标记；当再次遇到改元素时跳过
				*/
				for(var i = 0;i<this.length;i++){
					var dom = $(this).get(i);
					if(dom.stinit)break;
					$(dom).css(options.css);
					$(dom).addClass('ft-window');
					//初始化
					$(dom).appendTo($('body'));
					//窗体居中计算
					$(dom).moveWindowTo();
					$(dom).mousedown(function(){
						$.fn.ft.windowCtrl.activeWin(dom);
					});
					//增加标题位置
					var $title = $('<div class="ft-window-title"></div>');
					$title.css(options.titleCss);
					$title.appendTo($(dom));
					//拖动事件
					$title.mousedown(function(e){
						var titleDom = $title.get(0);
						titleDom.mouseP = {x:e.pageX,y:e.pageY};
						titleDom.windowP = $(dom).offset();
					});
					//如果启用拖动
					if(options.movenable){
						$title.mouseup(function(e){
							var titleDom = $title.get(0);
							titleDom.mouseP = undefined;
							titleDom.windowP = undefined;
							
						});
						$title.mouseout(function(e){
							var titleDom = $title.get(0);
							titleDom.mouseP = undefined;
							titleDom.windowP = undefined;
						});
						$title.mousemove(function(e){
							var titleDom = $title.get(0);
							if(titleDom.mouseP&&titleDom.windowP){
								var _left = titleDom.windowP.left+(e.pageX-titleDom.mouseP.x);
								_left = _left>=0?_left:0;
								var _top = titleDom.windowP.top+(e.pageY-titleDom.mouseP.y);
								_top = _top>=0?_top:0;
								$(dom).css({left:_left+'px',top:_top+'px'});
							}
						});
					}
					
					//图标设置
					var $icon = $('<span class="iconfont"></span>');
					$icon.html(options.icon);
					$icon.appendTo($title);
					//窗口名设置
					var $name = $('<span></span>');
					$name.html(options.title);
					$name.appendTo($title);
					
					//控制栏
					if(options.ctrlBar&&options.ctrlBar.enable){
						var $ctrlBar = $('<div></div>');
						$ctrlBar.css(options.ctrlBar.css);
						$ctrlBar.appendTo($title);
						for(var i = 0;i<options.ctrlBar.bars.length;i++){
							var bar = options.ctrlBar.bars[i];
							var $cbar = $('<span></span>');
							$cbar.addClass(bar.code);
							$cbar.addClass('iconfont');
							$cbar.html("&nbsp;"+bar.icon+"&nbsp;");
							$cbar.get(0).etemp = bar.event;
							if(bar.eventNeedToggle){
								$cbar.get(0).dtemp = 0;
							}
							$cbar.click(function(){
								if(isNaN(this.dtemp)){
									this.etemp(dom);
								}else{
									if(this.dtemp===this.etemp.length)this.dtemp = 0;
									var exec = this.etemp[this.dtemp];
									window.a = this.etemp;
									exec(dom);
									this.dtemp++;
								}
							});
							$cbar.appendTo($ctrlBar);
						}
					}
					
					//增加内容位置
					var $content = $('<div class="ft-window-content"></div>');
					$content.css(options.contentCss);
					//内容高度计算
					var realHeight = $(dom).height();
					var titleHeight = $title.outerHeight(true);
					var contentHeight = realHeight-titleHeight;
					var bordH = $content.bordHeight();  
					var paddH = $content.paddHeight();
					var margH = $content.margHeight(); 
					contentHeight = contentHeight -bordH - paddH - margH;
					$content.css({height:contentHeight+'px'});
					$content.get(0).htemp = contentHeight;
					//填充内容
					if(options.dataMode==='server'){
						$content.load(options.url);
					}else{
						$content.html(options.content);
					}
					
					$content.appendTo($(dom));
					
					//初始化
					dom.stinit = true;
				}	
				return this;
			},
			movenable : function(options){
				if(options){
					var $title = $(this).find('.ft-window-title');
					$title.mouseup(function(e){
						var titleDom = $title.get(0);
						titleDom.mouseP = undefined;
						titleDom.windowP = undefined;
						
					});
					$title.mouseout(function(e){
						var titleDom = $title.get(0);
						titleDom.mouseP = undefined;
						titleDom.windowP = undefined;
					});
					$title.mousemove(function(e){
						var titleDom = $title.get(0);
						if(titleDom.mouseP&&titleDom.windowP){
							var _left = titleDom.windowP.left+(e.pageX-titleDom.mouseP.x);
							_left = _left>=0?_left:0;
							var _top = titleDom.windowP.top+(e.pageY-titleDom.mouseP.y);
							_top = _top>=0?_top:0;
							$(dom).css({left:_left+'px',top:_top+'px'});
						}
					});
				}else{
					var $title = $(this).find('.ft-window-title');
					$title.die('mouseup');
					$title.die('mouseout');
					$title.die('mousemove');
				}
			},
			stl : function(css){
				if(css){
					$(this).css(css);
					return this;
				}else{
					return $(this).attr('style');
				}
			}
		});
	}
	
	
})(jQuery);