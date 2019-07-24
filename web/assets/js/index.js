/** 文档 首页 */

var Index = {

	init : function(){
		var that =this;
		commonUtil.http(Path.docList, {}, function(data){
			console.log(data);
			that.renderDocOfMenu(data.results, $("#sidebar-menu"));
		});
	},
	
	renderDocOfMenu : function (results, parent){
		var that =this;
		$(results).each(function(index, doc){
			if(doc.leaf){
				$(`<li id="doc-menu-${doc.docId}" data-docid="${doc.docId}" data-title="${doc.title}"><a href="javascirpt:void(0);"><i class="ti-file"></i>${doc.title}</a></li>`)
				.bind("click", function(){
					that.doInfo(doc);
					that.activeInfo(doc);
				}).appendTo(parent);
			}else{
				var li = $(`<li id="doc-menu-${doc.docId}" data-docid="${doc.docId}"  data-title="${doc.title}">
                	<ul class="doc-subs"></ul>
            	</li>`);
				li.appendTo(parent);
				
				$(`<a class="sidebar-sub-toggle"><i class="ti-folder"></i>${doc.title}<span class="sidebar-collapse-icon ti-angle-down"></span></a>`)
            	.bind("click", function(){
            		that.doInfo(doc);
            		that.activeInfo(doc);
            		if(!$(this).hasClass("loaded")){
            			$(this).addClass("loaded")
                		that.loadSubs(doc); //加载子目录
            		}
            	}).prependTo(li);
			}
		});
	},
	//缓存点击数据，用于新增等其他
	activeInfo : function(doc){
		var that =this;
		var layer = [];
		layer.push({id: doc.docId, title: doc.title});
		var parents = $(`#doc-menu-${doc.docId}`).parents("li");
		var breadcrumb = $(".breadcrumb .root");
		$(".breadcrumb .item").remove();
		breadcrumb.after(`<li class="item"><a href="#">${doc.title}</a></li>`)
		parent && $(parents).each(function(index, parent){
			var item = {id: $(parent).data("docid"), title: $(parent).data("title")}
			layer.push(item);
			breadcrumb.after(`<li class="item"><a href="#"> ${item.title}</a></li>`)
		});
		//新增时用
		 localStorage.setItem('active-info', JSON.stringify(layer));
		/*var data1 = JSON.parse(localStorage.getItem('active-info'));*/
	},
	//详情
	doInfo : function (doc){
		var that =this;
		$("#doc-info-title").text(doc.title);
		$("#doc-info-time").text(commonUtil.time(doc.createTime));
		commonUtil.http(Path.docInfo, {id: doc.docId}, function(data){
			if(!data){
				console.log(data);
				return false;
			}
			$("#doc-content").html(data.content);
		});
	},
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

}