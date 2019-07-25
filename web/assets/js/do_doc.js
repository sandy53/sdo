/** 文档操作 */

var path = {url : "/api/sdo/doc/save", method: "POST"};
var Doc = {
    init : function (){
    	var that = this;
    	that.bindEvent();
    	var docId = UrlUtil.fetchParamV("doc");
		docId && that.doInfo(docId);
		var parents = that.fetchParents();
		parents && that.renderBreadcrumb(parents);
    },
    bindEvent :function(){
    	var that = this;
    	$("#toIndex").bind("click", function(){
    		localStorage.setItem('active-info', null);
    		location.href="/index.html";
    	});
    },
    renderBreadcrumb : function(parents, currentId){
    	var that = this;
    	var breadcrumb = $(".breadcrumb");
    	breadcrumb.find(".item").remove();
    	var newParents = [];
    	parents && $(parents).each(function(index, item){
    		newParents.push(item);
    		$(`<li class="item" data-id="${item.id}"><a href="javascript:void(0);"> ${item.title}</a></li>`)
    		.bind("click", function(){
    			var id = $(this).data("id");
    			var tempParents = that.fetchParents();
    			tempParents && that.renderBreadcrumb(tempParents, id);
    		}).appendTo(breadcrumb);
    		if(currentId == item.id){
    			return false;
    		}
		});
    	if(newParents.length >0){
    		$("#input-doc-parent").val(newParents[newParents.length-1].id)
    	}
    	localStorage.setItem('active-info', JSON.stringify(newParents));
    },
    fetchParents : function (){
    	var that = this;
    	var json = localStorage.getItem('active-info');
    	if(!json){
    		return;
    	}
    	return JSON.parse(json);
    },
	//保存
	doSave : function(title, content){
		var that = this;
		var parents = that.fetchParents();
		console.log(parents)
		var parent = '';
		if(parents && parents.length > 0){
			parent = parents[0].id;
		}
		var docId = $("#input-doc-id").val();
		commonUtil.http(path, {parent: parent, title:title, content:content, docId: docId}, function(data){
			commonUtil.msg("保存成功");
			$("#btn-doc-save").remove();
		});
	},
	//详情
	doInfo : function (docId){
		var that =this;
		commonUtil.http(Path.docInfo, {id: docId}, function(data){
			if(!data){
				console.log(data);
				return false;
			}
			$("#input-doc-title").val(data.title);
			$("#input-doc-id").val(data.docId);
			$("#btn-doc-save").text("修改");
			editor.txt.html(data.detail.content);
			
			
		});
	},

}