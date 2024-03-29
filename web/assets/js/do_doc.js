/** 文档操作 */

var path = {url : "/api/sdo/doc/save", method: "POST"};


var Doc = {
	input : {
		parent : $("#input-doc-parent"),
		id : $("#input-doc-id"),
		title : $("#input-doc-title"),
		content : $("#input-doc-content"),
	},
	data :{
		parent : null,
	},
    init : function (){
    	var that = this;
    	that.bindEvent();
    	
    },
    bindEvent :function(){
    	var that = this;
    	$("#doc-to-add").bind("click", function(){
    		var spaceCode = localStorage.getItem('space-code');
		    if(!spaceCode){
		    	commonUtil.msg("未正确选择空间目录", true);
				return false;
		    }
    		$(".main-content").removeClass("fn-hide");
			$(".space-content").addClass("fn-hide");
    		$(".doc-update-item").show();
    		$(".doc-info-item").hide();
    		
    		ACTIVE_OPT = "new";
    		
		});
    	
    	$(".doc-info-opt").bind("click", function(){
			var code = $(this).data("code");
			code && that.infoOpt(code);
		});
    	$("#btn-doc-cancel").bind("click", ()=>{
    		that.doCancel();
    	});
    	$(".doc-unlock").bind("click", ()=>{
    		that.doLock('locked');
    	});
    	$(".doc-locked").bind("click", ()=>{
    		that.doLock('unlock');
    	});
    	
    	$(".j-do-move-clear").bind("click", function(){
			$("#doc-move-parent-title").val("");
    		$("#doc-move-parent-code").val("");
		});
    	
    	$(".j-do-move-save").bind("click", function(){
    		Doc.data.parent = $("#doc-move-parent-code").val();
    		that.doSave();
		});
    	
    },
    //对详情的操作
    infoOpt  : function(code){
    	var that = this;
    	ACTIVE_OPT = code;
    	switch (code) {
			case "new":
				that.clear();
				break;
			case "edit":
				that.initEdit();
				break;
			case "copy":
				that.initCopy();
				break;
			case "move":
				that.initMove();
				return;
			default:
				return false;
		}
    	//切换页面
    	that.optRender(true);
    },
    //编辑初始化
    initEdit :() => {
		var that = this;
		var doc = ACTIVE_DOC;
		if(!doc){
			return false;
		}
		Doc.input.parent.empty();
		Doc.input.id.val(doc.docId);
		Doc.input.title.val(doc.title);
		editor.setContent(doc.detail.content);
		$(".doc-move").hide();
	},
	  //复制初始化
	initCopy :() => {
		var that = this;
		var doc = ACTIVE_DOC;
		if(!doc){
			return false;
		}
		Doc.input.id.empty();
		Doc.input.title.val(doc.title + "      复制");
		editor.setContent(doc.detail.content);
		$(".doc-move").hide();
	},
	  //移动初始化
	initMove :() => {
		var that = this;
		var doc = ACTIVE_DOC;
		if(!doc){
			return false;
		}
		Doc.input.id.val(doc.docId);
		Doc.input.title.val(doc.title);
		$(".doc-move").show();
	},
    
    
    //清空编辑器
    clear : function (){
    	var that = this;
    	//不会清除上级文档信息
    	$(".input-doc-item").empty();
    	Doc.input.title.val("");
    	editor.setContent("");
    	//editor.txt.html("");
    },
    //元素切换
    optRender : function (showEidt){
    	var that = this;
    	if(showEidt){
    		$(".doc-info-item").hide();
    		$(".doc-update-item").show();
    	}else{
    		$(".doc-info-item").show();
    		$(".doc-update-item").hide();
    	}
    },
	//保存
	doSave : function(title, content){
		var that = this;
		var parent = Doc.input.parent.val();
		var param =  {operation:ACTIVE_OPT,  title:title, content:content, parent : Doc.input.parent.val(), docId: Doc.input.id.val()};
		if( ACTIVE_OPT == 'new'){
			param.parent = param.docId ;  //新增的时候当前活动文档就是新文档的父文档
			var spaceCode = localStorage.getItem('space-code');
		    if(!spaceCode){
		    	commonUtil.msg("未正确选择空间目录", true);
				return false;
		    }
		    param.spaceCode = spaceCode;
			param.docId = null;  
		}else if(ACTIVE_OPT == 'edit'){
			param.parent = null;
		}else if(ACTIVE_OPT == 'copy'){
			param.docId = null; 
		}else if(ACTIVE_OPT == 'move'){
			param.title = null; 
			param.content = null; 
			param.parent = Doc.data.parent;
			param.updateType = "MOVE";
			if(param.parent == param.docId){
				commonUtil.msg("移动位置错误，重新选择!", true);
				return false;
			}
		}
		console.log(param)
		console.log(ACTIVE_OPT)
		//return;
		commonUtil.http(path, param, function(data){
			commonUtil.msg("保存成功");
			setTimeout(() => {
				location.reload();
			}, 500);
		});
	},
	//取消提醒
	doCancel : () =>{
		var that = this;
		if(confirm("正在编辑， 取消后不可恢复?")){
			Doc.clear();
			Doc.optRender(false);
			ACTIVE_OPT = null;
			return true;
		}else{
			return false;
		}
	},
	//锁定
	doLock : (code) =>{
		var that = this;
		console.log(code)
		ACTIVE_DOC && 
		commonUtil.http(Path.doLock, {docId: ACTIVE_DOC.docId}, function(data){
			$(`.doc-lock`).removeClass("doc-lock-active");
			$(`.doc-${code}`).addClass("doc-lock-active");
		});
	},
	

}