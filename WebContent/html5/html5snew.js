/**
 * 页面加载完毕后初始化参数
 */
function init(){
	autoprocess(document.getElementById("pro11"),document.getElementById("pro11").value,100,2);
}
/**
 * 进度改变执行，使数字同步变化
 * @param obj 当前参数值
 */
function changepro(start){
	document.getElementById("prob11").innerHTML=start;
}
/**
 * 由于显示进度条，从30-100
 */
function autoprocess(obj,start,end,per){
	if(start!=null&&end!=null&&per!=null&obj!=null){
		if((start>=end)||((start+per)>=end)){
			start=end;
			obj.value=start;
			changepro(start);
			return;
		}else{
			start+=per;
			obj.value=start;
			changepro(start);
			//处理自动增长参数问题
			setTimeout(_autoprocess(obj,start,end,per), 2000);
		}
	}
	return;
}

/**
 * 返回settimeout无参函数
 */
function _autoprocess(obj,start,end,per){
	return function(){
		autoprocess(obj, start, end, per);
	};
}