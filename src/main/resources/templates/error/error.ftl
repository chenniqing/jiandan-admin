<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>/(ㄒoㄒ)/~~ - ${system_name}</title>
<style>
*{padding:0;margin:0}
a{text-decoration:none}
.notfoud-container .img-404{height:155px;background:url(/assets/images/error/404.png) center center no-repeat;-webkit-background-size:150px auto;margin-top:40px;margin-bottom:20px}
.notfoud-container .notfound-p{line-height:22px;font-size:17px;padding-bottom:15px;border-bottom:1px solid #f6f6f6;text-align:center;color:#262b31}
.notfoud-container .notfound-reason{color:#9ca4ac;font-size:13px;line-height:13px;text-align:left;width:210px;margin:0 auto}
.notfoud-container .notfound-reason p{margin-top:13px}
.notfoud-container .notfound-reason ul li{margin-top:10px;margin-left:36px}
.notfoud-container .notfound-btn-container{margin:40px auto 0;text-align:center}
.notfoud-container .notfound-btn-container .notfound-btn{display:inline-block;border:1px solid #ebedef;background-color:#239bf0;color:#fff;font-size:15px;border-radius:5px;text-align:center;padding:10px;line-height:16px;white-space:nowrap}
</style>
</head>

<body>
<div class="notfoud-container">
		<div class="img-404">
		</div>
		<p class="notfound-p">哎呀迷路了...</p>
		<div class="notfound-reason">
			<p>可能的原因：</p>
			<ul>
				<li>${message}</li>
			</ul>
		</div>
		<div class="notfound-btn-container">
			<a class="notfound-btn" href="javascript:;" onclick="goback()">返回</a>
		</div>
	</div>
</body>
<script>
	/**
	 * 返回
	 */
	function goback() {
		window.location.href = document.referrer;
	}
</script>
</html>
