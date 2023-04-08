function scanQRCode() {
    navigator.mediaDevices.getUserMedia({ video: { facingMode: "environment" } })
        .then(stream => {
            let video = document.createElement('video');
            video.srcObject = stream;
            video.setAttribute('autoplay', '');
            video.setAttribute('muted', '');
            video.setAttribute('playsinline', '');
            video.addEventListener('loadedmetadata', () => {
                let canvas = document.createElement('canvas');
                canvas.width = video.videoWidth;
                canvas.height = video.videoHeight;
                canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
                let base64Image = canvas.toDataURL('image/png');
                $.ajax({
                    url: '/scan',
                    method: 'POST',
                    data: base64Image,
                    contentType: 'application/json',
                    success: function (response) {
                        console.log(response);
                        // handle successful response
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                        // handle error response
                    }
                });
                stream.getTracks().forEach(track => track.stop());
                video.remove();
            });
        })
        .catch(error => {
            console.error(error);
            // handle error
        });
}
