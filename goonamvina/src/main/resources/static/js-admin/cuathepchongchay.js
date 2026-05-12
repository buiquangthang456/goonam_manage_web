document.addEventListener('DOMContentLoaded', function() {
    var categorySelect = document.getElementById('category');
    var finishTypeGroup = document.getElementById('finishTypeViGroup');

    // Hàm để kiểm tra và hiển thị/ẩn Finish Type
    function checkCategory() {
        var selectedCategory = categorySelect.options[categorySelect.selectedIndex].text;
        if (selectedCategory === 'Cửa thép/Cửa thép chống cháy') {
            finishTypeGroup.style.display = 'block';
        } else {
            finishTypeGroup.style.display = 'none';
        }
    }

    // Kiểm tra lần đầu khi tải trang
    checkCategory();

    // Kiểm tra lại mỗi khi thay đổi danh mục
    categorySelect.addEventListener('change', checkCategory);
});
document.addEventListener('DOMContentLoaded', function() {
    var categorySelect = document.getElementById('category');
    var finishTypeGroup = document.getElementById('finishTypeEnGroup');

    // Hàm để kiểm tra và hiển thị/ẩn Finish Type
    function checkCategory() {
        var selectedCategory = categorySelect.options[categorySelect.selectedIndex].text;
        if (selectedCategory === 'Cửa thép/Cửa thép chống cháy') {
            finishTypeGroup.style.display = 'block';
        } else {
            finishTypeGroup.style.display = 'none';
        }
    }

    // Kiểm tra lần đầu khi tải trang
    checkCategory();

    // Kiểm tra lại mỗi khi thay đổi danh mục
    categorySelect.addEventListener('change', checkCategory);
});