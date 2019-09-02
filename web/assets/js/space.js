/** 空间 */

var path = {url : "/api/sdo/doc/save", method: "POST"};


var Space = {

    init : function (){
    	var that = this;
    	that.bindEvent();
    	that.doList();
    },
    bindEvent :function(){
    	var that = this;
    	
    	$("#menu-space").bind("click", function(){
    		$(".main-content").addClass("fn-hide");
			$(".space-content").removeClass("fn-hide");
    	});
    	
    	$(".space-toadd").bind("click", function(){
    		$(this).addClass("fn-hide");
    		$(".space-additem").removeClass("fn-hide");
    	});
    	
    	$(".space-cancel").bind("click", function(){
    		$(".space-toadd").removeClass("fn-hide");
    		$(".space-additem").addClass("fn-hide");
    		$(".space-input").val("");
    	});
    	$(".space-save").bind("click", function(){
    		var name = $(".space-input").val();
    		name && 
    		commonUtil.http(Path.doSpace, {name: name}, function(data){
    			commonUtil.msg("保存成功");
    			
    			//还原plus
    			$(".space-toadd").removeClass("fn-hide");
        		$(".space-additem").addClass("fn-hide");
        		$(".space-input").val("");
    			
    			//添加到新
    			var space = $(`<div class="col-lg-3 space-item space-${data.code}" data-code = ${data.code}><div class="card alert active">${name}</div></div>`);
    			space.bind("click", function(){
    				that.doClick(data.code);
    			});
    			$(".space-plus").before(space);
    		});
    		
    	});
    },
    doList : function(){
    	var that = this;
    	commonUtil.http(Path.doSpaceList, {}, function(data){
			data &&  data.results &&
			$(data.results).each(function(index, item){
				//添加到新
    			var space = $(`<div class="col-lg-3 space-item space-${item.code}" data-code = ${item.code}><div class="card alert ">${item.name}</div></div>`);
    			space.bind("click", function(){
    				that.doClick(item.code);
    			});
    			space.prependTo(".space-content .row");
			});
			
			that.doClick();
		});
    },
    doClick : function (code){
    	var that = this;
    	//初始化
		
		if(code){
			$(".main-content").removeClass("fn-hide");
			$(".space-content").addClass("fn-hide");
		}else{
			code = localStorage.getItem('space-code');
			code = code ? code : "10086";//默认
		}
    	$(".space-item").find(".card").removeClass("active");
    	$(".space-"+code).find(".card").addClass("active");
    	localStorage.setItem('space-code', code);
     	Index.load(code);
   
    }

	

}