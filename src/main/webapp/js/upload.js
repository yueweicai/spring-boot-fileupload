$(function(){
	var inputs = new Array(); //声明文件域数组
	
	//为所有图片添加点击事件
	$(".thumbnail>img").click(function(){
		var me = this;
		$input = $("<input type='file'>");//动态构建文件域元素
		$input.click();//调用文件域点击事件，打开文件选择窗口
		$input.change(function(){ //监听文件域选择文件事件
			 var file  = $input[0].files[0]; //获取文件域中选定文件
			 var reader = new FileReader(); //定义文件读取器
			 reader.onloadend = function () { //文件读取器加载文件事件完成后
				 me.src = reader.result; //将读取到的图片文件赋给被点击的图片框
				 $(me).css({width:'250px',height:'167px'});//设置图片缩略图尺寸
				 inputs.push($input[0]); //将文件域放入数组中
			 }
			 if (file) {
				 reader.readAsDataURL(file);
			 } else {
			 	 me.src = "";
			 }
		})
	})
	//上传按钮点击事件，完成图片上传操作
	$(".btn").click(function(){
			if(inputs.length>0){
				var data = new FormData();
				$.each(inputs,function(i,e){
					data.append("file[]",e.files[0]);
				})
				$.ajax({  
		            url:'uploads',           //服务器控制器url
		            type:'POST',            //请求方法
		            data:data,               //请求数据 
		            async: false,           //不使用异步模式
		            cache: false,           //不使用缓存
		            contentType: false, //不设置内容类型  
		            processData: false, //不处理数据格式  
		            success:function(res){  
		                console.log(res);  
		            },  
		            error:function(res){  
		            	console.log(res);  
		            }
				})
			}  
	})
})