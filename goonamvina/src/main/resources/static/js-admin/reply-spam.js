function moveToTrash(feedbackId) {
    if (confirm('Bạn có chắc muốn đánh dấu là Spam và di chuyển vào thùng rác?')) {
        fetch('/admin/reply/moveToTrash/' + feedbackId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                window.location.reload();  // Làm mới lại trang sau khi thành công
            } else {
                alert('Có lỗi xảy ra, vui lòng thử lại sau.');
            }
        })
        .catch(error => console.error('Error:', error));
    }
}