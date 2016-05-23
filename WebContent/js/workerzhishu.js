/*
 * 使用worker计算1-99999之间的质数
 */
	var n=0;
	search:
		while(n<99999){
			n+=1;
			for (var i = 2; i <=Math.sqrt(n) ; i++) {
				//如果除以i的余数为0，则判断下一个
				if(n%i==0){
					continue search;
				}
			}
			//发现质数,进行数据传输
			postMessage(n);
	}