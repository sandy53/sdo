/** 文档 首页 */

var Index = {

	init : function(){
		var that =this;
		
		var docId = UrlUtil.fetchParamV("doc");
		docId && that.doInfo(docId);
		
		commonUtil.http(Path.docList, {}, function(data){
			var email = localStorage.getItem('email');
			email && $("#user-email").text(email.substring(0, email.indexOf("@")));
			that.renderDocOfMenu(data.results, $("#sidebar-menu"));
		});
		
		that.bindEvent();
	},
	bindEvent : function(){
		var that =this;
		
	},
	//渲染左菜单 
	renderDocOfMenu : function (results, parent){
		var that =this;
		$(results).each(function(index, doc){
			if(doc.leaf){
				$(`<li id="doc-menu-${doc.docId}" data-docid="${doc.docId}" data-title="${doc.title}"><a href="javascirpt:void(0);"><i class="ti-file"></i>${doc.title}</a></li>`)
				.bind("click", function(){
					that.docActive( doc, false);
				}).appendTo(parent);
			}else{
				var li = $(`<li id="doc-menu-${doc.docId}" data-docid="${doc.docId}"  data-title="${doc.title}">
                	<ul class="doc-subs"></ul>
            	</li>`);
				li.appendTo(parent);
				
				$(`<a class="sidebar-sub-toggle"><i class="ti-folder"></i>${doc.title}<span class="sidebar-collapse-icon ti-angle-down"></span></a>`)
            	.bind("click", function(){
            		var loaded = $(this).hasClass("loaded");
            		if(!loaded){
            			 $(this).addClass("loaded");
            		}
            		that.docActive( doc, !loaded);
            	}).prependTo(li);
			}
		});
	},
	//文档标题点击
	docActive :  (doc, loadSubs) =>{
		var that  =this;
		if(ACTIVE_OPT && ACTIVE_OPT != "move"){
			if(!Doc.doCancel()){
				return false;
			}
		}
		if(ACTIVE_OPT == "move"){
			$("#input-doc-parent-title").val(doc.title);
			$("#input-doc-parent").val(doc.docId);
		}else{
			Index.doInfo(doc.docId);
			Index.activeInfo(doc);
			Index.appendToUrl(doc);
		}
		if(loadSubs){
			Index.loadSubs(doc); //加载子目录
		}
	},
	//缓存点击数据，用于新增等其他
	activeInfo : function(doc){
		var that =this;
		var parents = $(`#doc-menu-${doc.docId}`).parents("li");
		var breadcrumb = $(".breadcrumb");
		$(".breadcrumb .item").remove();
		$(parents.toArray().reverse()).each(function(index, parent){
			var item = {id: $(parent).data("docid"), title: $(parent).data("title")}
			$(`<li class="item" data-id=${item.id}><a href="#"> ${item.title}</a></li>`).appendTo(breadcrumb);
		});
		$(`<li class="item" data-id=${doc.id}><a href="#"> ${doc.title}</a></li>`).appendTo(breadcrumb);
		$("#input-doc-parent").val(doc.parent);
		$("#input-doc-id").text(doc.docId);
	},
	//详情
	doInfo : function (docId){
		var that =this;
		commonUtil.http(Path.docInfo, {id: docId}, function(data){
			if(!data){
				console.log(data);
				return false;
			}
			$("#doc-info-title").text(data.title);
			$("#doc-info-time").text(commonUtil.time(data.createTime));
			$("#doc-content").html(data.detail.content);
			ACTIVE_DOC = data;
		});
	},
	//* 加载子文档列表 */
	loadSubs : function (doc){
		var that =this;
		doc && 
		commonUtil.http(Path.docList, {parent: doc.docId}, function(data){
			if(!data){
				console.log(data);
				return false;
			}
			that.renderDocOfMenu(data.results, $(`#doc-menu-${doc.docId} .doc-subs`));
		});
	},
	//添加当前文档到URL中
	appendToUrl : function (doc){
		var that =this;
		doc && 	UrlUtil.initByUrl("doc", doc.docId);
	},

}