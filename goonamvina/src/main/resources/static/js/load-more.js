document.addEventListener('DOMContentLoaded', function() {
    function setupLoadMore(buttonId, categoryId) {
        var loadMoreButton = document.getElementById(buttonId);
        var productsList = document.getElementById(categoryId);

        if (!loadMoreButton || !productsList) {
            console.error('Không tìm thấy phần tử với ID:', categoryId);
            return;
        }

        var products = productsList.getElementsByClassName('product-item');
        var itemsToShow = 4; // Số lượng sản phẩm muốn hiển thị ban đầu
        var itemsPerClick = 4; // Số lượng sản phẩm muốn hiển thị mỗi lần nhấn nút

        // Ẩn tất cả sản phẩm sau sản phẩm thứ 4
        for (var i = itemsToShow; i < products.length; i++) {
            products[i].classList.add('hidden');
        }

        loadMoreButton.addEventListener('click', function() {
            var currentlyDisplayed = 0;
            for (var i = 0; i < products.length; i++) {
                if (!products[i].classList.contains('hidden')) {
                    currentlyDisplayed++;
                }
            }

            for (var i = currentlyDisplayed; i < currentlyDisplayed + itemsPerClick; i++) {
                if (products[i]) {
                    products[i].classList.remove('hidden');
                }
            }

            if (currentlyDisplayed + itemsPerClick >= products.length) {
                loadMoreButton.style.display = 'none';
            }
        });
    }

    // Thiết lập nút "Xem thêm" cho các danh mục con của "Cửa thép"
    setupLoadMore('load-more-button-sondo', 'category-sondo');
    setupLoadMore('load-more-button-songia', 'category-songia');
    setupLoadMore('load-more-button-laminate', 'category-laminate');

    // Xử lý "Xem thêm" cho các danh mục khác ngoài "Cửa thép" nếu nó tồn tại
    if (document.getElementById('category-other')) {
        setupLoadMore('load-more-button-other', 'category-other');
    }
});
function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : "en"; // Mặc định là "en"
}
function showPhoneNumber(button) {
    var phoneNumber = button.nextElementSibling;
    var lang = getCookie("lang"); // Lấy ngôn ngữ từ cookie

    if (phoneNumber.style.display === "none") {
        phoneNumber.style.display = "block";
        button.textContent = (lang === "vi") ? "Ẩn số điện thoại" : "Hide phone number";
    } else {
        phoneNumber.style.display = "none";
        button.textContent = (lang === "vi") ? "Chi tiết liên hệ" : "Contact Details";
    }
}