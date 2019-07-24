/** 文档操作 */

var path = {url : "/api/sdo/doc/save", method: "POST"};
var Doc = {
    init : function (){
    	var that = this;
    	that.renderBreadcrumb();
    },
    renderBreadcrumb : function(){
    	var that = this;
    	var parents = that.fetchParents();
    	var breadcrumb = $(".breadcrumb .root");
    	parents && $(parents).each(function(index, item){
			breadcrumb.after(`<li class="item"><a href="#"> ${item.title}</a></li>`)
		});
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
		commonUtil.http(path, {parent: parent, title:title, content:content}, function(data){
			commonUtil.msg("保存成功");
			$("#btn-doc-save").remove();
		});
	},
	 

}