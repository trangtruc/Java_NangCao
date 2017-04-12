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
			<div class="row mt20">
				<video id="video" width="640" height="480" autoplay></video>
				<canvas id="canvas" width="640" height="480"></canvas>
			</div>
			<div class="row ml40">
				<div class="col-md-2"></div>
				<div class="col-md-4">
					<button id="snap" class="btn btn-success">Chụp ảnh</button>
				</div>
				<div class="col-md-2"></div>
				<div class="col-md-4" id="btn-save">
					
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
				$("#btn-save").html("<button id=save  class='btn btn-success'>Lưu</button>");
			});
		}, false);
		
		document.getElementById('btn-save').addEventListener('click', function() {
			var canvas=document.getElementById("canvas");
			var dataUrl=canvas.toDataURL("image/png");
			$.ajax({
				  type: "POST",
				  url: "http://localhost/website/CIF.php",
				  data: {image: dataUrl}
				})
				.done(function(respond){console.log("done: "+respond);})
				.fail(function(respond){console.log("fail");})
				.always(function(respond){console.log("always");})
		});
	</script>
</body></html>