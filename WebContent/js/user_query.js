	function showTime(){
		
		var regtime = document.getElementById("regtime");
		var timerange = document.getElementById("timerange");
		
		if(regtime.value=="全部时间"){
			timerange.style.visibility = "hidden";
		}else{
			timerange.style.visibility = "visible";
		}
		
	}
	
	//全选
	function selectAll(){
		
		var chkAll = document.getElementById("chkAll");
		
		var userids = document.getElementsByName("userids");
		
		for (var i = 0; i < userids.length; i++) {
			
			userids[i].checked = chkAll.checked;
		}
	}
	
	//反选
	function selectReverse(){
		
		var userids = document.getElementsByName("userids");
		
		for (var i = 0; i < userids.length; i++) {
			
			userids[i].checked = !userids[i].checked;
		}
	}
	
	//检查全选状态
	function checkAll(){
		
		var chkAll = document.getElementById("chkAll");
		
		var userids = document.getElementsByName("userids");
		
		var count = 0;
		
		for (var i = 0; i < userids.length; i++) {
			
			if(userids[i].checked){
				count++;
			} 
		}
		
		chkAll.checked = (count==userids.length);
		
	}

	//检查选中的用户的数量
	function checkData(){
		
		var userids = document.getElementsByName("userids");
		
		var count = 0;
		
		var usernames = "";
		
		for (var i = 0; i < userids.length; i++) {
			
			if(userids[i].checked){
				count++;
				//console.log(userids[i].value + ",");
				//alert(userids[i].parentNode.parentNode.children[2].innerText);
				usernames += userids[i].parentNode.parentNode.children[2].innerText + ",";
				
			} 
		}
		
		if(usernames!=""){
			usernames = usernames.substring(0, usernames.length - 1);
		}
		
		if(count==0){
			alert("至少要选择一个用户");
			return false;
		}
		
		if(!confirm("是否确认删除用户【" + usernames + "】")){
			return false;
		}
		
		return true;
		
	}
