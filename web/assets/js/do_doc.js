/** 文档操作 */

var path = {url : "/api/sdo/doc/save", method: "POST"};


var Doc = {
	input : {
		parent : $("#input-doc-parent"),
		id : $("#input-doc-id"),
		title : $("#input-doc-title"),
		content : $("#input-doc-content"),
	},
    init : function (){
    	var that = this;
    	that.bindEvent();
    	
    },
    bindEvent :function(){
    	var that = this;
    	$(".doc-info-opt").bind("click", function(){
			var code = $(this).data("code");
			code && that.infoOpt(code);
		});
    	$("#btn-doc-cancel").bind("click", ()=>{
    		that.doCancel();
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
				break;
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
		editor.txt.html(doc.detail.content);
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
		editor.txt.html(doc.detail.content);
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
		editor.txt.html(doc.detail.content);
		$(".doc-move").show();
	},
    
    
    //清空编辑器
    clear : function (){
    	var that = this;
    	//不会清除上级文档信息
    	$(".input-doc-item").empty();
    	Doc.input.title.val("");
    	editor.txt.html("");
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
		var param =  {title:title, content:content, parent : Doc.input.parent.val(), docId: Doc.input.id.val()};
		if( ACTIVE_OPT == 'new'){
			param.parent = param.docId ;  //新增的时候当前活动文档就是新文档的父文档
			param.docId = null;  
		}else if(ACTIVE_OPT == 'edit'){
			param.parent = null;
		}else if(ACTIVE_OPT == 'copy'){
			param.docId = null; 
		}else if(ACTIVE_OPT == 'move'){
			param.title = null; 
			param.content = null; 
		}
		console.log(param)
		//return;
		commonUtil.http(path, param, function(data){
			commonUtil.msg("保存成功");
			Doc.optRender(false);
			if(ACTIVE_OPT == 'move'){
				$("#doc-info-title").text(title);
				$("#doc-content").html(content);
			}else{
				$("#doc-info-title").text(data.title);
				$("#doc-info-time").text(commonUtil.time(data.createTime));
				$("#doc-content").html(data.content);
			}
			ACTIVE_OPT = null;
			Doc.clear();
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

}