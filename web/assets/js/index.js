/** 文档 首页 */

var Index = {

	init : function(){
		var that =this;
		var email = localStorage.getItem('email');
		email && $("#user-email").text(email.substring(0, email.indexOf("@")));

		var docId = UrlUtil.fetchParamV("doc");
		docId && that.doInfo(docId);
		
		
		that.bindEvent();
	},
	bindEvent : function(){
		var that =this;
		
	},
	
	load : function (spaceCode){
		var that =this;
		commonUtil.http(Path.docList, {spaceCode: spaceCode}, function(data){
			$("#sidebar-menu").find(".doc-menu-item").remove();
			that.renderDocOfMenu(data.results, $("#sidebar-menu"));
		});
	},
	
	//渲染左菜单 
	renderDocOfMenu : function (results, parent){
		var that =this;
		$(results).each(function(index, doc){
			if(doc.leaf){
				$(`<li id="doc-menu-item doc-menu-${doc.docId}" class="doc-menu-item" data-docid="${doc.docId}" data-title="${doc.title}">
						<a href="javascirpt:void(0);">${doc.title}</a></li>`)
				.bind("click", function(){
					that.docActive( doc, false);
				}).appendTo(parent);
			}else{
				var li = $(`<li id="doc-menu-${doc.docId}" class="doc-menu-item" data-docid="${doc.docId}"  data-title="${doc.title}">
                	<ul class="doc-subs"></ul>
            	</li>`);
				li.appendTo(parent); //<i class="ti-folder"></i>
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
			$("#doc-move-parent-title").val(doc.title);
    		$("#doc-move-parent-code").val(doc.docId);
		}else{
			Index.doInfo(doc.docId);
			Index.activeInfo(doc);
			Index.appendToUrl(doc);
			
			if($(".main-content").hasClass("fn-hide")){
				$(".main-content").removeClass("fn-hide");
				$(".space-content").addClass("fn-hide");
			}
			
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
			
			$(".main-content").show();
			$(".space-content").hide();
			
			$("#doc-info-title").text(data.title);
			$("#doc-info-time").text(commonUtil.time(data.createTime));
			$("#doc-content").html(data.detail.content);
			ACTIVE_DOC = data;
			//可操作按钮显示
			var owner =  data.owner;
			var locked =  data.locked;
			if(owner || !locked){
				//显示普通操作
				$(".doc-info-opt").addClass("doc-info-opt-active");
			}else{
				$(".doc-info-opt").removeClass("doc-info-opt-active");
			}
			if(owner){
				$(`.doc-${locked ? 'locked': 'unlock'}`).addClass("doc-lock-active");
			}else{
				$(`.doc-lock`).removeClass("doc-lock-active");
			}
			
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