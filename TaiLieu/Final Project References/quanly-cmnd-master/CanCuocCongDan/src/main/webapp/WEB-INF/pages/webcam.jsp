<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<html lang="en"><head>
	<meta charset="UTF-8">
	<title>CHỤP ẢNH TRỰC TUYẾN MIỄN PHÍ</title>
	<jsp:include page="include.jsp"></jsp:include>
</head>
<body>
		<input type=hidden id=key value='${_csrf.parameterName}' />
		<input type=hidden id=vlue value='${_csrf.token}' />
		<div class="col-md-12">	
			<div class="col-md-6">
				<div class="row mt5">
					<video id="video" width="640" height="480" autoplay></video>
				</div>
				<div class="row mt5">
					<div class="col-md-4"></div>
					<div class="col-md-3">
						<button id="snap" class="btn btn-success">Chụp ảnh</button>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row mt5">
					<canvas id="canvas" width="640" height="480"></canvas>
				</div>
				<div class="row mt5">
					<div class="col-md-4"></div>
					<div class="col-md-3">
						<a id='DownLoad' download="image.png"></a>
					</div>
				</div>
			</div>
		</div>
<script>

		// Put event listeners into place
		window.addEventListener("DOMContentLoaded", function() {
			// Grab elements, create settings, etc.
            var canvas = document.getElementById('canvas');
            var context = canvas.getContext('2d');
            var video = document.getElementById('video');
            var mediaConfig =  { video: true };
            var errBack = function(e) {
            	console.log('An error has occurred!', e)
            };

			// Put video listeners into place
            if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                navigator.mediaDevices.getUserMedia(mediaConfig).then(function(stream) {
                    video.src = window.URL.createObjectURL(stream);
                    video.play();
                });
            }

            /* Legacy code below! */
            else if(navigator.getUserMedia) { // Standard
				navigator.getUserMedia(mediaConfig, function(stream) {
					video.src = stream;
					video.play();
				}, errBack);
			} else if(navigator.webkitGetUserMedia) { // WebKit-prefixed
				navigator.webkitGetUserMedia(mediaConfig, function(stream){
					video.src = window.webkitURL.createObjectURL(stream);
					video.play();
				}, errBack);
			} else if(navigator.mozGetUserMedia) { // Mozilla-prefixed
				navigator.mozGetUserMedia(mediaConfig, function(stream){
					video.src = window.URL.createObjectURL(stream);
					video.play();
				}, errBack);
			}

			// Trigger photo take
			document.getElementById('snap').addEventListener('click', function() {
				context.drawImage(video, 0, 0, 640, 480);
				$("#DownLoad").html("<button id='save' class='btn btn-success'>Tải về</button>");
			});
		}, false);
		
		document.getElementById('DownLoad').addEventListener('click', function() {
			var canvas=document.getElementById("canvas");
			document.getElementById("DownLoad").download = "image.png";
		    document.getElementById("DownLoad").href = document.getElementById("canvas").toDataURL("image/png").replace(/^data:image\/[^;]/, 'data:application/octet-stream'); 
		    window.close();
		});
		function send(){
			var canvas = document.getElementById('canvas');
			var canvasData = canvas.toDataURL("image/png");
		    var ajax = new XMLHttpRequest();
		    ajax.open("GET",'test',false);
		    ajax.onreadystatechange = function() {
		        console.log(ajax.responseText);
		    }
		    ajax.setRequestHeader('Content-Type', 'application/upload');
		    ajax.send("_csrf="+$("#vlue").val()+"&file="+canvasData);
		    
		}
	</script>
</body></html>