init(50,"mylegend",600,600,main);/* *init(speed, divid, width, height,  completeFunc,  type  )  speed:游戏速度设定  divid:传入一个div的id，库件进行初始化的时候，会自动将canvas加入到此div内部  width:游戏界面宽  height:游戏界面高  completeFunc:游戏初始化后，调用此函数  type：当为null时，会先进行页面的onload操作，如果你的init函数调用是在onload之后，那么需要将此参数设为LEvent.INIT  */

var loadingLayer;
var backLayer;
var stopLayer;
var startLayer;
var loadIndex = 0;
var imglist = {};
var btnup,btndown,btnleft,btnright;
var imgData = new Array();

var mapImgList = new Array();
var mapmoveflag = "";
var MOVE_STEP = 10;

var combination = new Array([1,1,5], [1,2,4], [1,5,1], [2,1,4], [2,3,3], [2,4,1], [2,5,4], [3,1,2], [3,4,3], [3,5,5], [4,1,2], [4,2,3], [4,5,1], [4,5,5], [5,1,1], [5,2,4], [5,3,2], [5,5,1], [1,1,1], [1,1,1]);
var reels = new Array();
var kakes = new Array();

var stopBtn = new Array();
var start;
var win;var shibai;
function main(){
	imgData.push({name:"stop_up",path:"../image/tiger/slot_stop_up.png"});
	imgData.push({name:"stop_over",path:"../image/tiger/slot_stop_over.png"});
	imgData.push({name:"start",path:"../image/tiger/slot_start.jpg"});
	imgData.push({name:"kake",path:"../image/tiger/slot_kake.png"});
	imgData.push({name:"slot_back",path:"../image/tiger/slot_back.jpg"});
	imgData.push({name:"slot_ok",path:"../image/tiger/slot_ok.png"});		imgData.push({name:"slot_lose",path:"../image/tiger/slot_lose.png"});
	imgData.push({name:"item1",path:"../image/tiger/1.png"});
	imgData.push({name:"item2",path:"../image/tiger/2.png"});
	imgData.push({name:"item3",path:"../image/tiger/3.png"});
	imgData.push({name:"item4",path:"../image/tiger/4.png"});
	imgData.push({name:"item5",path:"../image/tiger/5.png"});
	imgData.push({name:"item6",path:"../image/tiger/6.png"});	loadingLayer = new LoadingSample1();	addChild(loadingLayer);		LLoadManage.load(		imgData,		function(progress){			loadingLayer.setProgress(progress);		},		function(result){			imglist = result;			removeChild(loadingLayer);			loadingLayer = null;			gameInit();		}	);
}
function gameInit(event){
	var i,j,bitmap,bitmapdata,childmap;
	
	backLayer = new LSprite();
	addChild(backLayer);

	bitmapdata = new LBitmapData(imglist["slot_back"]);
	bitmap = new LBitmap(bitmapdata);
	backLayer.addChild(bitmap);
	
	stopLayer = new LSprite();
	addChild(stopLayer);
	for(i=0;i<3;i++){
		var reel = new Reel(combination,i);
		reel.x = 150 * i + 90;
		reel.y = 225;
		reels.push(reel);
		addChild(reel);
		var kake = new LBitmap(new LBitmapData(imglist["kake"]));
		kake.x = 150 * i + 90;
		kake.y = 225;
		kakes.push(kake);
		addChild(kake);
		var stop = new LButton(new LBitmap(new LBitmapData(imglist["stop_up"])),new LBitmap(new LBitmapData(imglist["stop_over"])));
		stop.x = 150 * i + 110;
		stop.y = 490;
		stop.index = i;
		stopBtn.push(stop);
		stop.visible = false;
		stop.addEventListener(LMouseEvent.MOUSE_UP, stopevent);
		addChild(stop);
	}

	startLayer = new LSprite();
	addChild(startLayer);
	start = new LButton(new LBitmap(new LBitmapData(imglist["start"])),new LBitmap(new LBitmapData(imglist["start"])));
	start.x = 55;
	start.y = 450;
	startLayer.addChild(start);
	start.addEventListener(LMouseEvent.MOUSE_UP, onmouseup);
	
	
	win = new LButton(new LBitmap(new LBitmapData(imglist["slot_ok"])),new LBitmap(new LBitmapData(imglist["slot_ok"])));
	startLayer.addChild(win);
	win.visible = false;
	win.addEventListener(LMouseEvent.MOUSE_UP, winclick);
			shibai = new LButton(new LBitmap(new LBitmapData(imglist["slot_lose"])),new LBitmap(new LBitmapData(imglist["slot_lose"])));	startLayer.addChild(shibai);	shibai.visible = false;	shibai.addEventListener(LMouseEvent.MOUSE_UP, loseclick);	
	backLayer.addEventListener(LEvent.ENTER_FRAME,onframe);
}
function onframe(){
	var i;
	for(i=0;i<3;i++){
		reels[i].onframe();
	}
}
function stopevent(event,currentTarget){
	reels[currentTarget.index].stopFlag = true;
}
function onmouseup(event){
	var i;
	var stopNum = Math.floor(Math.random()*(combination.length/3));
	start.visible = false;
	for(i=0;i<3;i++){
		stopBtn[i].visible = true;
		reels[i].startReel = true;
		reels[i].stopFlag = false;
		reels[i].stopNum = stopNum;
	}
}
function winclick(){
	win.visible = false;
	start.visible = true;
}function loseclick(){	shibai.visible = false;	start.visible = true;}
function checkWin(){
	var i;
	var allstop = 0;
	for(i=0;i<3;i++){
		if(!reels[i].startReel)allstop++;
	}
	if(allstop >= 3){
		for(i=0;i<3;i++){
			stopBtn[i].visible = false;
		}
		
		if(reels[0].stopNum >= 2){
			win.visible = true;
		}else{
			shibai.visible = true;
		}
	}
}