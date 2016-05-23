/*
 * 根据传入的参数使用worker计算之间的质数
 */
onmessage=function(event){
	//提取数据
	var data=JSON.parse(event.data);
	//取出start
	var start=data.start;
	//取出end
	var end=data.end;
	var results="";
	search:
		for (var m = start; m <=end; m++) {
			for (var i = 2; i <=Math.sqrt(m) ; i++) {
				//如果除以i的余数为0，则判断下一个
				if(m%i==0){
					continue search;
				}
			}
			//收集找到的质数
			results+=(m+",");
		}
	//发现全部质数,进行数据传输
	postMessage(results);
}
	