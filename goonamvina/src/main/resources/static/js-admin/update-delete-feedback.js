function restoreFeedback(feedbackId) {
    if (confirm('Bạn có chắc muốn khôi phục phản hồi này?')) {
        fetch(`/admin/trash/restore/${feedbackId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                window.location.reload();
            } else {
                alert('Có lỗi xảy ra, vui lòng thử lại sau.');
            }
        });
    }
}

    function deleteForever(feedbackId) {
        if (confirm('Bạn có chắc chắn muốn xóa vĩnh viễn phản hồi này?')) {
            fetch(`/admin/trash/delete/${feedbackId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    window.location.reload();  // Làm mới lại trang sau khi xóa thành công
                } else {
                    alert('Có lỗi xảy ra, vui lòng thử lại sau.');
                }
            })
            .catch(error => console.error('Error:', error));
        }
    }
