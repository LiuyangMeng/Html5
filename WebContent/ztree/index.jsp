    <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
    <%  
    String path = request.getContextPath();  
    //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
    request.setAttribute("ctx",request.getContextPath());  
    %>  
      
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
      <head>  
        <title>zTree</title>  
        <meta http-equiv="pragma" content="no-cache">  
        <meta http-equiv="cache-control" content="no-cache">  
        <meta http-equiv="expires" content="0">      
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
        <meta http-equiv="description" content="This is my page">  
        <link rel="stylesheet" href="${ctx }/ztree/zTreeStyle.css" type="text/css">  
        <script type="text/javascript" src="${ctx }/ztree/jquery-1.4.4.min.js"></script>  
        <script type="text/javascript" src="${ctx }/ztree/jquery.ztree.core.min.js"></script> 
          
        <script type="text/javascript">  
            var setting = {  
                async:{  
                    enable:true,  
                    url:"loadData.jsp",  
                    autoParam:["id"],  
                },  
                data:{  
                    simpleData:{  
                        enable:true,  
                        idKey:"id",  
                        pIdKey:"pId",  
                        rootPId:0  
                    }  
                },  
                callback:{  
                    onClick:function(event,treeId,treeNode,clickFlag){  
                        if(!treeNode.isParent){  
                            alert("treeId自动编号：" + treeNode.tId + ",节点id是：" + treeNode.id + ",节点文本是：" + treeNode.name) ;  
                        }  
                    },  
                    onAsyncError:zTreeOnAsyncError,  
                    onAsyncSuccess:function(event,treeId,treeNode,msg){  
      
                    }  
                }  
            };  
            function filter(treeId, parentNode, childNodes) {    
                if (!childNodes)    
                    return null;    
                for ( var i = 0, l = childNodes.length; i < l; i++) {    
                    childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');    
                }    
                return childNodes;    
            }   
            function zTreeOnAsyncError(event,treeId,treeNode,XMLHttpRequest,textStatus,errorThrown){  
                alert("加载错误：" + XMLHttpRequest) ;  
            }  
            $(document).ready(function(){  
                $.fn.zTree.init($("#treeDemo"),setting) ;  
            }) ;  
        </script>  
      </head>  
        
      <body>  
         <div>  
            <ul id="treeDemo" class="zTree"></ul>  
        </div>  
      </body>  
    </html>  