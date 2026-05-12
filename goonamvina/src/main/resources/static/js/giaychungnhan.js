// Hàm mở lightbox khi nhấn vào hình ảnh
function openLightbox(image) {
    var lightbox = document.getElementById("lightbox");
    var lightboxImg = document.getElementById("lightbox-img");

    // Hiển thị lightbox và gán hình ảnh
    lightbox.style.display = "block";
    lightboxImg.src = image.src; // Đặt đường dẫn hình ảnh cho lightbox
}

// Hàm đóng lightbox khi nhấn vào biểu tượng đóng hoặc ngoài khung ảnh
function closeLightbox() {
    var lightbox = document.getElementById("lightbox");
    lightbox.style.display = "none"; // Ẩn lightbox
}